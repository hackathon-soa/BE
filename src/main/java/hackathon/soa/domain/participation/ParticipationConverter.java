package hackathon.soa.domain.participation;

import hackathon.soa.domain.participation.dto.ParticipationResponseDTO;
import hackathon.soa.entity.Member;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ParticipationConverter {
    public static ParticipationResponseDTO.ApplicantsResponseDTO toApplicantsResponseDTO(Member member, Integer age) {
        return ParticipationResponseDTO.ApplicantsResponseDTO.builder()
                .nickname(member.getNickname())
                .disabilityType(member.getDisabilityType())
                .temperature(member.getTemperature())
                .phoneNumber(member.getPhoneNumber())
                .gender(member.getGender().toString())
                .age(age)
                .volunteerHours(member.getVolunteerHours())
                .build();
    }

    public static ParticipationResponseDTO.ApplicantsResponsesDTO toApplicantsResponsesDTO(List<ParticipationResponseDTO.ApplicantsResponseDTO> dtos) {
        return ParticipationResponseDTO.ApplicantsResponsesDTO.builder()
                .applicants(dtos)
                .build();
    }
}
