package hackathon.soa.domain.segment;

import hackathon.soa.entity.CourseSegment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSegmentRepository extends JpaRepository<CourseSegment, Long> {
}
