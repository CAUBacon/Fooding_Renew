package teaspoon.fooding.domain.image;

import lombok.Getter;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.user.User;

import javax.persistence.*;

@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "image_type")
@Entity
public abstract class Image extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    private String imageLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id")
    private User uploader;
}
