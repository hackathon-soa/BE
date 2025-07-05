package hackathon.soa.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignupResponseDTO {
        private Long userId;
        private String appId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DuplicateCheckResponseDTO {
        private Boolean isAvailable;
        private String message;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResponseDTO {
        private Long memberId;
        private String accessToken;
    }
}
