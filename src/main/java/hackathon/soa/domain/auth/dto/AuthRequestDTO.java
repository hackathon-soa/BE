package hackathon.soa.domain.auth.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthRequestDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignupRequestDTO {

        @NotBlank(message = "아이디는 필수입니다")
        @Size(min = 4, max = 20, message = "아이디는 4~20자 사이여야 합니다")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문자와 숫자만 가능합니다")
        private String appId;

        @NotBlank(message = "비밀번호는 필수입니다")
        @Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                message = "비밀번호는 영문자, 숫자, 특수문자를 포함해야 합니다")
        private String password;

        @NotBlank(message = "이름은 필수입니다")
        @Size(min = 2, max = 10, message = "이름은 2~10자 사이여야 합니다")
        private String name;

        @NotBlank(message = "닉네임은 필수입니다")
        @Size(min = 2, max = 15, message = "닉네임은 2~15자 사이여야 합니다")
        private String nickname;

        @NotBlank(message = "전화번호는 필수입니다")
        @Pattern(regexp = "^01[0-9]-\\d{4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다 (ex. 010-1234-5678)")
        private String phoneNumber;

        @NotBlank(message = "생년월일은 필수입니다")
        @Pattern(regexp = "^\\d{8}$", message = "생년월일 형식이 올바르지 않습니다 (ex. 20010528)")
        private String birth;

        @NotBlank(message = "성별은 필수입니다")
        @Pattern(regexp = "^(남성|여성)$", message = "성별은 '남성' 또는 '여성'이어야 합니다")
        private String gender;

        @NotBlank(message = "장애 유형은 필수입니다")
        @Pattern(regexp = "^\\[.*\\]$", message = "장애 유형은 JSON 배열 형식이어야 합니다")
        private String disabilityType;

        @NotNull(message = "프로필 이미지는 필수입니다")
        private Integer profileImage;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DuplicateCheckRequestDTO {
        @NotBlank(message = "아이디는 필수입니다")
        @Size(min = 4, max = 20, message = "아이디는 4~20자 사이여야 합니다")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문자와 숫자만 가능합니다")
        private String appId;
    }
}