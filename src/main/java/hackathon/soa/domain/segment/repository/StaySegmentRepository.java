package hackathon.soa.domain.segment.repository;

import hackathon.soa.entity.Course;
import hackathon.soa.entity.StaySegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StaySegmentRepository extends JpaRepository<StaySegment, Long> {
    Optional<StaySegment> findByCourseSegmentId(Long courseSegmentId);
}
