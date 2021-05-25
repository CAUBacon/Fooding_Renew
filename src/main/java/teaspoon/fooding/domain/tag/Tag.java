package teaspoon.fooding.domain.tag;

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
public class Tag extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    private String name;
}
