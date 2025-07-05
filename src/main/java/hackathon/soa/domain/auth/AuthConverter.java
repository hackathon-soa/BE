package hackathon.soa.domain.auth;

import hackathon.soa.domain.auth.dto.AuthResponseDTO;
import hackathon.soa.entity.Member;

public class AuthConverter {
    public static AuthResponseDTO.SignupResponseDTO toSignUpResponseDTO(Member savedMember) {
        return AuthResponseDTO.SignupResponseDTO.builder()
                .userId(savedMember.getId())
                .appId(savedMember.getAppId())
                .build()
                ;
    }

    public static AuthResponseDTO.DuplicateCheckResponseDTO toDuplicateCheckResponseDTO(boolean isExists) {
        return AuthResponseDTO.DuplicateCheckResponseDTO.builder()
                .isAvailable(!isExists)
                .message(isExists ? "이미 사용중인 아이디입니다." : "사용가능한 아이디입니다.")
                .build();
    }
}
