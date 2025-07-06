package hackathon.soa.entity;

import hackathon.soa.entity.common.BaseEntity;
import hackathon.soa.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String birth;

    @Column(nullable = false, name = "gender", columnDefinition = "varchar(100)")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, name = "disability_type", columnDefinition = "varchar(100)")
    private String disabilityType;

    @Column(nullable = false, name = "temperature", columnDefinition = "double")
    @Builder.Default
    private Double temperature = 36.5;

    @Column(nullable = false, name = "profile_image_url", columnDefinition = "varchar(255)")
    private String profileImageUrl;

    @Column(nullable = true, name = "verification_image_url", columnDefinition = "varchar(255)")
    private String verificationImageUrl;

    @Column(nullable = true, name = "volunteer_hours", columnDefinition = "int")
    @Builder.Default
    private Integer volunteerHours = 0;

    @Column(nullable = false, name = "mileage", columnDefinition = "int")
    @Builder.Default
    private Integer mileage = 0;

    @Column(nullable = false, name = "is_verified", columnDefinition = "tinyint")
    @Builder.Default
    private Boolean isVerified = false;

    @OneToMany(mappedBy = "evaluatorMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemperatureMapping> temperaturesGiven = new ArrayList<>();

    @OneToMany(mappedBy = "targetMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemperatureMapping> temperaturesReceived = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SegmentParticipation> segmentParticipations = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Story> stories = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

    public void updateVerificationImageUrl(String verificationImageUrl) {
        this.verificationImageUrl = verificationImageUrl;
    }
}
