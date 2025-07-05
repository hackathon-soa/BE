package hackathon.soa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "title", columnDefinition = "varchar(100)")
    private String title;

    @Column(nullable = false, name = "region", columnDefinition = "varchar(100)")
    private String region;

    @Column(nullable = false, name = "intersts", columnDefinition = "text")
    private String interests;

    @Column(nullable = false, name = "special_note", columnDefinition = "text")
    private String specialNote;

    @Column(nullable = false, name = "preferred_gender", columnDefinition = "varchar(100)")
    @Enumerated(EnumType.STRING)
    private Gender preferredGender;

    @Column(nullable = false, name = "status", columnDefinition = "varchar(100)")
    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseTravelStyle> courseTravelStyle = new ArrayList<>();

    @Column(nullable = false, name = "start_time", columnDefinition = "datetime")
    private LocalDateTime startTime;

    @Column(nullable = false, name = "end_time", columnDefinition = "datetime")
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

}
