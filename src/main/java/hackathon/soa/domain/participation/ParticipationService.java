package hackathon.soa.domain.participation;

import hackathon.soa.common.apiPayload.code.status.ErrorStatus;
import hackathon.soa.common.apiPayload.exception.AuthHandler;
import hackathon.soa.common.apiPayload.exception.GeneralException;
import hackathon.soa.domain.member.MemberRepository;
import hackathon.soa.domain.segment.repository.CourseSegmentRepository;
import hackathon.soa.domain.segment.repository.StaySegmentRepository;
import hackathon.soa.entity.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipationService {

    private final MemberRepository memberRepository;
    private final SegmentParticipationRepository segmentParticipationRepository;
    private final StaySegmentRepository staySegmentRepository;
    private final CourseSegmentRepository courseSegmentRepository;

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
}
