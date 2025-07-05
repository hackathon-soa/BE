package hackathon.soa.domain.participation;

import hackathon.soa.entity.SegmentParticipation;
import hackathon.soa.entity.StaySegment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.Segment;
import java.util.List;

public interface SegmentParticipationRepository extends JpaRepository<SegmentParticipation, Long> {
    List<SegmentParticipation> findAllByStaySegment(StaySegment segment);
}
