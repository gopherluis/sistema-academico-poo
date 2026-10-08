package org.example.viewmodel;

public class UserViewModel {

    private String username;
    private String role;

    public UserViewModel(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public boolean isAdmin() {
        return role.equals("ADMIN");
    }

}