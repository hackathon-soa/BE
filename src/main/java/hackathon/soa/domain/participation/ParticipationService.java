package hackathon.soa.domain.participation;

import hackathon.soa.common.apiPayload.code.status.ErrorStatus;
import hackathon.soa.common.apiPayload.exception.AuthHandler;
import hackathon.soa.common.apiPayload.exception.GeneralException;
import hackathon.soa.domain.course.CourseRepository;
import hackathon.soa.domain.member.MemberRepository;
import hackathon.soa.domain.segment.CourseSegmentRepository;
import hackathon.soa.domain.segment.StaySegmentRepository;
import hackathon.soa.entity.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.Segment;
import java.util.List;
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

    public void registerEntireCourse(Long memberId, Long courseId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new AuthHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.NOT_FOUND_COURSE));

        List<CourseSegment> courseSegments = courseSegmentRepository.findByCourseId(courseId);

        if (courseSegments.isEmpty()) {
            throw new GeneralException(ErrorStatus.NOT_FOUND_SEGMENT);
        }

        List<StaySegment> staySegments = courseSegments.stream()
                .map(CourseSegment::getId)
                .map(id -> staySegmentRepository.findById(id)
                        .orElseThrow(() -> new GeneralException(ErrorStatus.NOT_FOUND_SEGMENT)))
                .collect(Collectors.toList());

        StaySegment segment1 = staySegmentRepository.findById(1L).orElseThrow(() -> new GeneralException(ErrorStatus.NOT_FOUND_SEGMENT));
        courseSegments.stream()
                        .forEach(segment ->
                                segmentParticipationRepository.save(
                                        SegmentParticipation.builder()
                                        .member(member)
                                        .staySegment(segment1)
                                        .status(SegmentParticipationStatus.PENDING)
                                        .build())
                        );
    }
}
