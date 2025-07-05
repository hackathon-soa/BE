package hackathon.soa.domain.participation;

import hackathon.soa.common.apiPayload.code.status.ErrorStatus;
import hackathon.soa.common.apiPayload.exception.AuthHandler;
import hackathon.soa.common.apiPayload.exception.GeneralException;
import hackathon.soa.domain.member.MemberRepository;
import hackathon.soa.domain.segment.CourseSegmentRepository;
import hackathon.soa.entity.CourseSegment;
import hackathon.soa.entity.Member;
import hackathon.soa.entity.SegmentParticipation;
import hackathon.soa.entity.SegmentParticipationStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.Segment;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipationService {

    private final MemberRepository memberRepository;
    private final SegmentParticipationRepository segmentParticipationRepository;
    private final CourseSegmentRepository courseSegmentRepository;

    public void registerCourseSegment(Long memberId, Long segmentId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new AuthHandler(ErrorStatus.MEMBER_NOT_FOUND));

        CourseSegment segment = courseSegmentRepository.findById(segmentId)
                        .orElseThrow(() -> new GeneralException(ErrorStatus.NOT_FOUND_SEGMENT));

        segmentParticipationRepository.save(
                SegmentParticipation.builder()
                        .member(member)
                        .courseSegment(segment)
                        .status(SegmentParticipationStatus.PENDING)
                        .build());
    }
}
