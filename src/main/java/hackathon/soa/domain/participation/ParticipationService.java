package hackathon.soa.domain.participation;

import hackathon.soa.common.apiPayload.code.status.ErrorStatus;
import hackathon.soa.common.apiPayload.exception.AuthHandler;
import hackathon.soa.common.apiPayload.exception.GeneralException;
import hackathon.soa.domain.course.repository.CourseRepository;
import hackathon.soa.domain.member.MemberRepository;
import hackathon.soa.domain.participation.dto.ParticipationResponseDTO;
import hackathon.soa.domain.segment.repository.CourseSegmentRepository;
import hackathon.soa.domain.segment.repository.StaySegmentRepository;
import hackathon.soa.entity.*;
import hackathon.soa.entity.enums.SegmentParticipationStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipationService {

    private final MemberRepository memberRepository;
    private final SegmentParticipationRepository segmentParticipationRepository;
    private final StaySegmentRepository staySegmentRepository;
    private final CourseSegmentRepository courseSegmentRepository;
    private final CourseRepository courseRepository;

    public void registerStaySegment(Long memberId, Long segmentId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new AuthHandler(ErrorStatus.MEMBER_NOT_FOUND));

        StaySegment segment = staySegmentRepository.findById(segmentId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.SEGMENT_NOT_FOUND)); // ❗ ErrorStatus 통일

        SegmentParticipation participation = ParticipationConverter.toSegmentParticipation(
                member, segment, SegmentParticipationStatus.PENDING
        );
        segmentParticipationRepository.save(participation);
    }

    public void registerEntireSegment(Long memberId, Long courseId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new AuthHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.NOT_FOUND_COURSE));

        List<CourseSegment> courseSegments = courseSegmentRepository.findAllByCourse(course);

        for (CourseSegment cs : courseSegments) {
            staySegmentRepository.findByCourseSegmentId(cs.getId()).ifPresent(staySegment -> {
                SegmentParticipation participation = ParticipationConverter.toSegmentParticipation(
                        member, staySegment, SegmentParticipationStatus.PENDING
                );
                segmentParticipationRepository.save(participation);
            });
        }
    }

    public void updateStatus(Long participationId, SegmentParticipationStatus status) {
        SegmentParticipation participation = segmentParticipationRepository.findById(participationId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.SEGMENT_NOT_FOUND)); // ❗ 더 구체적인 에러로 교체

        participation.updateStatus(status);
    }

    public ParticipationResponseDTO.ApplicantsResponsesDTO getApplicants (Long segmentId) {
        StaySegment segment = staySegmentRepository.findById(segmentId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.NOT_FOUND_SEGMENT));

        List<SegmentParticipation> segmentParticipations = segmentParticipationRepository.findAllByStaySegment(segment);

        if(segmentParticipations.isEmpty()) {
            throw new GeneralException(ErrorStatus.NOT_FOUND_SEGMENT_APPLICANTS);
        }

        List<ParticipationResponseDTO.ApplicantsResponseDTO> dtos = segmentParticipations.stream()
                .map(applicants -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate birthDate = LocalDate.parse(applicants.getMember().getBirth(), formatter);

                    Integer age = Period.between(birthDate, LocalDate.now()).getYears();

                    return ParticipationConverter.toApplicantsResponseDTO(applicants.getMember(), age);
                })
                .collect(Collectors.toList());

        return ParticipationConverter.toApplicantsResponsesDTO(dtos);
    }

}
