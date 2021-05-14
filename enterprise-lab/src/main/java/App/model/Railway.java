package App.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Railway {
    /**
     * K - км, на котором находится станция
     * V - Станция
     */
    private Map<Integer, Station> stations = new HashMap<>();
}