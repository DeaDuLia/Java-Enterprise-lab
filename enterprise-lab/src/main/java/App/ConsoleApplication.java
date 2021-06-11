package App;
/**
 * Java, заметки.
 * =====================================================================================================================
 * =====================================================================================================================
 * 1.1. Реализовать модель данных
 * 1.2. Реализовать консольное Enterprise приложение
 *  Spring, SpringBoot
 * 2. postgreSQL, JPA
 * 3. Spring Data, postgreSQL
 * =====================================================================================================================
 * =====================================================================================================================
 * Задачи в файле Enterprise задачи
 * Моя задача: 8
 * 1. Разобраться в предм. обл.
 * 2. 5-6 сущностей реализовать
 * =====================================================================================================================
 * =====================================================================================================================
 * Зависимости:
 * 1. Lambok
 * 2. junit (test)
 * =====================================================================================================================
 * =====================================================================================================================
 */
/*
 * 1. Набросать сущности ✅
 * 2. Создать базу данных ✅
 * 3. Написать методы для работы с БД ✅
 * 4. Реализовать работу методов ✅
 * 5. События ⏲️
 */
import App.config.AppConfiguration;
import App.model.*;
import App.service.DatabaseService;
import App.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

@Configuration
@ComponentScan(
        basePackageClasses = {TestService.class,
                AppConfiguration.class,
                Table1.class,
                Table1Mapper.class,
                DatabaseService.class
                })
public class ConsoleApplication
{
    public static Table1 table = new Table1();
    public static TestService testService;
    public static DatabaseService databaseService;

    public JdbcTemplate jdbcTemplate;
    static JdbcTemplate jd;
    @Autowired
    public ConsoleApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        jd = jdbcTemplate;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConsoleApplication.class);
        testService = context.getBean(TestService.class);
        databaseService = context.getBean(DatabaseService.class);
        MyListener myListener = new MyListener();
        myListener.start();
    }
    public static class MyListener extends Thread {
        public void run() {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String opt = sc.next().toLowerCase();
                if (opt.equals("st")) {
                    String name = sc.next();
                    int km = sc.nextInt();
                    int stID = jd.query("select id from station", new BeanPropertyRowMapper<>(Station.class)).size()+1;
                    Station station = testService.createStation(stID, name, km);
                    databaseService.addStation(station, jd);
                }
                if (opt.startsWith("tr")) {
                    Train train;
                    if (sc.hasNextInt()) {
                        int speedkoeff = sc.nextInt();
                        int waitingkoeff = sc.nextInt();
                        train = new Train(-1, speedkoeff, waitingkoeff);
                    } else {
                        train = new Train();
                    }
                    databaseService.addTrain(train, jd);
                    testService.print("Поезд создан");
                }
                if (opt.startsWith("add")) {
                    int train_id = sc.nextInt();
                    int station_id = sc.nextInt();
                    Train train = (Train) jd.query("select * from train where id = " + train_id, new BeanPropertyRowMapper<>(Train.class)).get(0);
                    Station station = (Station) jd.query("select * from station where id = " + station_id, new BeanPropertyRowMapper<>(Station.class)).get(0);
                    databaseService.putTrainInStation(train, station, jd);
                }
                if (opt.startsWith("del")) {
                    int train_id = sc.nextInt();
                    int station_id = sc.nextInt();
                    Train train = (Train) jd.query("select * from train where id = " + train_id, new BeanPropertyRowMapper<>(Train.class)).get(0);
                    Station station = (Station) jd.query("select * from station where id = " + station_id, new BeanPropertyRowMapper<>(Station.class)).get(0);
                    databaseService.deleteTrainFromStation(train, station, jd );
                }
                if (opt.startsWith("move")) {
                    int train_id = sc.nextInt();
                    int station_id = sc.nextInt();
                    Train train = (Train) jd.query("select * from train where id = " + train_id, new BeanPropertyRowMapper<>(Train.class)).get(0);
                    Station station = (Station) jd.query("select * from station where id = " + station_id, new BeanPropertyRowMapper<>(Station.class)).get(0);
                    databaseService.changeTrainStantion(train, station, jd);
                }
                if (opt.startsWith("ro")) {
                    String name = sc.next();
                    List<Integer> ids = new ArrayList<>();
                    while (sc.hasNextInt()) {
                        ids.add(sc.nextInt());
                    }
                    int roID = jd.query("select * from route", new BeanPropertyRowMapper<>(Route.class)).size()+1;
                    Route route = new Route(roID, name);
                    databaseService.addRoute(jd, route);

                    for (Integer key : ids) {
                        List<Station> stations = jd.query("select * from station where id = " + key, new BeanPropertyRowMapper<>(Station.class));
                        route.getStations().add(stations.get(0));
                        databaseService.addStationInRoute(stations.get(0), route, jd);

                    }
                    testService.print("Маршрут создан (" + roID + ")");
                    testService.print("╔═╗");
                    for (Integer key : ids) {
                        testService.print("╠" + key + "╣");
                    }
                    testService.print("╚═╝");
                }
                if (opt.startsWith("gr")) {
                    int roid = sc.nextInt();
                    Route route = (Route) jd.query("select * from route where id = " + roid, new BeanPropertyRowMapper<>(Route.class)).get(0);
                    testService.getRoute(route, roid);
                }
            }
        }
    }
}