package teaspoon.fooding.domain.shop;

import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.school.Position;

import javax.persistence.*;

@NoArgsConstructor
@Embeddable
public class Address {

    @Column(nullable = false)
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    public Address(String address, Position position) {
        this.address = address;
        this.position = position;
    }
}
