package org.example.controller;

import org.example.persistence.config.PersistenceConfiguration;
import org.example.persistence.config.PersistenceType;
import org.example.persistence.file.FilePersistenceService;
import org.example.service.AssessmentService;
import org.example.service.ClassService;

import java.util.Scanner;

public class AcademicSystemController {

    private FilePersistenceService filePersistenceService;
    private ClassService classService;
    private AssessmentService assessmentService;

    public AcademicSystemController() {
        classService = new ClassService();
        assessmentService = new AssessmentService();
        filePersistenceService = new FilePersistenceService();
    }

    public void start(Scanner sc) {

        while (true) {

            System.out.println();
            System.out.println("===== ACADEMIC SYSTEM =====");
            System.out.println("1 - Register class");
            System.out.println("2 - List classes");
            System.out.println("3 - Register assessment");
            System.out.println("4 - Class assessment summary report");
            System.out.println("5 - Assessment weight report");
            System.out.println("6 - Configure persistence type");
            System.out.println("7 - Save academic data using configured persistence");
            System.out.println("8 - Persistence configuration report");
            System.out.println("0 - Back");
            System.out.print("Choose: ");

            String option = sc.nextLine();

            if (option.equals("1")) {
                registerClass(sc);
            }

            else if (option.equals("2")) {
                classService.listClasses();
            }

            else if (option.equals("3")) {
                registerAssessment(sc);
            }

            else if (option.equals("4")) {
                generateClassSummaryReport(sc);
            }

            else if (option.equals("5")) {
                assessmentService.generateAssessmentWeightReport();
            }

            else if (option.equals("6")) {
                configurePersistence(sc);
            }

            else if (option.equals("7")) {
                filePersistenceService.saveAcademicData();
            }

            else if (option.equals("8")) {
                filePersistenceService.printConfigurationReport();
            }

            else if (option.equals("0")) {
                break;
            }

            else {
                System.out.println("Invalid option.");
            }

        }

    }

    private void configurePersistence(Scanner sc) {

        System.out.println();
        System.out.println("===== CONFIGURE PERSISTENCE =====");
        System.out.println("1 - DATABASE");
        System.out.println("2 - TXT");
        System.out.println("3 - JSON");
        System.out.println("4 - XML");
        System.out.print("Choose: ");

        String option = sc.nextLine();

        if (option.equals("1")) {
            PersistenceConfiguration.setCurrentType(PersistenceType.DATABASE);
        }

        else if (option.equals("2")) {
            PersistenceConfiguration.setCurrentType(PersistenceType.TXT);
        }

        else if (option.equals("3")) {
            PersistenceConfiguration.setCurrentType(PersistenceType.JSON);
        }

        else if (option.equals("4")) {
            PersistenceConfiguration.setCurrentType(PersistenceType.XML);
        }

        else {
            System.out.println("Invalid persistence type.");
            return;
        }

        System.out.println(
                "Persistence configured as "
                        + PersistenceConfiguration.getCurrentType()
        );

    }

    private void registerClass(Scanner sc) {

        System.out.println();
        System.out.println("===== REGISTER CLASS =====");

        System.out.print("Class name: ");
        String name = sc.nextLine();

        boolean success =
                classService.registerClass(name);

        if (success) {
            System.out.println("Class registered.");
        }

        else {
            System.out.println("Could not register class.");
        }

    }

    private void registerAssessment(Scanner sc) {

        System.out.println();
        System.out.println("===== REGISTER ASSESSMENT =====");

        System.out.print("Class ID: ");
        int classId =
                Integer.parseInt(sc.nextLine());

        System.out.print("Assessment name: ");
        String name =
                sc.nextLine();

        System.out.print("Weight: ");
        double weight =
                Double.parseDouble(sc.nextLine());

        boolean success =
                assessmentService.registerAssessment(
                        classId,
                        name,
                        weight
                );

        if (success) {
            System.out.println("Assessment registered.");
        }

        else {
            System.out.println("Could not register assessment.");
        }

    }

    private void generateClassSummaryReport(Scanner sc) {

        System.out.println();
        System.out.println("===== CLASS SUMMARY REPORT =====");

        System.out.print("Class ID: ");
        int classId =
                Integer.parseInt(sc.nextLine());

        assessmentService.generateClassSummaryReport(
                classId
        );

    }

}