package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import teaspoon.fooding.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findUserWithRolesById(Long id);

    List<User> findByNickname(String nickname);

    boolean existsByNickname(String nickname);
}
