package hackathon.soa.domain.course.dto;

import hackathon.soa.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class CourseRequestDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateCourseRequest {
        private String title;
        private String region;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private String interests;
        private String specialNote;
        private Gender preferredGender;
        private List<String> travelThemes; // ex: ["자연", "쇼핑"]
        private List<SegmentDTO> segments;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SegmentDTO {
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private boolean isMoving;
        private String movementType;       // 자동차, 도보 등 (이동일 경우)
        private double movementDistanceKm; // 이동일 경우
        private String locationName;       // 장소일 경우
        private String locationAddress;    // 장소일 경우
    }

}
