package hackathon.soa.domain.participation;

import hackathon.soa.common.apiPayload.code.status.ErrorStatus;
import hackathon.soa.common.apiPayload.exception.AuthHandler;
import hackathon.soa.common.apiPayload.exception.GeneralException;
import hackathon.soa.domain.course.repository.CourseRepository;
import hackathon.soa.domain.member.MemberRepository;
import hackathon.soa.domain.segment.repository.CourseSegmentRepository;
import hackathon.soa.domain.segment.repository.StaySegmentRepository;
import hackathon.soa.entity.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipationService {

    private final MemberRepository memberRepository;
    private final SegmentParticipationRepository segmentParticipationRepository;
    private final StaySegmentRepository staySegmentRepository;
    private final CourseSegmentRepository courseSegmentRepository;
    private final CourseRepository courseRepository;

    public void registerStaySegment(Long memberId, Long segmentId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new AuthHandler(ErrorStatus.MEMBER_NOT_FOUND));

        StaySegment segment = staySegmentRepository.findById(segmentId)
                        .orElseThrow(() -> new GeneralException(ErrorStatus.NOT_FOUND_SEGMENT));

        segmentParticipationRepository.save(
                SegmentParticipation.builder()
                        .member(member)
                        .staySegment(segment)
                        .status(SegmentParticipationStatus.PENDING)
                        .build());
    }

    public void registerEntireSegment(Long memberId, Long courseId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new AuthHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.NOT_FOUND_COURSE));

        List<CourseSegment> courseSegments = courseSegmentRepository.findAllByCourse(course);

        for (CourseSegment cs : courseSegments) {
            Optional<StaySegment> optionalStaySegment = staySegmentRepository.findByCourseSegmentId(cs.getId());

            if (optionalStaySegment.isEmpty()) {
                continue;
            }
            StaySegment staySegment = optionalStaySegment.get();

            segmentParticipationRepository.save(
                    SegmentParticipation.builder()
                            .member(member)
                            .staySegment(staySegment)
                            .status(SegmentParticipationStatus.PENDING)
                            .build()
            );
        }
    }
    public void updateStatus(Long participationId, SegmentParticipationStatus status) {
        SegmentParticipation participation = segmentParticipationRepository.findById(participationId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR));

        participation.updateStatus(status);
    }
}
