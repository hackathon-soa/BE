package hackathon.soa.domain.segment.dto;

import hackathon.soa.entity.MoveSegment;
import hackathon.soa.entity.MovementType;
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
        private Boolean isOwner; // 해당 멤버가 코스 작성자인지 여부
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

        public static StaySegmentDTO from(StaySegment staySegment) {
            return StaySegmentDTO.builder()
                    .segmentId(staySegment.getSegmentId())
                    .locationName(staySegment.getLocationName())
                    .locationAddress(staySegment.getLocationAddress())
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
}