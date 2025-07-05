package hackathon.soa.domain.course;

import hackathon.soa.domain.course.dto.NaverLocalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoursePlaceSearchService {

    private final RestTemplate restTemplate;

    @Value("${naver.client-id}")
    private String clientId;

    @Value("${naver.client-secret}")
    private String clientSecret;

    public List<String> search(String query) {
        //String encoded = UriUtils.encode(query, StandardCharsets.UTF_8);
        String apiUrl = "https://openapi.naver.com/v1/search/local.json?query=" + query + "&display=5&start=1&sort=random";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        headers.set("User-Agent", "Mozilla/5.0"); // ✅ User-Agent도 추가

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        // ✅ 실제 디버깅용: 원시 응답 출력
        ResponseEntity<String> debugResponse = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                String.class
        );
        System.out.println("✅ 요청 URL: " + apiUrl);
        System.out.println("✅ Client ID: " + clientId);
        System.out.println("✅ 네이버 원시 응답: " + debugResponse.getBody());

        // ✅ 실사용 로직: DTO 매핑용
        ResponseEntity<NaverLocalResponse> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                NaverLocalResponse.class
        );

        if (response.getBody() == null || response.getBody().getItems() == null) {
            System.out.println("⚠️ 응답이 null이거나 items 없음");
            return List.of("검색 결과가 없습니다.");
        }

        return response.getBody().getItems().stream()
                .map(item -> item.getTitle().replaceAll("<.*?>", "") )
                //.map(item -> item.getTitle().replaceAll("<.*?>", "") + " - " + item.getRoadAddress())
                .collect(Collectors.toList());
    }
}

