package hackathon.soa.domain.story;

import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.story.dto.StoryRequestDTO;
import hackathon.soa.domain.story.dto.StoryResponseDTO;
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

    private final StoryService storyService;

    @Operation(
            summary = "스토리 이미지 업로드",
            description = "memberId와 MultipartFile 이미지를 받아 S3(stories/)에 저장 후 URL을 반환합니다."
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<StoryResponseDTO.UploadResult> uploadStory(
            @Parameter(description = "스토리 작성자 ID", required = true)
            @RequestPart("request") StoryRequestDTO.UploadRequest request,

            @Parameter(description = "업로드할 이미지 파일", required = true)
            @RequestPart("image") MultipartFile image) {

        // 🚩 필요하다면 memberId로 회원 존재 여부 등을 체크
        String url = storyService.uploadStoryImage(image);

        return ApiResponse.onSuccess(StoryConverter.toUploadResult(url));
    }
}
