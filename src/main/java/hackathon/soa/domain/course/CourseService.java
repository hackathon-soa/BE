package hackathon.soa.domain.course;

import hackathon.soa.domain.course.dto.CourseRequestDTO;
import hackathon.soa.domain.course.repository.*;
import hackathon.soa.domain.member.MemberRepository;
import hackathon.soa.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TravelStyleRepository travelStyleRepository;
    private final CourseSegmentRepository courseSegmentRepository;
    private final MoveSegmentRepository moveSegmentRepository;
    private final StaySegmentRepository staySegmentRepository;
    private final CourseTravelStyleRepository courseTravelStyleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createCourse(CourseRequestDTO.CreateCourseRequest request,Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다: " + userId));

        // 1. Course 생성
        Course course = Course.builder()
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
        courseRepository.save(course);

        // 2. 여행 테마 저장
        for (String themeName : request.getTravelThemes()) {
            TravelStyle travelStyle = travelStyleRepository.findByName(themeName)
                    .orElseGet(() -> travelStyleRepository.save(
                            TravelStyle.builder().name(themeName).build()
                    ));

            CourseTravelStyle courseTravelStyle = CourseTravelStyle.builder()
                    .course(course)
                    .travelStyle(travelStyle)
                    .build();

            courseTravelStyleRepository.save(courseTravelStyle);
        }

        // 3. Segment 저장
        int order = 0;
        for (CourseRequestDTO.SegmentDTO dto : request.getSegments()) {
            CourseSegment segment = CourseSegment.builder()
                    .course(course)
                    .segmentOrder(order++)
                    .startTime(dto.getStartTime())
                    .endTime(dto.getEndTime())
                    .build();
            courseSegmentRepository.save(segment);

            if (dto.isMoving()) {
                MoveSegment move = MoveSegment.builder()
                        .courseSegment(segment)
                        .movementType(MovementType.valueOf(dto.getMovementType()))
                        .movementDistanceKm(dto.getMovementDistanceKm())
                        .build();
                moveSegmentRepository.save(move);
            } else {
                StaySegment stay = StaySegment.builder()
                        .courseSegment(segment)
                        .locationName(dto.getLocationName())
                        .locationAddress(dto.getLocationAddress())
                        .build();
                staySegmentRepository.save(stay);
            }
        }

        return course.getId();
    }

    private final CoursePlaceSearchService coursePlaceSearchService;

    public List<String> searchPlace(String query) {
        return coursePlaceSearchService.search(query);
    }
}
