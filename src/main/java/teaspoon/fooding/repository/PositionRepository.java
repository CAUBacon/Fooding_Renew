package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teaspoon.fooding.domain.school.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
