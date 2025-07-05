package hackathon.soa.domain.search;

import hackathon.soa.common.JwtUser;
import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.search.dto.SearchResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/region")
    @Operation(
            summary = "지역 기반 검색",
            description = "지역을 검색어로 코스를 검색합니다."
    )
    public ApiResponse<SearchResponseDTO.SearchCoursesResponseDTO> getCoursesByRegion(
            @Parameter(hidden = true) @JwtUser Long memberId,
            @RequestParam String region
    ) {
        SearchResponseDTO.SearchCoursesResponseDTO result = searchService.getCoursesByRegion(memberId, region);

        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/date")
    @Operation(
            summary = "날짜 기반 검색",
            description = "특정 날짜를 기반으로 코스를 검색합니다."
    )
    public ApiResponse<SearchResponseDTO.SearchCoursesResponseDTO> getCoursesByDate(
            @Parameter(hidden = true) @JwtUser Long memberId,
            @RequestParam String date
    ) {
        SearchResponseDTO.SearchCoursesResponseDTO result = searchService.getCoursesByDate(memberId, date);

        return ApiResponse.onSuccess(result);
    }
}
