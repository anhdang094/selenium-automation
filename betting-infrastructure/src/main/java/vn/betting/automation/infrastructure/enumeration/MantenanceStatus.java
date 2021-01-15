package vn.betting.automation.infrastructure.enumeration;

import java.util.HashMap;

public enum MantenanceStatus {

    ACTIVE(1), MAINTENANCE(2);

    private static final HashMap<Integer, MantenanceStatus> mantenanceStatusMap = new HashMap<>();

    static {
        for (MantenanceStatus databaseEnum : MantenanceStatus.values()) {
            mantenanceStatusMap.put(databaseEnum.value, databaseEnum);
        }
    }

    private final int value;

    MantenanceStatus(int value) {
        this.value = value;
    }

    public static MantenanceStatus fromValue(int value) {
        return mantenanceStatusMap.get(value);
    }

    public int getValue() {
        return value;
    }

}
