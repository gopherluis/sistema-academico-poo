package org.example.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.example.viewmodel.UserViewModel;

public class UserRepository {

    public UserViewModel authenticate(
        String username,
        String password
) {

        try (

                Connection conn =
                        DatabaseConnection.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                """
                                SELECT username, role
                                FROM users
                                WHERE username = ?
                                AND password = ?
                                """
                        )

        ) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {
                return new UserViewModel(
                        rs.getString("username"),
                        rs.getString("role")
                );
            }

            return null;

        }

        catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

    public void listUsers() {

        try (

                Connection conn =
                        DatabaseConnection.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                """
                                SELECT id, username, role
                                FROM users
                                ORDER BY id
                                """
                        )

        ) {

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id")
                                + " - "
                                + rs.getString("username")
                                + " - "
                                + rs.getString("role")
                );

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    public boolean existsByUsername(
            String username
    ) {

        try (

                Connection conn =
                        DatabaseConnection.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                """
                                SELECT 1
                                FROM users
                                WHERE username = ?
                                """
                        )

        ) {

            ps.setString(1, username);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        }

        catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

    public boolean createUser(
            String username,
            String password
    ) {

        if (existsByUsername(username)) {
            return false;
        }

        try (

                Connection conn =
                        DatabaseConnection.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                """
                                INSERT INTO users
                                (
                                    username,
                                    password
                                )
                                VALUES
                                (
                                    ?,
                                    ?
                                )
                                """
                        )

        ) {

            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate();

            return true;

        }

        catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }
    
    public boolean deleteByUsername(String username) {

        try (

                Connection conn =
                        DatabaseConnection.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                """
                                DELETE FROM users
                                WHERE username = ?
                                """
                        )

        ) {

            ps.setString(1, username);

            int rowsAffected =
                    ps.executeUpdate();

            return rowsAffected > 0;

        }

        catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

}