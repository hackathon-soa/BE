package hackathon.soa.domain.member;

import hackathon.soa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByAppId(String appId);
}
