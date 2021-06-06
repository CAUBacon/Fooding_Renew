package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teaspoon.fooding.domain.school.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
