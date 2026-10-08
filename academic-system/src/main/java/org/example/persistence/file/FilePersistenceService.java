package org.example.persistence.file;

import org.example.persistence.AssessmentRepository;
import org.example.persistence.config.PersistenceConfiguration;
import org.example.persistence.config.PersistenceType;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class FilePersistenceService {

    private AssessmentRepository assessmentRepository;

    public FilePersistenceService() {
        assessmentRepository = new AssessmentRepository();
        createFolders();
    }

    private void createFolders() {

        new File("data/txt").mkdirs();
        new File("data/json").mkdirs();
        new File("data/xml").mkdirs();

    }

    public void saveAcademicData() {

        PersistenceType type =
                PersistenceConfiguration.getCurrentType();

        if (type == PersistenceType.TXT) {
            saveToTxt();
        }

        else if (type == PersistenceType.JSON) {
            saveToJson();
        }

        else if (type == PersistenceType.XML) {
            saveToXml();
        }

        else {
            System.out.println(
                    "Current persistence is DATABASE. Data is already saved in MySQL."
            );
        }

    }

    public void saveToTxt() {

        List<AcademicFilePersistence> data =
                assessmentRepository.getAllAcademicData();

        try (
                FileWriter writer =
                        new FileWriter("data/txt/assessments.txt")
        ) {

            for (AcademicFilePersistence item : data) {

                writer.write(
                        "Class: " + item.getClassName() + "\n"
                );

                writer.write(
                        "Assessment: " + item.getAssessmentName() + "\n"
                );

                writer.write(
                        "Weight: " + item.getWeight() + "\n"
                );

                writer.write("\n");

            }

            System.out.println(
                    "Academic data saved to TXT file."
            );

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void saveToJson() {

        List<AcademicFilePersistence> data =
                assessmentRepository.getAllAcademicData();

        try (
                FileWriter writer =
                        new FileWriter("data/json/academicData.json")
        ) {

            writer.write("[\n");

            for (int i = 0; i < data.size(); i++) {

                AcademicFilePersistence item =
                        data.get(i);

                writer.write("  {\n");
                writer.write("    \"className\": \"" + item.getClassName() + "\",\n");
                writer.write("    \"assessmentName\": \"" + item.getAssessmentName() + "\",\n");
                writer.write("    \"weight\": " + item.getWeight() + "\n");
                writer.write("  }");

                if (i < data.size() - 1) {
                    writer.write(",");
                }

                writer.write("\n");

            }

            writer.write("]\n");

            System.out.println(
                    "Academic data saved to JSON file."
            );

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void saveToXml() {

        List<AcademicFilePersistence> data =
                assessmentRepository.getAllAcademicData();

        try (
                FileWriter writer =
                        new FileWriter("data/xml/academicData.xml")
        ) {

            writer.write("<academicData>\n");

            for (AcademicFilePersistence item : data) {

                writer.write("  <assessment>\n");
                writer.write("    <className>" + item.getClassName() + "</className>\n");
                writer.write("    <assessmentName>" + item.getAssessmentName() + "</assessmentName>\n");
                writer.write("    <weight>" + item.getWeight() + "</weight>\n");
                writer.write("  </assessment>\n");

            }

            writer.write("</academicData>\n");

            System.out.println(
                    "Academic data saved to XML file."
            );

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void printConfigurationReport() {

        System.out.println();
        System.out.println("===== PERSISTENCE CONFIGURATION REPORT =====");
        System.out.println(
                "Current persistence type: "
                        + PersistenceConfiguration.getCurrentType()
        );

    }

}