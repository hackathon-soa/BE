package hackathon.soa.domain.story;

import hackathon.soa.config.AmazonConfig;
import hackathon.soa.domain.s3.AmazonS3Manager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final AmazonS3Manager amazonS3Manager;
    private final AmazonConfig amazonConfig;   // testsPath와 비슷하게 storiesPath를 yml에 정의했다고 가정

    /**
     * S3에 스토리 이미지를 업로드 후 URL 반환
     */
    public String uploadStoryImage(MultipartFile image) {
        validateImage(image);

        String keyName = generateStoryKeyName(image.getOriginalFilename());
        return amazonS3Manager.uploadFile(keyName, image);
    }

    /* ---------- private ---------- */

    private void validateImage(MultipartFile image) {
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("업로드할 이미지가 없습니다.");
        }
    }

    private String generateStoryKeyName(String originalFileName) {
        String ext = FilenameUtils.getExtension(originalFileName);   // commons-io
        return String.format("%s/%s.%s",
                amazonConfig.getStoriesPath(),   // 예) "stories"
                UUID.randomUUID(),
                ext.toLowerCase());
    }
}
