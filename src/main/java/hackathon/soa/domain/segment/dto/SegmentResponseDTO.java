package hackathon.soa.domain.segment.dto;

import hackathon.soa.domain.search.dto.SearchResponseDTO;
import hackathon.soa.entity.MoveSegment;
import hackathon.soa.entity.enums.MovementType;
import hackathon.soa.entity.StaySegment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

public class SegmentResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseDetailResponseDTO {
        private Long courseId;
        private Boolean isMine; // 해당 멤버가 코스 작성자인지 여부
        private Boolean canApplyAll; // 전체 신청 가능 여부 (모든 장소 세그먼트에 참여하지 않았는지)
        private SearchResponseDTO.SearchCourseResponseDTO courseInfo; // 코스 기본 정보 및 좋아요 정보
        private List<SegmentDetailDTO> segments;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SegmentDetailDTO {
        private Integer segmentOrder;
        private String segmentType; // "장소" or "이동"
        private String date; // "07/05(토)"
        private String startTime; // "09:00"
        private String endTime; // "09:30"
        private StaySegmentDTO staySegment;
        private MoveSegmentDTO moveSegment;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StaySegmentDTO {
        private Long segmentId;
        private String locationName;
        private String locationAddress;
        private Boolean isParticipated; // 현재 사용자의 참여 여부

        public static StaySegmentDTO from(StaySegment staySegment, Boolean isParticipated) {
            return StaySegmentDTO.builder()
                    .segmentId(staySegment.getSegmentId())
                    .locationName(staySegment.getLocationName())
                    .locationAddress(staySegment.getLocationAddress())
                    .isParticipated(isParticipated)
                    .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MoveSegmentDTO {
        private Long segmentId;
        private MovementType movementType;
        private BigDecimal movementDistanceKm;

        public static MoveSegmentDTO from(MoveSegment moveSegment) {
            return MoveSegmentDTO.builder()
                    .segmentId(moveSegment.getSegmentId())
                    .movementType(moveSegment.getMovementType())
                    .movementDistanceKm(BigDecimal.valueOf(moveSegment.getMovementDistanceKm()))
                    .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyCourseDetailResponseDTO {
        private Long courseId;
        private List<MySegmentDetailDTO> segments;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MySegmentDetailDTO {
        private Integer segmentOrder;
        private String segmentType; // "장소" or "이동"
        private String date; // "07/05(토)"
        private String startTime; // "09:00"
        private String endTime; // "09:30"
        private MyStaySegmentDTO staySegment;
        private MoveSegmentDTO moveSegment;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyStaySegmentDTO {
        private Long segmentId;
        private String locationName;
        private String locationAddress;
        private String mateStatus; // "모집 중", "신청확인", "신청확정"
    }
}