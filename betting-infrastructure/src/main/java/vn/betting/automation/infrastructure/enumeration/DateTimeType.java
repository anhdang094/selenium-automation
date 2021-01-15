package vn.betting.automation.infrastructure.enumeration;

import java.util.HashMap;

public enum DateTimeType {
    UNKNOW(0),
    MILISECOND(1),
    SECOND(2),
    MINUTES(3),
    HOUR(4),
    DAY(5),
    WEEK(6),
    MONTH(7),
    YEAR(8);

    private static final HashMap<Integer, DateTimeType> typeMap = new HashMap<>();

    static {
        for (DateTimeType type : DateTimeType.values()) {
            typeMap.put(type.type, type);
        }
    }

    private final int type;

    DateTimeType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static DateTimeType fromValue(int value) {
        return typeMap.get(value);
    }
}
