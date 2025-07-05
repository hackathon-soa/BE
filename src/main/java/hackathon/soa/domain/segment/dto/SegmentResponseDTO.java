package hackathon.soa.domain.segment.dto;

import hackathon.soa.domain.search.dto.SearchResponseDTO;
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
        private Boolean isMine; // 해당 멤버가 코스 작성자인지 여부
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
        private String date; // "07/05(토)" 형식
        private String startTime; // "09:00" 형식
        private String endTime; // "09:30" 형식
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
}