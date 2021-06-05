package teaspoon.fooding.domain.image;

import lombok.Getter;
import teaspoon.fooding.domain.menu.Menu;

import javax.persistence.*;

@Getter
@Entity
public class MenuImage extends Image{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;
}
