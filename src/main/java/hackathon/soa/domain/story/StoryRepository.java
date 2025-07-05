package hackathon.soa.domain.story;

import hackathon.soa.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
    // 필요한 경우, 회원 ID로 해당 사용자의 스토리를 찾는 메서드도 정의 가능
    // List<Story> findByMemberId(Long memberId);
}

