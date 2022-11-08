package comparator;

import model.Student;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;

public enum StudentComparatorType {

    UNIVERSITY_ID(Comparator.comparing(Student::getUniversityId, StringUtils::compare)),
    FULL_NAME(Comparator.comparing(Student::getFullName, StringUtils::compare)),
    COURSE(Comparator.comparingInt(Student::getCurrentCourseNumber)),
    AVG_EXAM_SCORE(Comparator.comparingDouble(Student::getAvgExamScore).reversed());

    private final Comparator<Student> comparator;

    StudentComparatorType(Comparator<Student> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Student> getComparator() {
        return comparator;
    }
}
