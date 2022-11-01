import model.*;
import comparator.*;

import java.util.List;

public class Main {
    private static final String fileName = "src/main/resources/universityInfo.xlsx";

    public static void main(String[] args) {
        List<Student> students = ExcelReader.readStudents(fileName);
        System.out.println("Students:");
        for (StudentComparatorType studentComparatorType : StudentComparatorType.values()) {
            System.out.println("Sorted by " + studentComparatorType + ":");
            StudentComparator studentComparator =
                    ComparatorSelector.getStudentComparator(studentComparatorType);
            students.stream()
                    .sorted(studentComparator)
                    .forEach(System.out::println);
        }

        List<University> universities = ExcelReader.readUniversities(fileName);
        System.out.println("\nUniversities:");
        for (UniversityComparatorType universityComparatorType : UniversityComparatorType.values()) {
            System.out.println("Sorted by " + universityComparatorType + ":");
            UniversityComparator universityComparator =
                    ComparatorSelector.getUniversityComparator(universityComparatorType);
            universities.stream()
                        .sorted(universityComparator)
                        .forEach(System.out::println);
        }
    }
}
