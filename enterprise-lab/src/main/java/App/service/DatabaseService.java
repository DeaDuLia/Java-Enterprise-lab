package App.service;

import App.model.Route;
import App.model.Station;
import App.model.Train;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    public void addTrain (Train train, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.update("insert into train (speedkoeff, waitingkoeff) values (?, ?)", train.getSpeedKoeff(), train.getWaitingKoeff());
    }
    public void addStation (Station station, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.update("insert into station (station_name, km) values(?, ?)", station.getName(), station.getKm());
    }

    public void putTrainInStation (Train train, Station station, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.update("insert into trains_on_stations (train_id, station_id) values (?, ?)", train.getId(), station.getId());
        System.out.println("Поезд (" + train.getId() + ") добавлен на станцию (" + station.getId() + ")");
    }
    public void deleteTrainFromStation (Train train, Station station, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.update("delete from trains_on_stations where train_id = ? and station_id = ?", train.getId(), station.getId());
        System.out.println("Поезд (" + train.getId() + ") удалён с станции (" + station.getId() + ")");
    }
    public void changeTrainStantion (Train train, Station station, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.update("update trains_on_stations set station_id = ? where train_id = ?", station.getId(), train.getId());
        System.out.println("Поезд (" + train.getId() + ") перемещён на станцию (" + station.getId() + ")");
    }

    public void addRoute (JdbcTemplate jdbcTemplate, Route route) {
        jdbcTemplate.update("insert into route (route_name) values(?)", route.getName());
    }
    public void removeRoute (JdbcTemplate jdbcTemplate, Route route) {
        jdbcTemplate.update("delete from route where id = ?", route.getId());
    }
    public void addStationInRoute (Station station, Route route, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.update("insert into stations_on_route (route_id, station_id) values (?, ?)", route.getId(), station.getId());
    }
    public void removeStationFromRoute (Station station, Route route, JdbcTemplate jdbcTemplate) {
        jdbcTemplate.update("delete from stations_on_route where route_id = ? and station_id = ?", route.getId(), station.getId());
    }

}
