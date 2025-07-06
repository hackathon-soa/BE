package hackathon.soa.domain.story;

import hackathon.soa.domain.story.dto.StoryResponseDTO;
import hackathon.soa.entity.Story;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StoryConverter {

    public StoryResponseDTO.StoryListDTO toStoryListDTO(Story story) {
        return StoryResponseDTO.StoryListDTO.builder()
                .memberId(story.getMember().getId())
                .nickname(story.getMember().getNickname())
                .profileImageUrl(story.getMember().getProfileImageUrl())
                .imageUrl(story.getImageUrl())
                .build();
    }
}
