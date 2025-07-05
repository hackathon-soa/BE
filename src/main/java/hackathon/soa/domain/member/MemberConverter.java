package hackathon.soa.domain.member;

import hackathon.soa.domain.auth.dto.AuthRequestDTO;
import hackathon.soa.entity.Gender;
import hackathon.soa.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberConverter {
    public static Member toMember(AuthRequestDTO.SignupRequestDTO request, PasswordEncoder passwordEncoder) {
        // 성별 변환
        Gender gender = "남성".equals(request.getGender()) ? Gender.MALE : Gender.FEMALE;

        // 프로필 이미지 변환
        String profileImageUrl;
        switch (request.getProfileImage()) {
            case 1:
                profileImageUrl = "https://umc-hack-demo-bucket.s3.ap-northeast-2.amazonaws.com/rabbit1.png";
                break;
            case 2:
                profileImageUrl = "https://umc-hack-demo-bucket.s3.ap-northeast-2.amazonaws.com/rabbit2.png";
                break;
            case 3:
                profileImageUrl = "https://umc-hack-demo-bucket.s3.ap-northeast-2.amazonaws.com/rabbit3.png";
                break;
            case 4:
                profileImageUrl = "https://umc-hack-demo-bucket.s3.ap-northeast-2.amazonaws.com/rabbit4.png";
                break;
            case 5:
                profileImageUrl = "https://umc-hack-demo-bucket.s3.ap-northeast-2.amazonaws.com/rabbit5.png";
                break;
            default:
                profileImageUrl = "https://umc-hack-demo-bucket.s3.ap-northeast-2.amazonaws.com/rabbit1.png";
        }

        return Member.builder()
                .appId(request.getAppId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .nickname(request.getNickname())
                .phoneNumber(request.getPhoneNumber())
                .birth(request.getBirth())
                .gender(gender)
                .disabilityType(request.getDisabilityType())
                .profileImageUrl(profileImageUrl)
                .mileage(0)
                .build();
    }

}
