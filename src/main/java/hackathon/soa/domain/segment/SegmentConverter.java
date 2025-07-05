package hackathon.soa.domain.segment;

import hackathon.soa.domain.search.dto.SearchResponseDTO;
import hackathon.soa.domain.segment.dto.SegmentResponseDTO;
import hackathon.soa.entity.CourseSegment;
import hackathon.soa.entity.MoveSegment;
import hackathon.soa.entity.StaySegment;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class SegmentConverter {

    public static SegmentResponseDTO.CourseDetailResponseDTO toCourseDetailResponseDTO(
            Long courseId, Boolean isOwner,
            SearchResponseDTO.SearchCourseResponseDTO courseInfo,
            List<SegmentResponseDTO.SegmentDetailDTO> segments) {
        return SegmentResponseDTO.CourseDetailResponseDTO.builder()
                .courseId(courseId)
                .isMine(isOwner)
                .courseInfo(courseInfo)
                .segments(segments)
                .build();
    }

    public static SegmentResponseDTO.SegmentDetailDTO toSegmentDetailDTO(
            CourseSegment courseSegment,
            StaySegment staySegment,
            MoveSegment moveSegment,
            Boolean isParticipated) {

        // 시간 포맷터 정의
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd(E)", Locale.KOREAN);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String segmentType = (staySegment != null) ? "장소" : "이동";

        return SegmentResponseDTO.SegmentDetailDTO.builder()
                .segmentOrder(courseSegment.getSegmentOrder())
                .segmentType(segmentType)
                .date(courseSegment.getStartTime().format(dateFormatter))
                .startTime(courseSegment.getStartTime().format(timeFormatter))
                .endTime(courseSegment.getEndTime().format(timeFormatter))
                .staySegment(staySegment != null ? toStaySegmentDTO(staySegment, isParticipated) : null)
                .moveSegment(moveSegment != null ? toMoveSegmentDTO(moveSegment) : null)
                .build();
    }

    public static SegmentResponseDTO.StaySegmentDTO toStaySegmentDTO(StaySegment staySegment, Boolean isParticipated) {
        return SegmentResponseDTO.StaySegmentDTO.builder()
                .segmentId(staySegment.getSegmentId())
                .locationName(staySegment.getLocationName())
                .locationAddress(staySegment.getLocationAddress())
                .isParticipated(isParticipated)
                .build();
    }

    public static SegmentResponseDTO.MoveSegmentDTO toMoveSegmentDTO(MoveSegment moveSegment) {
        return SegmentResponseDTO.MoveSegmentDTO.builder()
                .segmentId(moveSegment.getSegmentId())
                .movementType(moveSegment.getMovementType())
                .movementDistanceKm(BigDecimal.valueOf(moveSegment.getMovementDistanceKm()))
                .build();
    }


    public static SegmentResponseDTO.MyCourseDetailResponseDTO toMyCourseDetailResponseDTO(
            Long courseId,
            List<SegmentResponseDTO.MySegmentDetailDTO> segments) {
        return SegmentResponseDTO.MyCourseDetailResponseDTO.builder()
                .courseId(courseId)
                .segments(segments)
                .build();
    }

    public static SegmentResponseDTO.MySegmentDetailDTO toMySegmentDetailDTO(
            CourseSegment courseSegment,
            StaySegment staySegment,
            MoveSegment moveSegment,
            String mateStatus) {

        // 시간 포맷터 정의
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd(E)", Locale.KOREAN);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String segmentType = (staySegment != null) ? "장소" : "이동";

        return SegmentResponseDTO.MySegmentDetailDTO.builder()
                .segmentOrder(courseSegment.getSegmentOrder())
                .segmentType(segmentType)
                .date(courseSegment.getStartTime().format(dateFormatter))
                .startTime(courseSegment.getStartTime().format(timeFormatter))
                .endTime(courseSegment.getEndTime().format(timeFormatter))
                .staySegment(staySegment != null ? toMyStaySegmentDTO(staySegment, mateStatus) : null)
                .moveSegment(moveSegment != null ? toMoveSegmentDTO(moveSegment) : null)
                .build();
    }

    public static SegmentResponseDTO.MyStaySegmentDTO toMyStaySegmentDTO(StaySegment staySegment, String mateStatus) {
        return SegmentResponseDTO.MyStaySegmentDTO.builder()
                .segmentId(staySegment.getSegmentId())
                .locationName(staySegment.getLocationName())
                .locationAddress(staySegment.getLocationAddress())
                .mateStatus(mateStatus)
                .build();
    }
}