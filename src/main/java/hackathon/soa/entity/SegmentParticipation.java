package hackathon.soa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "segment_participation")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class SegmentParticipation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stay_segment_id")
    private StaySegment staySegment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "course_segment_status", columnDefinition = "varchar(100)")
    private SegmentParticipationStatus status;

}
