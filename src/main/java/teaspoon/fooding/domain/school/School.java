package teaspoon.fooding.domain.school;

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
public class School extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "school_id")
    private Long id;

    private String name;
}
