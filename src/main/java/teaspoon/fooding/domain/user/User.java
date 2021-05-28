package teaspoon.fooding.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import teaspoon.fooding.domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
@Entity
public abstract class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<UserRole> roles = new ArrayList<>();

//    @OneToMany(mappedBy = "uploader")
//    private List<Image> images;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fromUser")
    private List<UserFollow> followees;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toUser")
    private List<UserFollow> followers;

    public User(String nickname, Gender gender) {
        this.nickname = nickname;
        this.gender = gender;
    }

    public void assignRoles(UserRole...roles) {
        this.roles.addAll(Arrays.asList(roles));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles().stream()
                .map(UserRole::getRole)
                .map(SimpleGrantedAuthority::new)
                .collect(toList());
    }

    @Override
    public String getUsername() {
        return id.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @OneToMany(mappedBy = "author")
//    private List<Review> reviews;


}
