package hackathon.soa.domain.search;

import hackathon.soa.common.apiPayload.code.status.ErrorStatus;
import hackathon.soa.common.apiPayload.exception.GeneralException;
import hackathon.soa.domain.course.CourseRepository;
import hackathon.soa.domain.likes.LikesRepository;
import hackathon.soa.domain.member.MemberRepository;
import hackathon.soa.domain.search.dto.SearchResponseDTO;
import hackathon.soa.entity.Course;
import hackathon.soa.entity.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchService {
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;
    private final LikesRepository likesRepository;

    public SearchResponseDTO.SearchCoursesResponseDTO getCoursesByRegion(Long memberId, String region) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        List<Course> courses = courseRepository.findAllByRegion(region);

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

        List<SearchResponseDTO.SearchCourseResponseDTO> dtos = sortedCourses.stream()
                .map(course -> {
                    boolean liked = likesRepository.existsByCourseAndMember(course, member);
                    return SearchConverter.toSearchCourseResponseDTO(course, liked);
                })
                .collect(Collectors.toList());

        return SearchConverter.toSearchCoursesResponseDTO(dtos);
    }

    public SearchResponseDTO.SearchCoursesResponseDTO getCoursesByDate(Long memberId, String date) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        LocalDate targetDate = LocalDate.parse(date);
        LocalDateTime targetDateTime = targetDate.atStartOfDay();

        List<Course> courses = courseRepository.findAllByDateBetween(targetDateTime);

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

        List<SearchResponseDTO.SearchCourseResponseDTO> dtos = sortedCourses.stream()
                .map(course -> {
                    boolean liked = likesRepository.existsByCourseAndMember(course, member);
                    return SearchConverter.toSearchCourseResponseDTO(course, liked);
                })
                .collect(Collectors.toList());

        return SearchConverter.toSearchCoursesResponseDTO(dtos);
    }
}
