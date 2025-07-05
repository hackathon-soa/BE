package hackathon.soa.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Auditable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "app_id", unique = true, columnDefinition = "varchar(100)")
    private String appId;

    @Column(nullable = false, name = "password", columnDefinition = "varchar(100)")
    private String password;

    @Column(nullable = false, name = "name", columnDefinition = "varchar(100)")
    private String name;

    @Column(nullable = false, name = "nickname", columnDefinition = "varchar(100)")
    private String nickname;

    @Column(nullable = false, name = "phone_number", columnDefinition = "varchar(100)")
    private String phoneNumber;

    @Column(nullable = false, name = "birth", columnDefinition = "date")
    private LocalDate birth;

    @Column(nullable = false, name = "gender", columnDefinition = "varchar(100)")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, name = "address", columnDefinition = "varchar(100)")
    private String address;

    @Column(nullable = false, name = "disability_type", columnDefinition = "varchar(100)")
    private String disabilityType;

    @Column(nullable = false, name = "disability", columnDefinition = "tinyint")
    private Boolean disability;

    @Column(nullable = false, name = "temperature", columnDefinition = "double")
    @Builder.Default
    private Double temperature = 36.5;

    @Column(nullable = false, name = "profile_imgage_url", columnDefinition = "varchar(255)")
    private String profileImageUrl;

    @Column(nullable = false, name = "mileage", columnDefinition = "int")
    private Integer mileage;

    @Column(nullable = false, name = "is_verified", columnDefinition = "tinyint")
    @Builder.Default
    private Boolean isVerified = false;

    @OneToMany(mappedBy = "evaluatorMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemperatureMapping> temperaturesGiven = new ArrayList<>();

    @OneToMany(mappedBy = "targetMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemperatureMapping> temperaturesReceived = new ArrayList<>();
}
