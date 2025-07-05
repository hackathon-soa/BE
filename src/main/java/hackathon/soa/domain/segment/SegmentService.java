package hackathon.soa.domain.segment;

import hackathon.soa.common.apiPayload.code.status.ErrorStatus;
import hackathon.soa.common.apiPayload.exception.CourseHandler;
import hackathon.soa.common.apiPayload.exception.SegmentHandler;
import hackathon.soa.domain.course.repository.CourseRepository;
import hackathon.soa.domain.segment.dto.SegmentResponseDTO;
import hackathon.soa.domain.segment.repository.CourseSegmentRepository;
import hackathon.soa.domain.segment.repository.MoveSegmentRepository;
import hackathon.soa.domain.segment.repository.StaySegmentRepository;
import hackathon.soa.entity.Course;
import hackathon.soa.entity.CourseSegment;
import hackathon.soa.entity.MoveSegment;
import hackathon.soa.entity.StaySegment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
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

    public SegmentResponseDTO.CourseDetailResponseDTO getCourseDetail(Long courseId, Long memberId) {
        // 1. 코스 조회 및 작성자 확인
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseHandler(ErrorStatus.NOT_FOUND_COURSE));

        boolean isOwner = course.getMember().getId().equals(memberId);

        // 2. 코스 세그먼트들을 순서대로 조회
        List<CourseSegment> courseSegments = courseSegmentRepository.findByCourse_IdOrderBySegmentOrder(courseId);

        if (courseSegments.isEmpty()) {
            throw new CourseHandler(ErrorStatus.NOT_FOUND_COURSE);
        }

        // 3. 각 세그먼트 정보를 조회하여 DTO 생성
        List<SegmentResponseDTO.SegmentDetailDTO> segmentDetails = courseSegments.stream()
                .map(this::buildSegmentDetail)
                .collect(Collectors.toList());

        return SegmentResponseDTO.CourseDetailResponseDTO.builder()
                .courseId(courseId)
                .isOwner(isOwner)
                .segments(segmentDetails)
                .build();
    }

    private SegmentResponseDTO.SegmentDetailDTO buildSegmentDetail(CourseSegment courseSegment) {
        Long segmentId = courseSegment.getId();

        // 시간 포맷터 정의
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd(E)", Locale.KOREAN);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            // 장소 세그먼트 먼저 확인 (@MapsId로 PK가 동일함)
            Optional<StaySegment> staySegment = staySegmentRepository.findById(segmentId);
            if (staySegment.isPresent()) {
                return SegmentResponseDTO.SegmentDetailDTO.builder()
                        .segmentOrder(courseSegment.getSegmentOrder())
                        .segmentType("장소")
                        .date(courseSegment.getStartTime().format(dateFormatter))
                        .startTime(courseSegment.getStartTime().format(timeFormatter))
                        .endTime(courseSegment.getEndTime().format(timeFormatter))
                        .staySegment(SegmentResponseDTO.StaySegmentDTO.from(staySegment.get()))
                        .moveSegment(null)
                        .build();
            }

            // 이동 세그먼트 확인 (@MapsId로 PK가 동일함)
            Optional<MoveSegment> moveSegment = moveSegmentRepository.findById(segmentId);
            if (moveSegment.isPresent()) {
                return SegmentResponseDTO.SegmentDetailDTO.builder()
                        .segmentOrder(courseSegment.getSegmentOrder())
                        .segmentType("이동")
                        .date(courseSegment.getStartTime().format(dateFormatter))
                        .startTime(courseSegment.getStartTime().format(timeFormatter))
                        .endTime(courseSegment.getEndTime().format(timeFormatter))
                        .staySegment(null)
                        .moveSegment(SegmentResponseDTO.MoveSegmentDTO.from(moveSegment.get()))
                        .build();
            }

            throw new SegmentHandler(ErrorStatus.SEGMENT_NOT_FOUND);

        } catch (Exception e) {
            log.error("Error while building segment detail for segmentId: {}", segmentId, e);
            throw new SegmentHandler(ErrorStatus.SEGMENT_DESERIALIZATION_ERROR);
        }
    }
}