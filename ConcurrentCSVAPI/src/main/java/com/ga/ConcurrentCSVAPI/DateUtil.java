package com.ga.ConcurrentCSVAPI;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {
    public static long yearsWorked(LocalDate joinDate) {
        return ChronoUnit.YEARS.between(joinDate, LocalDate.now());
    }
}
