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
}
