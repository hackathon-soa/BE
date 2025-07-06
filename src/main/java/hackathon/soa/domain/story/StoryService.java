package hackathon.soa.domain.story;

import hackathon.soa.domain.story.dto.StoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;

    public Page<StoryResponseDTO.StoryListDTO> getStories(Pageable pageable) {
        return storyRepository.findAll(pageable)
                .map(StoryConverter::toStoryListDTO);
    }

}
