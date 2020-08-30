import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        LocalDateTime nowDate = LocalDateTime.now();
        LocalDateTime twoHoursLater = nowDate.plusHours(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");

        System.out.println("Local time: " + nowDate.getHour() + ":" + nowDate.getMinute());

        airport.getTerminals().stream()
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)

                // Stream<Flight>
                .filter(flight -> {
                    LocalDateTime dt = toLocalDateTime(flight.getDate());
                    return flight.getType().equals(Flight.Type.DEPARTURE)
                            && dt.isAfter(nowDate)
                            && dt.isBefore(twoHoursLater);
                })

                .forEach(flight -> {
                    LocalDateTime dt = toLocalDateTime(flight.getDate());
                    System.out.println(
                            dt.format(formatter)
                                    + "\t"
                                    + flight.getAircraft().getModel()
                    );
                });

    }

    private static LocalDateTime toLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}

