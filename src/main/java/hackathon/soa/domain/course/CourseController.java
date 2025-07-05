package hackathon.soa.domain.course;

import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.course.dto.CourseRequestDTO;
import hackathon.soa.domain.story.StoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Course", description = "코스 등록 관련 API")
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final Long userId = 1L;

    @PostMapping("/courses")
    public ApiResponse<Long> createCourse(@RequestBody CourseRequestDTO.CreateCourseRequest request) {
        Long courseId = courseService.createCourse(request,userId);
        return ApiResponse.onSuccess(courseId); // ✅ 올바른 정적 메서드 호출
    }

}
