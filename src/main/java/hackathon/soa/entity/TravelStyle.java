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
@Table(name = "travel_style")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class TravelStyle extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name", columnDefinition = "varchar(100)")
    private String name;

    @OneToMany(mappedBy = "travelStyle", cascade = CascadeType.ALL)
    private List<CourseTravelStyle> courseTravelStyles = new ArrayList<>();
}
