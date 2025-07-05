package hackathon.soa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "story")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Story extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "image_url", columnDefinition = "varchar(255)")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}

