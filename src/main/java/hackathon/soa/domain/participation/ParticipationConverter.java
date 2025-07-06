package hackathon.soa.domain.participation;

import hackathon.soa.domain.participation.dto.ParticipationResponseDTO;
import hackathon.soa.entity.Member;
import hackathon.soa.entity.SegmentParticipation;
import hackathon.soa.entity.StaySegment;
import hackathon.soa.entity.enums.SegmentParticipationStatus;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ParticipationConverter {

    public SegmentParticipation toSegmentParticipation(Member member, StaySegment segment, SegmentParticipationStatus status) {
        return SegmentParticipation.builder()
                .member(member)
                .staySegment(segment)
                .status(status)
                .build();
    }

    public ParticipationResponseDTO.ApplicantsResponseDTO toApplicantsResponseDTO(Member member, Integer age) {
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

    public ParticipationResponseDTO.ApplicantsResponsesDTO toApplicantsResponsesDTO(List<ParticipationResponseDTO.ApplicantsResponseDTO> dtos) {
        return ParticipationResponseDTO.ApplicantsResponsesDTO.builder()
                .applicants(dtos)
                .build();
    }
}

