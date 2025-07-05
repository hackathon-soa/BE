package hackathon.soa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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


}
