package hackathon.soa.domain.auth.course;

import hackathon.soa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c join fetch c.member where c.member.id = :memberId")
    List<Course> findAllByMemberId(@Param("memberId") Long memberId);

    @Query("select count(l) from Likes l where l.course.id = :courseId")
    int countLikesByCourseId(@Param("courseId") Long courseId);
}
