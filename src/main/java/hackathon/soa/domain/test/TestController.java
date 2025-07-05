package hackathon.soa.domain.test;

import hackathon.soa.common.JwtUser;
import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.test.dto.TestResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Test", description = "테스트용 API")
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("")
    public ApiResponse<TestResponseDTO.TestDTO> test() {
        return ApiResponse.onSuccess(TestConverter.toTempTestDTO());
    }


    @GetMapping("/Jwt")
    @Operation(summary = "JWT 토큰 테스트", description = "JWT 토큰을 통해 현재 사용자의 memberId를 반환합니다.")
    public ApiResponse<TestResponseDTO.JWTResponseDTO> testJwtToken(
            @JwtUser Long memberId
    ) {
        TestResponseDTO.JWTResponseDTO response = TestConverter.toJWTTestDTO(memberId);
        return ApiResponse.onSuccess(response);
    }
}
