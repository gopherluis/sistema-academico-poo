package org.example;

import org.example.academic.system.controller.AuthenticationController;
import org.example.academic.system.security.Session;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AuthenticationController authController = new AuthenticationController();

        System.out.println("==================================");
        System.out.println(" Academic System");
        System.out.println("==================================");

        System.out.print("Usuário: ");
        String username = scanner.nextLine();

        System.out.print("Senha: ");
        String password = scanner.nextLine();

        try {
            authController.login(username, password);

            System.out.println("\nLogin realizado com sucesso!");
            System.out.println("Usuário: " + Session.getLoggedUser().getUsername());
            System.out.println("Perfil: " + Session.getLoggedUser().getRole());

            // Aqui entraremos com os menus nas próximas etapas
            // authController.showMenu();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        scanner.close();
    }
}