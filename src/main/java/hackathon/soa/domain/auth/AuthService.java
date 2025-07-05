package hackathon.soa.domain.auth;

import hackathon.soa.common.JwtUtil;
import hackathon.soa.common.apiPayload.code.status.ErrorStatus;
import hackathon.soa.common.apiPayload.exception.AuthHandler;
import hackathon.soa.domain.auth.dto.AuthRequestDTO;
import hackathon.soa.domain.auth.dto.AuthResponseDTO;
import hackathon.soa.domain.member.MemberConverter;
import hackathon.soa.domain.member.MemberRepository;
import hackathon.soa.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDTO.SignupResponseDTO register(AuthRequestDTO.SignupRequestDTO request) {
        // 1) 아이디 중복 체크
        if (memberRepository.existsByAppId(request.getAppId())) {
            throw new AuthHandler(ErrorStatus.ID_DUPLICATED);
        }


        // 2) Entity 변환  Converter 작업 (비밀번호 암호화)
        Member member = MemberConverter.toMember(request, passwordEncoder);


        // 3) Rdpository 저장
        Member savedMember = memberRepository.save(member);


        // 4) ResponseDTO 변환 Converter 작업
        return AuthConverter.toSignUpResponseDTO(savedMember);
    }

    public AuthResponseDTO.DuplicateCheckResponseDTO checkDuplicate(AuthRequestDTO.DuplicateCheckRequestDTO request) {
        // 1) 아이디 중복 체크
        boolean isExists = memberRepository.existsByAppId(request.getAppId());

        return AuthConverter.toDuplicateCheckResponseDTO(isExists);
    }

    public AuthResponseDTO.LoginResponseDTO login(AuthRequestDTO.LoginRequestDTO request) {
        // 1) 사용자 조회
        Member member = memberRepository.findByAppId(request.getAppId())
                .orElseThrow(() -> new AuthHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // 2) 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new AuthHandler(ErrorStatus.INVALID_PASSWORD);
        }

        // 3) 계정 승인 여부 확인
        if (!member.getIsVerified()) {
            throw new AuthHandler(ErrorStatus.ACCOUNT_NOT_APPROVED);
        }

        // 4) JWT 토큰 생성
        String accessToken = jwtUtil.generateAccessToken(member.getId());

        // 45) 응답 DTO 생성
        return AuthConverter.toLoginResponseDTO(member, accessToken);
    }
}
