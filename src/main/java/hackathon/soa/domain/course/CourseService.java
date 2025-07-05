package hackathon.soa.domain.course;

import hackathon.soa.common.apiPayload.code.status.ErrorStatus;
import hackathon.soa.common.apiPayload.exception.CourseHandler;
import hackathon.soa.domain.course.dto.CourseRequestDTO;
import hackathon.soa.domain.course.repository.*;
import hackathon.soa.domain.member.MemberRepository;
import hackathon.soa.domain.segment.repository.CourseSegmentRepository;
import hackathon.soa.domain.segment.repository.MoveSegmentRepository;
import hackathon.soa.domain.segment.repository.StaySegmentRepository;
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
    public Long createCourse(CourseRequestDTO.CreateCourseRequest request, Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new CourseHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // 1. Course 생성
        Course course = CourseConverter.toCourseEntity(request, member);
        courseRepository.save(course);

        // 2. 여행 테마 저장
        for (String themeName : request.getTravelThemes()) {
            TravelStyle travelStyle = travelStyleRepository.findByName(themeName)
                    .orElseGet(() -> travelStyleRepository.save(
                            TravelStyle.builder().name(themeName).build()
                    ));

            courseTravelStyleRepository.save(
                    CourseConverter.toCourseTravelStyle(course, travelStyle)
            );
        }

        // 3. Segment 저장
        int order = 0;
        for (CourseRequestDTO.SegmentDTO dto : request.getSegments()) {
            CourseSegment segment = CourseConverter.toCourseSegment(course, dto, order++);
            courseSegmentRepository.save(segment);

            if (dto.isMoving()) {
                moveSegmentRepository.save(
                        CourseConverter.toMoveSegment(segment, dto.getMovementType(), dto.getMovementDistanceKm())
                );
            } else {
                staySegmentRepository.save(
                        CourseConverter.toStaySegment(segment, dto.getLocationName(), dto.getLocationAddress())
                );
            }
        }

        return course.getId();
    }

    private final CoursePlaceSearchService coursePlaceSearchService;

    public List<String> searchPlace(String query) {
        return coursePlaceSearchService.search(query);
    }
}
