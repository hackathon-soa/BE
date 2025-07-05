package hackathon.soa.domain.participation;

import hackathon.soa.common.JwtUser;
import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.domain.search.dto.SearchResponseDTO;
import hackathon.soa.entity.SegmentParticipationStatus;
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

    @PatchMapping("/segments/{participationId}/approve")
    @Operation(summary = "동행 수락 API", description = "신청자를 수락하여 상태를 APPROVED로 변경")
    public ApiResponse<?> approveParticipation(@PathVariable Long participationId) {
        participationService.updateStatus(participationId, SegmentParticipationStatus.APPROVED);
        return ApiResponse.onSuccess();
    }

    @PatchMapping("/segments/{participationId}/reject")
    @Operation(summary = "동행 거절 API", description = "신청자를 거절하여 상태를 REJECTED로 변경")
    public ApiResponse<?> rejectParticipation(@PathVariable Long participationId) {
        participationService.updateStatus(participationId, SegmentParticipationStatus.REJECTED);
        return ApiResponse.onSuccess();
    }

    @PostMapping("/courses/{courseId}/register")
    @Operation(
            summary = "여행 전체에 동행 신청하기 api",
            description = "여행 코스 전체에 동행을 신청하는 api입니다."
    )
    public ApiResponse<?> registerEntireSegment (
            @Parameter(hidden = true) @JwtUser Long memberId,
            @PathVariable Long courseId
    ) {
        participationService.registerEntireSegment(memberId, courseId);
        return ApiResponse.onSuccess();
    }


}
