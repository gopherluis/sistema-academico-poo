package org.example.persistence.config;

public class PersistenceConfiguration {

    private static PersistenceType currentType =
            PersistenceType.DATABASE;

    public static PersistenceType getCurrentType() {
        return currentType;
    }

    public static void setCurrentType(
            PersistenceType type
    ) {
        currentType = type;
    }

}