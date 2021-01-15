package vn.betting.automation.infrastructure.enumeration;

import java.util.HashMap;

public enum BankFunctionType {

    BANK("bank"), BANKFUNCTION("bankfunction");

    private static final HashMap<String, BankFunctionType> bankFunctionTypeMap = new HashMap<>();

    static {
        for (BankFunctionType databaseEnum : BankFunctionType.values()) {
            bankFunctionTypeMap.put(databaseEnum.value, databaseEnum);
        }
    }

    private final String value;

    BankFunctionType(String value) {
        this.value = value;
    }

    public static BankFunctionType fromValue(String value) {
        return bankFunctionTypeMap.get(value);
    }

    public String getValue() {
        return value;
    }

}
