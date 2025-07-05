package hackathon.soa.domain.likes;

import hackathon.soa.entity.Course;
import hackathon.soa.entity.Likes;
import hackathon.soa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    boolean existsByCourseAndMember(Course course, Member member);
}
