package com.java.oop;

import java.util.Arrays;
import java.util.HashMap;

public class MBTA {

    private HashMap<String, Line> lines = new HashMap<>();

    public MBTA() {
        lines.put("Red", new Line("Red", Arrays.asList(
                new Station("South Station"),
                new Station("Park Street"),
                new Station("Kendall"),
                new Station("Central"),
                new Station("Harvard"),
                new Station("Porter"),
                new Station("Davis"),
                new Station("Alewife")
        )));

        lines.put("Green", new Line("Green", Arrays.asList(
                new Station("Government Center"),
                new Station("Park Street"),
                new Station("Boylston"),
                new Station("Arlington"),
                new Station("Copley"),
                new Station("Hynes"),
                new Station("Kenmore")
        )));

        lines.put("Orange", new Line("Orange", Arrays.asList(
                new Station("North Station"),
                new Station("Haymarket"),
                new Station("Park Street"),
                new Station("State"),
                new Station("Downtown Crossing"),
                new Station("Chinatown"),
                new Station("Back Bay"),
                new Station("Forest Hills")
        )));
    }

    public Line getLine(String name) {
        return lines.get(name);
    }
}
