package model;

import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement(name = "studentEntry")
@XmlAccessorType(XmlAccessType.NONE)
public class Student implements Serializable {

    @XmlElement(name = "studentName")
    @SerializedName("Полное имя")
    private String fullName;
    @XmlElement(name = "universityId")
    @SerializedName("ID университета")
    private String universityId;
    @SerializedName("Курс")
    private int currentCourseNumber;
    @XmlElement(name = "avgScore")
    @SerializedName("Средняя оценка за экзамен")
    private double avgExamScore;

    public Student() {
    }

    public Student(String fullName, String universityId, int currentCourseNumber, float avgExamScore) {
        this.fullName = fullName;
        this.universityId = universityId;
        this.currentCourseNumber = currentCourseNumber;
        this.avgExamScore = avgExamScore;
    }

    public String getFullName() {
        return fullName;
    }

    public Student setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getUniversityId() {
        return universityId;
    }

    public Student setUniversityId(String universityId) {
        this.universityId = universityId;
        return this;
    }

    public int getCurrentCourseNumber() {
        return currentCourseNumber;
    }

    public Student setCurrentCourseNumber(int currentCourseNumber) {
        this.currentCourseNumber = currentCourseNumber;
        return this;
    }

    public double getAvgExamScore() {
        return avgExamScore;
    }

    public Student setAvgExamScore(double avgExamScore) {
        this.avgExamScore = avgExamScore;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Student [fullName = %-15s, universityId = %-10s, currentCourseNumber = %d, avgExamScore = %.1f]",
                fullName, universityId, currentCourseNumber, avgExamScore);
    }
}
