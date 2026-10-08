package org.example.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClassRepository {

    public boolean createClass(String name) {

        try (

                Connection conn =
                        DatabaseConnection.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                """
                                INSERT INTO classes
                                (
                                    name
                                )
                                VALUES
                                (
                                    ?
                                )
                                """
                        )

        ) {

            ps.setString(1, name);

            ps.executeUpdate();

            return true;

        }

        catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

    public void listClasses() {

        try (

                Connection conn =
                        DatabaseConnection.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                """
                                SELECT id, name
                                FROM classes
                                ORDER BY id
                                """
                        )

        ) {

            ResultSet rs = ps.executeQuery();

            System.out.println();
            System.out.println("===== CLASSES =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id")
                                + " - "
                                + rs.getString("name")
                );

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

}