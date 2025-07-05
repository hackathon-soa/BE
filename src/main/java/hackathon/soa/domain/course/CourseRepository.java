package hackathon.soa.domain.course;

import hackathon.soa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.plaf.synth.Region;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c join fetch c.member where c.member.id = :memberId")
    List<Course> findAllByMemberId(@Param("memberId") Long memberId);

    @Query("select count(l) from Likes l where l.course.id = :courseId")
    int countLikesByCourseId(@Param("courseId") Long courseId);

    List<Course> findAllByRegion(String region);

    @Query("select c from Course c where :targetDate between c.startTime and c.endTime")
    List<Course> findAllByDateBetween(@Param("targetDate") LocalDateTime targetDate);
}
