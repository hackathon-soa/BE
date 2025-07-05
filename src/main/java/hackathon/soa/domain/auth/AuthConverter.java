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
}
