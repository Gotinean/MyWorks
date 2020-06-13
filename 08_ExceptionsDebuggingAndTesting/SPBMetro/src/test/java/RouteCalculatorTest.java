import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    List<Station> route;
    StationIndex stationIndex = new StationIndex();
    RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
    String names;
    @Override
    protected void setUp() throws Exception {
        route = new ArrayList<>();
        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");
        route.add(new Station("Петровская", line1));
        route.add(new Station("Арбузная", line1));
        route.add(new Station("Морковная", line2));
        route.add(new Station("Яблочная", line2));
        route.add(new Station("Виноградная", line3));


        Station station = new Station("Петровская", line1);
        Station station1 = new Station("Арбузная", line1);
        Station station2 = new Station("Морковная", line2);
        Station station3 = new Station("Яблочная", line2);
        Station station4 = new Station("Виноградная", line3);
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        line1.addStation(station);
        line1.addStation(station1);
        line2.addStation(station2);
        line2.addStation(station3);
        line3.addStation(station4);
        stationIndex.addStation(station);
        stationIndex.addStation(station1);
        stationIndex.addStation(station2);
        stationIndex.addStation(station3);
        stationIndex.addStation(station4);
        List<Station> list = new ArrayList<>();
        list.add(station1);
        list.add(station2);
        stationIndex.addConnection(list);
        List<Station> list1 = new ArrayList<>();
        list1.add(station3);
        list1.add(station4);
        stationIndex.addConnection(list1);
    }
//    public List<Station> getList(String... names){
//
//    }

    public void testСalculateDuration(){
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 12;
        assertEquals(expected, actual);
    }
    public void testGetShortestRoute(){
        List<Station> actual =  routeCalculator.getShortestRoute(stationIndex.getStation("Петровская"), stationIndex.getStation("Яблочная"));
        List<Station> expected = route.subList(0,4);
        assertEquals(actual, expected);
    }

    public void testGetRouteOnTheLine(){
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("Петровская"), stationIndex.getStation("Арбузная"));
        List<Station> expected = route.subList(0, 2);
        assertEquals(actual, expected);
    }

    public void testGetRouteWithOneConnection(){
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("Петровская"), stationIndex.getStation("Яблочная"));
        List<Station> expected = route.subList(0, 4);
        assertEquals(actual, expected);
    }

    public void testGetRouteWithTwoConnections(){
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("Петровская"), stationIndex.getStation("Виноградная"));
        List<Station> expected = route.subList(0,5);
        assertEquals(expected, actual);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
