package hackathon.soa.domain.segment;

import hackathon.soa.common.apiPayload.code.status.ErrorStatus;
import hackathon.soa.common.apiPayload.exception.CourseHandler;
import hackathon.soa.common.apiPayload.exception.GeneralException;
import hackathon.soa.common.apiPayload.exception.SegmentHandler;
import hackathon.soa.domain.course.repository.CourseRepository;
import hackathon.soa.domain.likes.LikesRepository;
import hackathon.soa.domain.member.MemberRepository;
import hackathon.soa.domain.search.SearchConverter;
import hackathon.soa.domain.search.dto.SearchResponseDTO;
import hackathon.soa.domain.segment.dto.SegmentResponseDTO;
import hackathon.soa.domain.segment.repository.CourseSegmentRepository;
import hackathon.soa.domain.segment.repository.MoveSegmentRepository;
import hackathon.soa.domain.segment.repository.StaySegmentRepository;
import hackathon.soa.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SegmentService {

    private final CourseSegmentRepository courseSegmentRepository;
    private final StaySegmentRepository staySegmentRepository;
    private final MoveSegmentRepository moveSegmentRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;
    private final LikesRepository likesRepository;

    public SegmentResponseDTO.CourseDetailResponseDTO getCourseDetail(Long courseId, Long memberId) {
        // 1. 코스 조회 및 작성자 확인
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseHandler(ErrorStatus.NOT_FOUND_COURSE));

        boolean isOwner = course.getMember().getId().equals(memberId);

        // 2. 코스 기본 정보 및 좋아요 정보 조회
        SearchResponseDTO.SearchCourseResponseDTO courseInfo = getCourseById(memberId, courseId);

        // 3. 코스 세그먼트들을 순서대로 조회
        List<CourseSegment> courseSegments = courseSegmentRepository.findByCourse_IdOrderBySegmentOrder(courseId);

        if (courseSegments.isEmpty()) {
            throw new CourseHandler(ErrorStatus.NOT_FOUND_COURSE);
        }

        // 4. 각 세그먼트 정보를 조회하여 DTO 생성
        List<SegmentResponseDTO.SegmentDetailDTO> segmentDetails = courseSegments.stream()
                .map(segment -> buildSegmentDetail(segment, memberId))
                .collect(Collectors.toList());

        // 5. SegmentConverter를 사용하여 최종 응답 DTO 생성
        return SegmentConverter.toCourseDetailResponseDTO(courseId, isOwner, courseInfo, segmentDetails);
    }

    private SegmentResponseDTO.SegmentDetailDTO buildSegmentDetail(CourseSegment courseSegment, Long memberId) {
        Long segmentId = courseSegment.getId();

        try {
            // 장소 세그먼트 먼저 확인
            Optional<StaySegment> staySegment = staySegmentRepository.findById(segmentId);
            if (staySegment.isPresent()) {
                // 현재 사용자가 이 장소 세그먼트에 참여했는지 확인
                boolean isParticipated = staySegment.get().getSegmentParticipations().stream()
                        .anyMatch(participation -> participation.getMember().getId().equals(memberId));

                return SegmentConverter.toSegmentDetailDTO(courseSegment, staySegment.get(), null, isParticipated);
            }

            // 이동 세그먼트 확인
            Optional<MoveSegment> moveSegment = moveSegmentRepository.findById(segmentId);
            if (moveSegment.isPresent()) {
                return SegmentConverter.toSegmentDetailDTO(courseSegment, null, moveSegment.get(), false);
            }

            throw new SegmentHandler(ErrorStatus.SEGMENT_NOT_FOUND);

        } catch (Exception e) {
            log.error("Error while building segment detail for segmentId: {}", segmentId, e);
            throw new SegmentHandler(ErrorStatus.SEGMENT_DESERIALIZATION_ERROR);
        }
    }

    public SearchResponseDTO.SearchCourseResponseDTO getCourseById(Long memberId, Long courseId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.NOT_FOUND_COURSE));

        boolean liked = likesRepository.existsByCourseAndMember(course, member);

        return SearchConverter.toSearchCourseResponseDTO(course, liked);
    }


    public SegmentResponseDTO.MyCourseDetailResponseDTO getMyCourseDetail(Long courseId, Long memberId) {
        // 1. 코스 조회 및 작성자 확인
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseHandler(ErrorStatus.NOT_FOUND_COURSE));

        // 내 코스가 아니면 예외 발생
        if (!course.getMember().getId().equals(memberId)) {
            throw new CourseHandler(ErrorStatus.UNAUTHORIZED_COURSE_ACCESS);
        }

        // 2. 코스 세그먼트들을 순서대로 조회
        List<CourseSegment> courseSegments = courseSegmentRepository.findByCourse_IdOrderBySegmentOrder(courseId);

        if (courseSegments.isEmpty()) {
            throw new CourseHandler(ErrorStatus.NOT_FOUND_COURSE);
        }

        // 3. 각 세그먼트 정보를 조회하여 DTO 생성
        List<SegmentResponseDTO.MySegmentDetailDTO> segmentDetails = courseSegments.stream()
                .map(this::buildMySegmentDetail)
                .collect(Collectors.toList());

        // 4. SegmentConverter를 사용하여 최종 응답 DTO 생성
        return SegmentConverter.toMyCourseDetailResponseDTO(courseId, segmentDetails);
    }

    private SegmentResponseDTO.MySegmentDetailDTO buildMySegmentDetail(CourseSegment courseSegment) {
        Long segmentId = courseSegment.getId();

        try {
            // 장소 세그먼트 먼저 확인
            Optional<StaySegment> staySegment = staySegmentRepository.findById(segmentId);
            if (staySegment.isPresent()) {
                // 동행 메이트 상태 확인
                String mateStatus = determineMateStatus(staySegment.get());

                return SegmentConverter.toMySegmentDetailDTO(courseSegment, staySegment.get(), null, mateStatus);
            }

            // 이동 세그먼트 확인
            Optional<MoveSegment> moveSegment = moveSegmentRepository.findById(segmentId);
            if (moveSegment.isPresent()) {
                return SegmentConverter.toMySegmentDetailDTO(courseSegment, null, moveSegment.get(), null);
            }

            throw new SegmentHandler(ErrorStatus.SEGMENT_NOT_FOUND);

        } catch (Exception e) {
            log.error("Error while building my segment detail for segmentId: {}", segmentId, e);
            throw new SegmentHandler(ErrorStatus.SEGMENT_DESERIALIZATION_ERROR);
        }
    }

    private String determineMateStatus(StaySegment staySegment) {
        List<SegmentParticipation> participations = staySegment.getSegmentParticipations();

        if (participations.isEmpty()) {
            return "모집 중";
        }

        boolean hasApproved = participations.stream()
                .anyMatch(participation -> participation.getStatus() == SegmentParticipationStatus.APPROVED);

        if (hasApproved) {
            return "신청확정";
        }

        return "신청확인";
    }
}