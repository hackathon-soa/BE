package hackathon.soa.domain.participation;

import hackathon.soa.entity.Member;
import hackathon.soa.entity.SegmentParticipation;
import hackathon.soa.entity.enums.SegmentParticipationStatus;
import hackathon.soa.entity.StaySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ParticipationConverter {

    public SegmentParticipation toSegmentParticipation(Member member, StaySegment segment, SegmentParticipationStatus status) {
        return SegmentParticipation.builder()
                .member(member)
                .staySegment(segment)
                .status(status)
                .build();
    }
}
