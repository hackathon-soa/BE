package hackathon.soa.domain.course.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverLocalResponse {
    private List<NaverLocalItem> items;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NaverLocalItem {
        private String title;
        private String roadAddress;
    }
}

