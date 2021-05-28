package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teaspoon.fooding.domain.user.LocalUser;

public interface LocalUserRepository extends JpaRepository<LocalUser, Long> {
}
