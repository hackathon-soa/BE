package hackathon.soa.domain.test;

import hackathon.soa.common.apiPayload.exception.TempHandler;
import hackathon.soa.config.AmazonConfig;
import hackathon.soa.domain.member.MemberRepository;
import hackathon.soa.domain.s3.AmazonS3Manager;
import hackathon.soa.domain.story.StoryRepository;
import hackathon.soa.entity.Member;
import hackathon.soa.entity.Story;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TestService {

    private final AmazonS3Manager amazonS3Manager;
    private final AmazonConfig amazonConfig;
    private final MemberRepository memberRepository;
    private final StoryRepository storyRepository;

    public String uploadTestImage(MultipartFile image, Long userId) {
        // 1. 파일명 생성
        String keyName = generateTestImageKeyName(image.getOriginalFilename());

        // 2. S3에 업로드
        String imageUrl = amazonS3Manager.uploadFile(keyName, image);

        // 3. Member 조회
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 회원이 존재하지 않습니다: " + userId));

        // 4. Story 생성 및 저장
        Story story = Story.builder()
                .imageUrl(imageUrl)
                .member(member)
                .build();
        storyRepository.save(story);

        return imageUrl;
    }

    private String generateTestImageKeyName(String originalFileName) {
        String ext = originalFileName.substring(originalFileName.lastIndexOf(".")); // .jpg, .png 등
        return amazonConfig.getTestsPath() + "/" + UUID.randomUUID() + ext; // tests/uuid.jpg
    }

}
