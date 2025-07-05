package hackathon.soa.domain.course.repository;

import hackathon.soa.entity.TravelStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TravelStyleRepository extends JpaRepository<TravelStyle, Long> {
    Optional<TravelStyle> findByName(String name); // 🔍 여행 테마 이름으로 조회
}


