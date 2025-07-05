package hackathon.soa.domain.search.dto;

import hackathon.soa.domain.home.dto.HomeResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class SearchResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchCourseResponseDTO {
        private LocalDate startDate;
        private LocalDate endDate;
        private String region;
        private String gender;
        private String userName;
        private String disabilityType;
        private boolean liked;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchCoursesResponseDTO {
        private List<SearchResponseDTO.SearchCourseResponseDTO> foundCourses;
    }
}
