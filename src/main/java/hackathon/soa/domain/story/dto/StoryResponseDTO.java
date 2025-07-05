package hackathon.soa.domain.story.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoryResponseDTO {

    @Getter
    @AllArgsConstructor
    public static class ImageUploadResultDTO {
        private String imageUrl;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class StoryListDTO {
        private Long memberId;
        private String nickname;
        private String profileImageUrl;
        private String imageUrl;
    }
}
