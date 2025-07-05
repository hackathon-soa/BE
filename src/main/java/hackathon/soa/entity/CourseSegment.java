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
@Table(name = "course_segment")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class CourseSegment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false, name = "segment_order", columnDefinition = "int")
    private int segmentOrder;

    @Column(nullable = false, name = "start_time", columnDefinition = "datetime")
    private LocalDateTime startTime;

    @Column(nullable = false, name = "end_time", columnDefinition = "datetime")
    private LocalDateTime endTime;

}
