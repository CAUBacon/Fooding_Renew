package teaspoon.fooding.domain.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Category extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;
}
