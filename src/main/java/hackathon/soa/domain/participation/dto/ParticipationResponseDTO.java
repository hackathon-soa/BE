package hackathon.soa.domain.participation.dto;

import hackathon.soa.domain.search.dto.SearchResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class ParticipationResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApplicantsResponseDTO {
        private String nickname;
        private String disabilityType;
        private Double temperature;
        private String phoneNumber;
        private String gender;
        private Integer age;
        private Integer volunteerHours;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApplicantsResponsesDTO {
        private List<ParticipationResponseDTO.ApplicantsResponseDTO> applicants;
    }
}
