package hackathon.soa.domain.story;

import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.story.dto.StoryRequestDTO;
import hackathon.soa.domain.story.dto.StoryResponseDTO;
import hackathon.soa.domain.test.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Story", description = "스토리 관련 API")
@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
public class StoryController {

    private final TestService testService;

    private final Long userId = 1L;

    @Operation(summary = "스토리 이미지 업로드", description = "MultipartFile로 전달된 이미지를 S3에 업로드하고 URL을 반환합니다.")
    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> uploadImage(
            @Parameter(description = "업로드할 이미지 파일", required = true)
            @RequestPart("image") MultipartFile image) {

        String url = testService.uploadTestImage(image, userId);
        return ApiResponse.onSuccess(url);
    }

}
