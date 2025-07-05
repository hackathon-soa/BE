package hackathon.soa.domain.participation;

import hackathon.soa.entity.SegmentParticipation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SegmentParticipationRepository extends JpaRepository<SegmentParticipation, Long> {
}
