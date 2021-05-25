package teaspoon.fooding.domain.user;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class UserFollow {

    @Id @GeneratedValue
    @Column(name = "user_follow_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser;

}
