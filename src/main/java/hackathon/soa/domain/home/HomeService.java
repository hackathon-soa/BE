package hackathon.soa.domain.home;

import hackathon.soa.common.apiPayload.code.status.ErrorStatus;
import hackathon.soa.common.apiPayload.exception.GeneralException;
import hackathon.soa.domain.auth.course.CourseRepository;
import hackathon.soa.domain.home.dto.HomeResponseDTO;
import hackathon.soa.entity.Course;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeService {
    private final CourseRepository courseRepository;

    public HomeResponseDTO.MyCoursesResponseDTO getMyCourses(Long memberId) {
        List<Course> courses = courseRepository.findAllByMemberId(memberId);

        if (courses.isEmpty()) {
            throw new GeneralException(ErrorStatus.NOT_FOUND_COURSE);
        }

        List<Course> sortedCourses = courses.stream()
                .sorted((c1, c2) -> {
                        int likes1 = courseRepository.countLikesByCourseId(c1.getId());
                        int likes2 = courseRepository.countLikesByCourseId(c2.getId());

                        if (likes1 != likes2) {
                            return Integer.compare(likes2, likes1);
                        } else {
                            return c2.getCreatedAt().compareTo(c1.getCreatedAt());
                }})
                .collect(Collectors.toList());

        return HomeConverter.toMyCoursesResponseDTO(sortedCourses);

    }

    public HomeResponseDTO.MyCoursesResponseDTO getMyTwoCourses(Long memberId) {
        List<Course> courses = courseRepository.findAllByMemberId(memberId);

        if (courses.isEmpty()) {
            throw new GeneralException(ErrorStatus.NOT_FOUND_COURSE);
        }

        List<Course> sortedCourses = courses.stream()
                .sorted((c1, c2) -> {
                    int likes1 = courseRepository.countLikesByCourseId(c1.getId());
                    int likes2 = courseRepository.countLikesByCourseId(c2.getId());

                    if (likes1 != likes2) {
                        return Integer.compare(likes2, likes1);
                    } else {
                        return c2.getCreatedAt().compareTo(c1.getCreatedAt());
                    }})
                .limit(2)
                .collect(Collectors.toList());

        return HomeConverter.toMyCoursesResponseDTO(sortedCourses);

    }
}
