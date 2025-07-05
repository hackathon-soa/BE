package hackathon.soa.domain.story;

import hackathon.soa.domain.story.dto.StoryResponseDTO;

public class StoryConverter {

    private StoryConverter() {} // 정적 메서드만 사용

    public static StoryResponseDTO.UploadResult toUploadResult(String imageUrl) {
        return StoryResponseDTO.UploadResult.builder()
                .imageUrl(imageUrl)
                .build();
    }
}
