package teaspoon.fooding.domain.shop;

import lombok.NoArgsConstructor;
import teaspoon.fooding.domain.school.Position;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@NoArgsConstructor
@Embeddable
public class Address {

    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    public Address(String address, Position position) {
        this.address = address;
        this.position = position;
    }
}
