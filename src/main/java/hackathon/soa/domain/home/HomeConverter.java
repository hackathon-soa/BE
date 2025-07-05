package hackathon.soa.domain.home;

import hackathon.soa.domain.home.dto.HomeResponseDTO;
import hackathon.soa.entity.Course;

import java.util.List;
import java.util.stream.Collectors;

public class HomeConverter {

    public static HomeResponseDTO.MyCoursesResponseDTO toMyCoursesResponseDTO(List<Course> courses) {
        return HomeResponseDTO.MyCoursesResponseDTO.builder()
                .myCourses(
                        courses.stream()
                                .map(HomeConverter::toMyCourseResponseDTO)
                                .collect(Collectors.toList())
                )
                .build();

    }

    public static HomeResponseDTO.MyCourseResponseDTO toMyCourseResponseDTO(Course course) {
        return HomeResponseDTO.MyCourseResponseDTO.builder()
                .startDate(course.getStartTime().toLocalDate())
                .endDate(course.getEndTime().toLocalDate())
                .region(course.getRegion())
                .gender(course.getPreferredGender().toString())
                .userName(course.getMember().getNickname())
                .disabilityType(course.getMember().getDisabilityType())
                .build();
    }
}
