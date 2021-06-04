package App.service;

import App.model.Route;
import App.model.Station;
import App.model.Table1;
import App.model.Train;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TestService {
    public void print (String text) {
        System.out.println(text);
    }
    public void moveTrain (Train train, Station nextStation, Station pastStation) {
        print("Поезд " + train.getId() + " перешёл из " + pastStation.getId() + " в " + nextStation.getId());
        pastStation.getTrainsOnStation().remove(train);
        nextStation.getTrainsOnStation().add(train);
    }

    public Station createStation (int id, String name, int km) {
        print("Станция создана (" + id + ")");
        return new Station(id, name, km);
    }

    public Train createTrain (int id) {
        print("Поезд создан (" + id + ")");
        return new Train(id);
    }


    public void putTrainAtStation (Train train, Station station) {
        print("Поезд (" + train.getId() + ") добавлен на станцию (" + station.getId() + ")");
        station.getTrainsOnStation().add(train);
    }



    public Route getRoute (Route route, int roID) {
        print("Маршрут выбран");
        print("╔═╗");
        for (Station st : route.getStations()) {
            print("╠" + st.getId() + "╣");
        }
        print("╚═╝");
        return route;
    }
    public void createTableEl (Table1 table, Route route, Train train) {
        table.addRoute(train.getId(), route);
        print("Маршрут добавлен в расписание");
        print("╔═╗ \uD83D\uDE86[" + train.getId() + "]");
        for (Station st : route.getStations()) {
            print("╠" + st.getId() + "╣");
        }
        print("╚═╝");
    }
    public void printss (JdbcTemplate jdbcTemplate) {
        System.out.println(jdbcTemplate.getMaxRows());
    }
}
