package hackathon.soa.domain.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TestResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestDTO{
        String testString;
    }

    @Getter
    @AllArgsConstructor
    public static class ImageUploadResultDTO {
        private String imageUrl;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExceptionDTO{
        Integer flag;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JWTResponseDTO {
        private Long memberId;
    }
}
