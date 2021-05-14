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
    public Station (int id) {
        this.id = id;
    }
}
