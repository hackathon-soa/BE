package hackathon.soa.domain.segment.repository;

import hackathon.soa.entity.Course;
import hackathon.soa.entity.CourseSegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//통일
public interface CourseSegmentRepository extends JpaRepository<CourseSegment, Long> {
    List<CourseSegment> findByCourse_IdOrderBySegmentOrder(Long courseId);
    List<CourseSegment> findAllByCourse(Course course);
}
