package org.example.auth;

public class Session {

    private static String currentUser;

    private static String currentRole;

    public static void login(
            String username,
            String role
    ) {

        currentUser = username;
        currentRole = role;

    }

    public static void logout() {

        currentUser = null;
        currentRole = null;

    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static String getCurrentRole() {
        return currentRole;
    }

    public static boolean isLogged() {
        return currentUser != null;
    }

    public static boolean isAdmin() {

        return "ADMIN".equals(currentRole);

    }

}