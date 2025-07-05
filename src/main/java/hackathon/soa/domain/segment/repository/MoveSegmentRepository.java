package hackathon.soa.domain.segment.repository;

import hackathon.soa.entity.MoveSegment;
import hackathon.soa.entity.StaySegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoveSegmentRepository extends JpaRepository<MoveSegment, Long> {
}
