package hackathon.soa.domain.search;

import hackathon.soa.domain.likes.LikesRepository;
import hackathon.soa.domain.search.dto.SearchResponseDTO;
import hackathon.soa.entity.Course;
import hackathon.soa.entity.Member;

import java.util.List;
import java.util.stream.Collectors;

public class SearchConverter {

    private static LikesRepository likesRepository;

    public static SearchResponseDTO.SearchCoursesResponseDTO toSearchCoursesResponseDTO (List<SearchResponseDTO.SearchCourseResponseDTO> dtos) {
        return SearchResponseDTO.SearchCoursesResponseDTO.builder()
                .foundCourses(dtos)
                .build();
    }

    public static SearchResponseDTO.SearchCourseResponseDTO toSearchCourseResponseDTO(Course course, boolean liked) {
        return SearchResponseDTO.SearchCourseResponseDTO.builder()
                .startDate(course.getStartTime().toLocalDate())
                .endDate(course.getEndTime().toLocalDate())
                .region(course.getRegion())
                .gender(course.getPreferredGender().toString())
                .userName(course.getMember().getNickname())
                .disabilityType(course.getMember().getDisabilityType())
                .liked(liked)
                .build();
    }
}
