package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teaspoon.fooding.domain.menu.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
