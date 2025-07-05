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


    public static AuthResponseDTO.LoginResponseDTO toLoginResponseDTO(Member member, String accessToken) {
        return AuthResponseDTO.LoginResponseDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }


    public static AuthResponseDTO.UploadResponseDTO toUploadResponseDTO(Member member, String imageUrl) {
        return AuthResponseDTO.UploadResponseDTO.builder()
                .userId(member.getId())
                .verificationImageUrl(imageUrl)
                .build();
    }
}
