package model;

import comparator.*;

public class ComparatorSelector {

    private ComparatorSelector() {
    }

    public static StudentComparator getStudentComparator(StudentComparatorType studentComparatorType) {

        return switch (studentComparatorType) {
            case UNIVERSITY_ID -> new StudentUniversityIdComparator();
            case COURSE -> new StudentCourseComparator();
            case AVG_EXAM_SCORE -> new StudentAvgExamScoreComparator();
            case FULL_NAME -> new StudentFullNameComparator();
        };
    }

    public static UniversityComparator getUniversityComparator(UniversityComparatorType universityComparatorType) {

        return switch (universityComparatorType) {
            case ID -> new UniversityIdComparator();
            case SHORT_NAME -> new UniversityShortNameComparator();
            case PROFILE -> new UniversityProfileComparator();
            case YEAR -> new UniversityYearComparator();
            case FULL_NAME -> new UniversityFullNameComparator();
        };
    }
}