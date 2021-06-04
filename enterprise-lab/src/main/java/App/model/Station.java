package App.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Station {
    private List<Train> trainsOnStation = new ArrayList<>();
    private int id;
    private int km;
    private String name;
    public Station() {
        id = -1;
        km = 0;
        name = "-";
    }
    public Station(int id) {
        this.id = id;
        km = 0;
        name = "-";
    }
    public Station (int id, String name, int km) {
        this.id = id;
        this.name = name;
        this.km = km;
    }
}
