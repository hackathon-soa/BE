package hackathon.soa.domain.story;

import hackathon.soa.config.AmazonConfig;
import hackathon.soa.domain.s3.AmazonS3Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final AmazonS3Manager amazonS3Manager;
    private final AmazonConfig amazonConfig;

    public String uploadTestImage(MultipartFile image) {
        // 파일명을 고유하게 생성: 예) tests/uuid.jpg
        String keyName = generateTestImageKeyName(image.getOriginalFilename());

        // S3에 업로드 및 URL 반환
        return amazonS3Manager.uploadFile(keyName, image);
    }

    private String generateTestImageKeyName(String originalFileName) {
        String ext = originalFileName.substring(originalFileName.lastIndexOf(".")); // .jpg, .png 등
        return amazonConfig.getTestsPath() + "/" + UUID.randomUUID() + ext; // tests/uuid.jpg
    }

}
