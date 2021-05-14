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
    public Route (int id) {
        this.id = id;
    }
}