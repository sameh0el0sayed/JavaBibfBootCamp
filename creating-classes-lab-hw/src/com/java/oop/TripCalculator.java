package com.java.oop;
public class TripCalculator {

    private MBTA mbta;

    public TripCalculator(MBTA mbta) {
        this.mbta = mbta;
    }

    public int stopsBetweenStations(String startLine, String startStation,
                                    String endLine, String endStation) {

        Line line1 = mbta.getLine(startLine);
        Line line2 = mbta.getLine(endLine);

        // نفس الخط
        if (startLine.equals(endLine)) {
            return Math.abs(
                    line1.indexOf(startStation) -
                            line1.indexOf(endStation)
            );
        }

        // خطوط مختلفة: المرور على Park Street
        int toPark = Math.abs(
                line1.indexOf(startStation) - line1.indexOf("Park Street")
        );

        int fromPark = Math.abs(
                line2.indexOf(endStation) - line2.indexOf("Park Street")
        );

        return toPark + fromPark;
    }
}
