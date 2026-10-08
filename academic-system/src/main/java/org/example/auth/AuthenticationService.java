package org.example.auth;

import org.example.persistence.UserRepository;
import org.example.viewmodel.UserViewModel;

public class AuthenticationService {

    private UserRepository repository;

    public AuthenticationService() {
        repository = new UserRepository();
    }

    public UserViewModel login(
            String username,
            String password
    ) {

        UserViewModel user =
                repository.authenticate(
                        username,
                        password
                );

        if (user != null) {
            Session.login(
                    user.getUsername(),
                    user.getRole()
            );
        }

        return user;

    }

    public boolean deleteUser(String username) {

        if (!Session.isAdmin()) {
            return false;
        }

        if (Session.getCurrentUser().equals(username)) {
            return false;
        }

        return repository.deleteByUsername(username);

    }

    public void listUsers() {

    if (!Session.isAdmin()) {
        System.out.println("Acesso negado.");
        return;
    }

    repository.listUsers();

}

    public boolean register(
            String username,
            String password
    ) {

        return repository.createUser(
                username,
                password
        );

    }

}