package App.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Table {
    /**
     * K - id Поезда
     * V - Маршрут
     */
    private Map<Integer, Route> routes = new HashMap();

    public Route getRouteByTrainId(int trainId) {
        Route tmp  = routes.get(trainId);
        return new Route (trainId);
    }

    public void addRoute (int trainId, Route route) {
        /*
         1. Проверить M, выдать ошибку, если привышаем
         2. Если меньше M, добавляем
         */
    }
    public void removeRoute (int trainId) {
        routes.remove(trainId);
    }

}
