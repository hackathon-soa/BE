package hackathon.soa.domain.home.dto;

import hackathon.soa.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class HomeResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyCourseResponseDTO {
        private LocalDate startDate;
        private LocalDate endDate;
        private String region;
        private String gender;
        private String userName;
        private String disabilityType;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyCoursesResponseDTO {
        private List<MyCourseResponseDTO> myCourses;
    }
}
