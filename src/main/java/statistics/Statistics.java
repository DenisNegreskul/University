package statistics;

import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import model.StudyProfile;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "statisticsEntry")
@XmlAccessorType(XmlAccessType.NONE)
public class Statistics implements Serializable {
    @XmlElement(name = "universityProfile")
    @SerializedName("Профиль обучения")
    private StudyProfile studyProfile;
    @XmlElement(name = "avgScore")
    @SerializedName("Средняя оценка за экзамен по профилю")
    private double avgExamScore;
    @SerializedName("Количество студентов на данном профиле")
    private int amountOfStudents;
    @SerializedName("Количество университетов данного профиля")
    private int amountOfUniversities;
    @SerializedName("Названия университетов данного профиля")
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
