package hackathon.soa.domain.story.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Story 업로드 요청 DTO
 *  - multipart/form-data 전송 시
 *    ├─ image   : MultipartFile  (컨트롤러 @RequestPart)
 *    └─ request : JSON           (memberId만 포함)
 */
public class StoryRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UploadRequest {
        private Long memberId;
    }
}
