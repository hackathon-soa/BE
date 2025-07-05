package hackathon.soa.domain.course.repository;

import hackathon.soa.entity.CourseSegment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseSegmentRepository extends JpaRepository<CourseSegment, Long> {
    // 예: 특정 Course의 모든 Segment를 순서대로 조회
    // List<CourseSegment> findByCourseIdOrderBySegmentOrder(Long courseId);
}
