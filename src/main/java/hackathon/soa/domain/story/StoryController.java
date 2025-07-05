package hackathon.soa.domain.story;

import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.story.dto.StoryRequestDTO;
import hackathon.soa.domain.story.dto.StoryResponseDTO;
import hackathon.soa.domain.test.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Story", description = "스토리 관련 API")
@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
public class StoryController {

    private final TestService testService;
    private final StoryService storyService;

    private final Long userId = 1L;

    @Operation(summary = "스토리 이미지 업로드", description = "MultipartFile로 전달된 이미지를 S3에 업로드하고 URL을 반환합니다.")
    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> uploadImage(
            @Parameter(description = "업로드할 이미지 파일", required = true)
            @RequestPart("image") MultipartFile image) {

        String url = testService.uploadTestImage(image, userId);
        return ApiResponse.onSuccess(url);
    }

    @Operation(summary = "스토리 목록 조회", description = "스토리 목록을 10개씩 페이지네이션으로 반환합니다.")
    @GetMapping
    public ApiResponse<Page<StoryResponseDTO.StoryListDTO>> getStoryList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<StoryResponseDTO.StoryListDTO> stories = storyService.getStories(pageable);
        return ApiResponse.onSuccess(stories);
    }

}
