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
import App.config.AppConfiguration;
import App.model.*;
import App.service.TestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@ComponentScan(
        basePackageClasses = {TestService.class,
                AppConfiguration.class,
                })
public class ConsoleApplication
{
    public static int trID = 0;
    public static int stID = 0;
    public static int roID = 0;
    public static Map<Integer, Train> trains = new HashMap<>();
    public static Map<Integer, Route> routes = new HashMap<>();
    public static Railway railway = new Railway();
    public static Table table = new Table();
    public static TestService testService;
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConsoleApplication.class);
        testService = context.getBean(TestService.class);
        // Далее работа с сервисами
        //testService.printHello();
        MyListener myListener = new MyListener();
        myListener.start();
    }
    public static class MyListener extends Thread {
        public void run() {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String opt = sc.next().toLowerCase();
                if (opt.equals("st")) {
                    railway.getStations().put(stID, testService.createStation(stID));
                    stID++;
                }
                if (opt.equals("tr")) {
                    trains.put(trID, testService.createTrain(trID));
                    trID++;
                }
                if (opt.startsWith("add")) {
                    int trid = sc.nextInt();
                    int stid = sc.nextInt();
                    testService.putTrainAtStation(trains.get(trid), railway.getStations().get(stid));
                }
                if (opt.startsWith("move")) {
                    int trid = sc.nextInt();
                    int stid = sc.nextInt();
                    int stid0 = sc.nextInt();
                    testService.moveTrain(trains.get(trid), railway.getStations().get(stid), railway.getStations().get(stid0));
                }
                if (opt.startsWith("ro")) {
                    List<Integer> ids = new ArrayList<>();
                    while (sc.hasNextInt()) {
                        ids.add(sc.nextInt());
                    }
                    Route route = new Route(roID);
                    testService.print("Маршрут создан (" + roID + ")");
                    testService.print("╔═╗");
                    for (Integer key : ids) {
                        route.getStations().add(railway.getStations().get(key));
                        testService.print("╠" + key + "╣");
                    }
                    testService.print("╚═╝");
                    routes.put(roID, route);
                    roID++;
                }
                if (opt.startsWith("gr")) {
                    int roid = sc.nextInt();
                    testService.getRoute(routes, roid);
                }
                if (opt.startsWith("ta")) {
                    int trid = sc.nextInt();
                    int roid = sc.nextInt();
                    testService.createTableEl(table, routes.get(roid), trains.get(trid));
                }
            }
        }
    }
}