package hackathon.soa.domain.member;

import hackathon.soa.domain.auth.dto.AuthRequestDTO;
import hackathon.soa.entity.enums.Gender;
import hackathon.soa.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberConverter {
    public static Member toMember(AuthRequestDTO.SignupRequestDTO request, PasswordEncoder passwordEncoder) {
        // 성별 변환
        Gender gender = "남성".equals(request.getGender()) ? Gender.MALE : Gender.FEMALE;

        return Member.builder()
                .appId(request.getAppId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .nickname(request.getNickname())
                .phoneNumber(request.getPhoneNumber())
                .birth(request.getBirth())
                .gender(gender)
                .disabilityType(request.getDisabilityType())
                .profileImageUrl("https://umc-hack-demo-bucket.s3.ap-northeast-2.amazonaws.com/stories/KakaoTalk_20250706_075626836.png")
                .mileage(0)
                .build();
    }

}
