package hackathon.soa.domain.course;

import hackathon.soa.common.apiPayload.code.status.ErrorStatus;
import hackathon.soa.common.apiPayload.exception.CourseHandler;
import hackathon.soa.domain.course.dto.CourseRequestDTO;
import hackathon.soa.entity.*;
import hackathon.soa.entity.enums.CourseStatus;
import hackathon.soa.entity.enums.MovementType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CourseConverter {

    public Course toCourseEntity(CourseRequestDTO.CreateCourseRequest request, Member member) {
        return Course.builder()
                .title(request.getTitle())
                .region(request.getRegion())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .interests(request.getInterests())
                .specialNote(request.getSpecialNote())
                .preferredGender(request.getPreferredGender())
                .status(CourseStatus.IN_PROGRESS)
                .member(member)
                .build();
    }

    public CourseSegment toCourseSegment(Course course, CourseRequestDTO.SegmentDTO dto, int order) {
        return CourseSegment.builder()
                .course(course)
                .segmentOrder(order)
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .build();
    }

    public MoveSegment toMoveSegment(CourseSegment segment, String movementTypeStr, Double distance) {
        MovementType movementType;
        try {
            movementType = MovementType.valueOf(movementTypeStr);
        } catch (IllegalArgumentException e) {
            throw new CourseHandler(ErrorStatus.SEGMENT_DESERIALIZATION_ERROR);
        }

        return MoveSegment.builder()
                .courseSegment(segment)
                .movementType(movementType)
                .movementDistanceKm(distance)
                .build();
    }

    public StaySegment toStaySegment(CourseSegment segment, String name, String address) {
        return StaySegment.builder()
                .courseSegment(segment)
                .locationName(name)
                .locationAddress(address)
                .build();
    }

    public CourseTravelStyle toCourseTravelStyle(Course course, TravelStyle travelStyle) {
        return CourseTravelStyle.builder()
                .course(course)
                .travelStyle(travelStyle)
                .build();
    }
}
