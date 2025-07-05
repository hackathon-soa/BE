package hackathon.soa.domain.s3;

import hackathon.soa.entity.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
    Optional<Uuid> findByUuid(String uuid);
}

