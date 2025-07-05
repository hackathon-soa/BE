package hackathon.soa.domain.test;

import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.test.dto.TestResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/response")
    public ApiResponse<TestResponseDTO.TestDTO> testAPI(){
        return ApiResponse.onSuccess(TestConverter.toTestDTO());
    }
}
