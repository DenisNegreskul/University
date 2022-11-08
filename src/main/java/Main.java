import comparator.StudentComparatorType;
import comparator.UniversityComparatorType;
import model.Student;
import model.University;

import java.util.List;

public class Main {
    private static final String fileName = "src/main/resources/universityInfo.xlsx";

    public static void main(String[] args) {
        List<Student> students = ExcelReader.readStudents(fileName);
        System.out.println("Students:");
        for (StudentComparatorType studentComparatorType : StudentComparatorType.values()) {
            System.out.println("Sorted by " + studentComparatorType + ":");
            students.stream()
                    .sorted(studentComparatorType.getComparator())
                    .forEach(System.out::println);
        }

        List<University> universities = ExcelReader.readUniversities(fileName);
        System.out.println("\nUniversities:");
        for (UniversityComparatorType universityComparatorType : UniversityComparatorType.values()) {
            System.out.println("Sorted by " + universityComparatorType + ":");
            universities.stream()
                        .sorted(universityComparatorType.getComparator())
                        .forEach(System.out::println);
        }
    }
}
