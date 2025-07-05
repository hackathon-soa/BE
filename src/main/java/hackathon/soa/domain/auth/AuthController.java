package hackathon.soa.domain.auth;

import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.auth.dto.AuthRequestDTO;
import hackathon.soa.domain.auth.dto.AuthResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
