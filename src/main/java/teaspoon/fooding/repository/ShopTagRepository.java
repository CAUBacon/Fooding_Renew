package teaspoon.fooding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teaspoon.fooding.domain.tag.ShopTag;

public interface ShopTagRepository extends JpaRepository<ShopTag, Long> {
}
