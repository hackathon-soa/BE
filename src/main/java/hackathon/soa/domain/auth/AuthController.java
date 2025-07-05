package hackathon.soa.domain.auth;

import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.auth.dto.AuthRequestDTO;
import hackathon.soa.domain.auth.dto.AuthResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(
            summary = "회원가입",
            description = "사용자 정보를 받아 새로운 계정을 생성합니다."
    )
    public ApiResponse<AuthResponseDTO.SignupResponseDTO> register(
            @RequestBody @Valid AuthRequestDTO.SignupRequestDTO request
    ) {
        AuthResponseDTO.SignupResponseDTO result = authService.register(request);
        return ApiResponse.onSuccess(result);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "신원인증 이미지 업로드",
            description = "사용자의 신원정보를 증빙할 수 있는 이미지를 받아서 S3에 업로드합니다."
    )
    public ApiResponse<AuthResponseDTO.UploadResponseDTO> upload(
            @RequestParam Long memberId,
            MultipartFile file
            ) {
        AuthResponseDTO.UploadResponseDTO result = authService.uploadVerification(memberId, file);
        return ApiResponse.onSuccess(result);
    }


    @GetMapping("/check")
    @Operation(
            summary = "아이디 중복 확인",
            description = "회원가입 시 아이디 중복 여부를 확인합니다."
    )
    public ApiResponse<AuthResponseDTO.DuplicateCheckResponseDTO> checkDuplicate(
            @RequestParam String appId
    ) {
        AuthRequestDTO.DuplicateCheckRequestDTO request = AuthRequestDTO.DuplicateCheckRequestDTO.builder()
                .appId(appId)
                .build();

        AuthResponseDTO.DuplicateCheckResponseDTO result = authService.checkDuplicate(request);
        return ApiResponse.onSuccess(result);
    }


    @PostMapping("/login")
    @Operation(
            summary = "로그인",
            description = "아이디와 비밀번호로 로그인하여 액세스 토큰을 발급받습니다."
    )
    public ApiResponse<AuthResponseDTO.LoginResponseDTO> login(
            @RequestBody @Valid AuthRequestDTO.LoginRequestDTO request
    ) {
        AuthResponseDTO.LoginResponseDTO result = authService.login(request);
        return ApiResponse.onSuccess(result);
    }
}
