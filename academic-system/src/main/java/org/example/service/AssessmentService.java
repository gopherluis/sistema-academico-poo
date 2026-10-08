package org.example.service;

import org.example.persistence.AssessmentRepository;

public class AssessmentService {

    private AssessmentRepository repository;

    public AssessmentService() {
        repository = new AssessmentRepository();
    }

    public void generateClassSummaryReport(int classId) {

        if (classId <= 0) {
            System.out.println("Invalid class ID.");
            return;
        }

        repository.printAssessmentsByClassId(classId);

    }

    public void generateAssessmentWeightReport() {

        repository.printAssessmentWeightReport();

    }

    public boolean registerAssessment(
            int classId,
            String name,
            double weight
    ) {

        if (classId <= 0) {
            return false;
        }

        if (name == null || name.isBlank()) {
            return false;
        }

        if (weight <= 0) {
            return false;
        }

        return repository.createAssessment(
                classId,
                name,
                weight
        );

    }

}