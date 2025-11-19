import com.java.oop.MBTA;
import com.java.oop.TripCalculator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public static void main(String[] args) {

    MBTA mbta = new MBTA();
    TripCalculator calc = new TripCalculator(mbta);

    System.out.println(calc.stopsBetweenStations("Red", "Alewife", "Red", "Alewife"));
    System.out.println(calc.stopsBetweenStations("Red", "Alewife", "Red", "South Station"));
    System.out.println(calc.stopsBetweenStations("Red", "South Station", "Green", "Kenmore"));
}
