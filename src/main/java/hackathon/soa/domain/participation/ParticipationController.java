package hackathon.soa.domain.participation;

import hackathon.soa.common.JwtUser;
import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.search.dto.SearchResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/participation")
public class ParticipationController {

    private final ParticipationService participationService;

    @PostMapping("/segments/{segmentId}/register")
    @Operation(
            summary = "동행 신청하기 api",
            description = "여행 코스에서 동행을 신청하는 api입니다."
    )
    public ApiResponse<?> registerCourseSegment (
            @Parameter(hidden = true) @JwtUser Long memberId,
            @PathVariable Long segmentId
    ) {
        participationService.registerStaySegment(memberId, segmentId);
        return ApiResponse.onSuccess();
    }

}
