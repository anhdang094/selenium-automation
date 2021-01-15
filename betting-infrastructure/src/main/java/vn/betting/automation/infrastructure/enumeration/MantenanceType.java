package vn.betting.automation.infrastructure.enumeration;

import java.util.HashMap;

public enum MantenanceType {

    UNKNOWN("unknown"), STATUS("status"), MANUAL("manual"), DAILY("daily");

    private static final HashMap<String, MantenanceType> mantenanceTypeMap = new HashMap<>();

    static {
        for (MantenanceType databaseEnum : MantenanceType.values()) {
            mantenanceTypeMap.put(databaseEnum.value, databaseEnum);
        }
    }

    private final String value;

    MantenanceType(String value) {
        this.value = value;
    }

    public static MantenanceType fromValue(String value) {
        return mantenanceTypeMap.get(value);
    }

    public String getValue() {
        return value;
    }

}
