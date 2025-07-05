package hackathon.soa.domain.auth;

import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.auth.dto.AuthRequestDTO;
import hackathon.soa.domain.auth.dto.AuthResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
}
