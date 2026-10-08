package org.example.persistence.file;

public class AcademicFilePersistence {

    private String className;
    private String assessmentName;
    private double weight;

    public AcademicFilePersistence(
            String className,
            String assessmentName,
            double weight
    ) {
        this.className = className;
        this.assessmentName = assessmentName;
        this.weight = weight;
    }

    public String getClassName() {
        return className;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public double getWeight() {
        return weight;
    }

}