package vn.betting.automation.infrastructure.enumeration;

import java.util.HashMap;

public enum DatabaseType {

    MYSQL(1), MONGODB(2), KAFKA(3);

    private static final HashMap<Integer, DatabaseType> databaseTypeMap = new HashMap<>();

    static {
        for (DatabaseType databaseEnum : DatabaseType.values()) {
            databaseTypeMap.put(databaseEnum.value, databaseEnum);
        }
    }

    private final int value;

    DatabaseType(int value) {
        this.value = value;
    }

    public static DatabaseType fromValue(int value) {
        return databaseTypeMap.get(value);
    }

    public int getValue() {
        return value;
    }

}
