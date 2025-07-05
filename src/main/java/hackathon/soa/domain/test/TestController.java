package hackathon.soa.domain.test;

import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.s3.AmazonS3Manager;
import hackathon.soa.domain.s3.UuidRepository;
import hackathon.soa.domain.test.dto.TestResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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

    @Operation(summary = "테스트용 이미지 업로드", description = "MultipartFile로 전달된 이미지를 S3의 tests 디렉토리에 업로드하고 URL을 반환합니다.")
    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> uploadImage(
            @Parameter(description = "업로드할 이미지 파일", required = true)
            @RequestPart("image") MultipartFile image) {

        String url = testService.uploadTestImage(image);
        return ApiResponse.onSuccess(url);
    }
}
