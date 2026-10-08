package org.example.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/academic_system";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "1234";

    public static Connection connect()
            throws Exception {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );

    }

}