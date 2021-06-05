package teaspoon.fooding.domain.school;

import lombok.Builder;
import lombok.Getter;
import teaspoon.fooding.domain.BaseEntity;
import teaspoon.fooding.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Entity
public class Position extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "position_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    private String name;

    @Builder
    public Position(School school, String name) {
        this.school = school;
        this.name = name;
    }
}
