package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teaspoon.fooding.domain.tag.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
