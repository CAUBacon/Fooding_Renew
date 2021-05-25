package teaspoon.fooding.domain.shop;

import teaspoon.fooding.domain.school.Position;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class Address {

    private final String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private final Position position;

    public Address(String address, Position position) {
        this.address = address;
        this.position = position;
    }
}
