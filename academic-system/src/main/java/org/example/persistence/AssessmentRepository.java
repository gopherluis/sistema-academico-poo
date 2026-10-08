package org.example.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AssessmentRepository {

    public boolean createAssessment(
            int classId,
            String name,
            double weight
    ) {

        try (

                Connection conn =
                        DatabaseConnection.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                """
                                INSERT INTO assessments
                                (
                                    class_id,
                                    name,
                                    weight
                                )
                                VALUES
                                (
                                    ?,
                                    ?,
                                    ?
                                )
                                """
                        )

        ) {

            ps.setInt(1, classId);
            ps.setString(2, name);
            ps.setDouble(3, weight);

            ps.executeUpdate();

            return true;

        }

        catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

    public java.util.List<org.example.persistence.file.AcademicFilePersistence>
getAllAcademicData() {

    java.util.List<org.example.persistence.file.AcademicFilePersistence> data =
            new java.util.ArrayList<>();

    try (

            Connection conn =
                    DatabaseConnection.connect();

            PreparedStatement ps =
                    conn.prepareStatement(
                            """
                            SELECT
                                c.name AS class_name,
                                a.name AS assessment_name,
                                a.weight AS assessment_weight
                            FROM assessments a
                            JOIN classes c
                                ON a.class_id = c.id
                            ORDER BY c.name, a.name
                            """
                    )

    ) {

        ResultSet rs =
                ps.executeQuery();

        while (rs.next()) {

            data.add(
                    new org.example.persistence.file.AcademicFilePersistence(
                            rs.getString("class_name"),
                            rs.getString("assessment_name"),
                            rs.getDouble("assessment_weight")
                    )
            );

        }

    }

    catch (Exception e) {

        e.printStackTrace();

    }

    return data;

}

    public void printAssessmentWeightReport() {

        try (

                Connection conn =
                        DatabaseConnection.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                """
                                SELECT 
                                    c.name AS class_name,
                                    a.name AS assessment_name,
                                    a.weight AS assessment_weight
                                FROM assessments a
                                JOIN classes c
                                    ON a.class_id = c.id
                                ORDER BY c.name, a.name
                                """
                        )

        ) {

            ResultSet rs =
                    ps.executeQuery();

            String currentClass = "";
            double totalWeight = 0;
            boolean found = false;

            System.out.println();
            System.out.println("===== ASSESSMENT WEIGHT REPORT =====");

            while (rs.next()) {

                String className =
                        rs.getString("class_name");

                if (!className.equals(currentClass)) {

                    if (!currentClass.equals("")) {
                        System.out.println("Total weight: " + totalWeight);
                        System.out.println();
                    }

                    currentClass = className;
                    totalWeight = 0;
                    found = true;

                    System.out.println("Class: " + currentClass);

                }

                double weight =
                        rs.getDouble("assessment_weight");

                System.out.println(
                        "- "
                                + rs.getString("assessment_name")
                                + " | Weight: "
                                + weight
                );

                totalWeight += weight;

            }

            if (found) {
                System.out.println("Total weight: " + totalWeight);
            } else {
                System.out.println("No assessments registered.");
            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void printAssessmentsByClassId(int classId) {

        try (

                Connection conn =
                        DatabaseConnection.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                """
                                SELECT 
                                    c.name AS class_name,
                                    a.name AS assessment_name,
                                    a.weight AS assessment_weight
                                FROM assessments a
                                JOIN classes c
                                    ON a.class_id = c.id
                                WHERE c.id = ?
                                """
                        )

        ) {

            ps.setInt(1, classId);

            ResultSet rs =
                    ps.executeQuery();

            double totalWeight = 0;
            boolean found = false;
            String className = "";

            while (rs.next()) {

                if (!found) {
                    className =
                            rs.getString("class_name");

                    System.out.println();
                    System.out.println("===== CLASS ASSESSMENT SUMMARY =====");
                    System.out.println("Class: " + className);
                    found = true;
                }

                System.out.println(
                        "Assessment: "
                                + rs.getString("assessment_name")
                                + " | Weight: "
                                + rs.getDouble("assessment_weight")
                );

                totalWeight +=
                        rs.getDouble("assessment_weight");

            }

            if (found) {
                System.out.println("Total weight: " + totalWeight);
            } else {
                System.out.println("No assessments found for this class.");
            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

}