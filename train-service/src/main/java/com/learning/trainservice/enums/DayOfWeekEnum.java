package com.learning.trainservice.enums;

import lombok.Getter;

import java.time.DayOfWeek;

@Getter
public enum DayOfWeekEnum {
    MONDAY(0),
    TUESDAY(1),
    WEDNESDAY(2),
    THURSDAY(3),
    FRIDAY(4),
    SATURDAY(5),
    SUNDAY(6);

    private final int dayNumber;
    DayOfWeekEnum(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public static DayOfWeekEnum fromDayOfWeek(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case MONDAY -> MONDAY;
            case TUESDAY -> TUESDAY;
            case WEDNESDAY -> WEDNESDAY;
            case THURSDAY -> THURSDAY;
            case FRIDAY -> FRIDAY;
            case SATURDAY -> SATURDAY;
            case SUNDAY -> SUNDAY;
            default -> throw new IllegalArgumentException("Invalid dayOfWeek: " + dayOfWeek);
        };
    }

}
