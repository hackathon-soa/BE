package hackathon.soa.domain.segment;

import hackathon.soa.common.JwtUser;
import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.auth.AuthService;
import hackathon.soa.domain.auth.dto.AuthRequestDTO;
import hackathon.soa.domain.auth.dto.AuthResponseDTO;
import hackathon.soa.domain.segment.dto.SegmentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/segment")
@RequiredArgsConstructor
public class SegmentController {

    private final SegmentService segmentService;

    @GetMapping("/{courseId}")
    @Operation(
            summary = "코스 상세 조회",
            description = "코스 ID로 특정 코스의 세그먼트들의 정보를 조회합니다."
    )
    public ApiResponse<SegmentResponseDTO.CourseDetailResponseDTO> getCourseDetail(
            @PathVariable Long courseId,
            @Parameter(hidden = true) @JwtUser Long memberId
    ) {
        SegmentResponseDTO.CourseDetailResponseDTO result = segmentService.getCourseDetail(courseId, memberId);
        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/my/{courseId}")
    @Operation(
            summary = "나의 특정 코스 상세 조회",
            description = "코스 ID로 특정 코스의 세그먼트들의 정보를 조회합니다. (내꺼)"
    )
    public ApiResponse<SegmentResponseDTO.MyCourseDetailResponseDTO> getMyCourseDetail(
            @PathVariable Long courseId,
            @Parameter(hidden = true) @JwtUser Long memberId
    ) {
        SegmentResponseDTO.MyCourseDetailResponseDTO result = segmentService.getMyCourseDetail(courseId, memberId);
        return ApiResponse.onSuccess(result);
    }
}
