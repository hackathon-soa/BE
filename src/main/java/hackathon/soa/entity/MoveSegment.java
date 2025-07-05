package hackathon.soa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Entity
@Table(name = "move_segment")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class MoveSegment extends BaseEntity {

    @Id
    private Long segmentId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "segment_id")
    private CourseSegment courseSegment;

    @Column(nullable = false, name = "movement_type", columnDefinition = "varchar(100)")
    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    @Column(nullable = false, name = "movement_distance_km", columnDefinition = "double")
    private double movementDistanceKm;
}
