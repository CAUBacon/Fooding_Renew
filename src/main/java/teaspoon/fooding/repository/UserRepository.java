package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teaspoon.fooding.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
