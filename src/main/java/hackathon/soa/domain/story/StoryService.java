package hackathon.soa.domain.story;

import hackathon.soa.config.AmazonConfig;
import hackathon.soa.domain.s3.AmazonS3Manager;
import hackathon.soa.domain.story.dto.StoryRequestDTO;
import hackathon.soa.domain.story.dto.StoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final AmazonS3Manager amazonS3Manager;
    private final AmazonConfig amazonConfig;

    public StoryResponseDTO.UploadResult uploadStory(StoryRequestDTO.UploadRequest request) {
        MultipartFile image = request.getImage();
        Long memberId = request.getMemberId();

        String keyName = generateStoryKeyName(image.getOriginalFilename(), memberId);
        String imageUrl = amazonS3Manager.uploadFile(keyName, image);

        return StoryResponseDTO.UploadResult.builder()
                .imageUrl(imageUrl)
                .build();
    }

    private String generateStoryKeyName(String originalFileName, Long memberId) {
        String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
        return amazonConfig.getStoriesPath() + "/" + memberId + "/" + UUID.randomUUID() + ext;
    }
}
