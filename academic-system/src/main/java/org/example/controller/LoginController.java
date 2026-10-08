package org.example.controller;

import org.example.auth.AuthenticationService;
import org.example.auth.Session;
import org.example.viewmodel.UserViewModel;

import java.util.Scanner;

public class LoginController {

    private void deleteUser(Scanner sc) {

        System.out.println();
        System.out.println("===== REMOVER USUÁRIO =====");

        System.out.print("Usuário a remover: ");
        String username = sc.nextLine();

        boolean success =
                auth.deleteUser(username);

        if (success) {
            System.out.println("Usuário removido.");
        } else {
            System.out.println("Não foi possível remover o usuário.");
        }

    }

    private AuthenticationService auth;

    public LoginController() {
        auth = new AuthenticationService();
    }

    public void start() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println();
            System.out.println("===== SISTEMA ACADÊMICO =====");
            System.out.println("1 - Login");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            String option = sc.nextLine();

            if (option.equals("1")) {
                login(sc);
            }

            else if (option.equals("0")) {
                System.out.println("Sistema encerrado.");
                break;
            }

            else {
                System.out.println("Opção inválida.");
            }

        }

    }

    private void login(Scanner sc) {

        System.out.println();
        System.out.println("===== LOGIN =====");

        System.out.print("Usuário: ");
        String username = sc.nextLine();

        System.out.print("Senha: ");
        String password = sc.nextLine();

        UserViewModel user =
                auth.login(username, password);

        if (user == null) {
            System.out.println("Credenciais inválidas.");
            return;
        }

        System.out.println("Bem-vindo " + user.getUsername());

        System.out.println(
        "Permissão: " +
        user.getRole()
    );

        showMenu(sc);

    }

    private void showMenu(Scanner sc) {

    while (Session.isLogged()) {

        System.out.println();
        System.out.println("===== MENU =====");
        System.out.println("1 - Logout");
        System.out.println("2 - Academic system");

        if (Session.isAdmin()) {
            System.out.println("3 - Listar usuários");
            System.out.println("4 - Remover usuário");
        }

        System.out.print("Escolha: ");

        String option = sc.nextLine();

        if (option.equals("1")) {

            Session.logout();
            System.out.println("Logout realizado.");

        }

        else if (option.equals("2")) {

            AcademicSystemController academicController =
                    new AcademicSystemController();

            academicController.start(sc);

        }

        else if (option.equals("3") && Session.isAdmin()) {

            auth.listUsers();
        }
        else if (option.equals("4") && Session.isAdmin()) {
            deleteUser(sc);
        }
        else {
            System.out.println("Opção inválida.");
        }
    }
}
}
