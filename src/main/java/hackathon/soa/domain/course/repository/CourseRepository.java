package hackathon.soa.domain.course.repository;

import hackathon.soa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // 예: 특정 지역 기반 코스 검색
    // List<Course> findByRegion(String region);
}

