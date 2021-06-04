
package App.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Route {
    private List<Station> stations = new ArrayList<>();
    private int id;
    private String name;

    public Route () {
        id = -1;
        name = "-";
    }
    public Route (int id, String name) {
        this.id = id;
        this.name = name;
    }
}