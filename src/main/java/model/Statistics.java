package model;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private StudyProfile studyProfile;
    private double avgExamScore;
    private int amountOfStudents;
    private int amountOfUniversities;
    private List<String> universityNames;

    public Statistics() {
    }

    public Statistics(StudyProfile studyProfile, double avgExamScore, int amountOfStudents, int amountOfUniversities) {
        this.studyProfile = studyProfile;
        this.avgExamScore = avgExamScore;
        this.amountOfStudents = amountOfStudents;
        this.amountOfUniversities = amountOfUniversities;
    }

    public StudyProfile getStudyProfile() {
        return studyProfile;
    }

    public void setStudyProfile(StudyProfile studyProfile) {
        this.studyProfile = studyProfile;
    }

    public double getAvgExamScore() {
        return avgExamScore;
    }

    public void setAvgExamScore(double avgExamScore) {
        this.avgExamScore = avgExamScore;
    }

    public int getAmountOfStudents() {
        return amountOfStudents;
    }

    public void setAmountOfStudents(int amountOfStudents) {
        this.amountOfStudents = amountOfStudents;
    }

    public int getAmountOfUniversities() {
        return amountOfUniversities;
    }

    public void setAmountOfUniversities(int amountOfUniversities) {
        this.amountOfUniversities = amountOfUniversities;
    }

    public List<String> getUniversityNames() {
        return universityNames;
    }

    public void setUniversityNames(List<String> universityNames) {
        this.universityNames = universityNames;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "studyProfile=" + studyProfile +
                ", avgExamScore=" + avgExamScore +
                ", amountOfStudents=" + amountOfStudents +
                ", amountOfUniversities=" + amountOfUniversities +
                ", universityNames=" + universityNames +
                '}';
    }
}
