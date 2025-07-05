package hackathon.soa.domain.story.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

public class StoryRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UploadRequest {
        private Long memberId;               // 회원 ID
        private MultipartFile image;         // 업로드할 이미지
    }
}
