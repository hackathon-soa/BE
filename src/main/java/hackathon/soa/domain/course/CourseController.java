package hackathon.soa.domain.course;

import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.course.dto.CourseRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Tag(name = "Course", description = "코스 등록 관련 API")
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final Long userId = 1L;

    @Operation(summary = "코스 등록하기", description = "관광코스 등록하기 버튼 클릭 시 호출되는 API입니다.")
    @PostMapping("/courses")
    public ApiResponse<Long> createCourse(@RequestBody CourseRequestDTO.CreateCourseRequest request) {
        Long courseId = courseService.createCourse(request,userId);
        return ApiResponse.onSuccess(courseId);
    }

    @Operation(summary = "장소 검색", description = "입력된 키워드를 기반으로 장소를 검색합니다.")
    @GetMapping("/api/courses/place")
    public ApiResponse<List<String>> searchPlace(@RequestParam String query) {
        List<String> results = courseService.searchPlace(query);
        return ApiResponse.onSuccess(results);
    }


}
