package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import teaspoon.fooding.domain.user.LocalUser;

import java.util.Optional;

public interface LocalUserRepository extends JpaRepository<LocalUser, Long> {
    @EntityGraph(attributePaths = {"roles"})
    Optional<LocalUser> findByEmail(String email);

    boolean existsByEmail(String email);

}
