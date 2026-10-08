package org.example;

import org.example.persistence.DatabaseConnection;

public class MainDatabaseTest {

    public static void main(
            String[] args
    ) {

        try {

            DatabaseConnection.connect();

            System.out.println(
                    "Banco conectado!"
            );

        }

        catch (
                Exception e
        ) {

            System.out.println(
                    "Erro na conexão"
            );

            e.printStackTrace();

        }

    }

}