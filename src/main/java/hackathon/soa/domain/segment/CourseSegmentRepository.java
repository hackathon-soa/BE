package hackathon.soa.domain.segment;

import hackathon.soa.entity.CourseSegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseSegmentRepository extends JpaRepository<CourseSegment, Long> {
    List<CourseSegment> findByCourseId(Long courseId);
}
