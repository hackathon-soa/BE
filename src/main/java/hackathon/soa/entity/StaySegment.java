package hackathon.soa.entity;

import hackathon.soa.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stay_segment")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class StaySegment extends BaseEntity {

    @Id
    private Long segmentId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "segment_id")
    private CourseSegment courseSegment;

    @Column(nullable = false, name = "location_name", columnDefinition = "varchar(100)")
    private String locationName;

    @Column(nullable = false, name = "location_address", columnDefinition = "varchar(255)")
    private String locationAddress;

    @OneToMany(mappedBy = "staySegment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SegmentParticipation> segmentParticipations = new ArrayList<>();
}
