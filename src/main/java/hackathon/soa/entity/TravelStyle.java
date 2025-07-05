package hackathon.soa.entity;

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
    @GeneratedValue
    private Long id;

    @Column(nullable = false, name = "name", columnDefinition = "varchar(1001-")
    private String name;

    @OneToMany(mappedBy = "travelStyle", cascade = CascadeType.ALL)
    private List<CourseTravelStyle> courseTravelStyles = new ArrayList<>();
}
