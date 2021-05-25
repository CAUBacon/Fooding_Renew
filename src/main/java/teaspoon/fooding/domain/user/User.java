package teaspoon.fooding.domain.user;

import lombok.Getter;
import teaspoon.fooding.domain.BaseTimeEntity;
import teaspoon.fooding.domain.image.Image;
import teaspoon.fooding.domain.review.Review;

import javax.persistence.*;
import java.util.List;

@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
@Entity
public abstract class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserRole role;

//    @OneToMany(mappedBy = "uploader")
//    private List<Image> images;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fromUser")
    private List<UserFollow> followees;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toUser")
    private List<UserFollow> followers;

//    @OneToMany(mappedBy = "author")
//    private List<Review> reviews;


}
