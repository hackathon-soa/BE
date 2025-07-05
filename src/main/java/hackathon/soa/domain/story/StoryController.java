package hackathon.soa.domain.story;

import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.story.dto.StoryRequestDTO;
import hackathon.soa.domain.story.dto.StoryResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stories")
public class StoryController {

    private final StoryService storyService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "스토리 추가", description = "회원 ID와 이미지를 받아 스토리를 등록합니다.")
    public ApiResponse<StoryResponseDTO.UploadResult> uploadStory(
            @RequestPart("image") MultipartFile image,
            @RequestPart("memberId") Long memberId
    ) {
        StoryRequestDTO.UploadRequest request = StoryRequestDTO.UploadRequest.builder()
                .memberId(memberId)
                .image(image)
                .build();

        return ApiResponse.onSuccess(storyService.uploadStory(request));
    }
}
