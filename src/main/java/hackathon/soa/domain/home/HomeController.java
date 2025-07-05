package hackathon.soa.domain.home;

import hackathon.soa.common.JwtUser;
import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.home.dto.HomeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home/")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @PostMapping("/my-courses")
    @Operation(
            summary = "나의 여행 코스 조회",
            description = "현재 로그인 한 사용자가 생성한 여행 코스를 조회합니다."
    )
    public ApiResponse<HomeResponseDTO.MyCoursesResponseDTO> getMyCourses(
            @Parameter(hidden = true) @JwtUser Long memberId
    ) {
        HomeResponseDTO.MyCoursesResponseDTO result = homeService.getMyCourses(memberId);

        return ApiResponse.onSuccess(result);
    }

    @PostMapping("/my-courses/two")
    @Operation(
            summary = "나의 여행 코스 두개 조회",
            description = "현재 로그인 한 사용자가 생성한 여행 코스 상단 두개를 조회합니다."
    )
    public ApiResponse<HomeResponseDTO.MyCoursesResponseDTO> getMyTwoCourses(
            @Parameter(hidden = true) @JwtUser Long memberId
    ) {
        HomeResponseDTO.MyCoursesResponseDTO result = homeService.getMyTwoCourses(memberId);

        return ApiResponse.onSuccess(result);
    }
}
