package hackathon.soa.domain.member;

import hackathon.soa.entity.Member;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByAppId(String appId);

    Optional<Member> findByAppId(String appId);
}
