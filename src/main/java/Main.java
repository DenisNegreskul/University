import comparator.StudentComparatorType;
import comparator.UniversityComparatorType;
import excel.ExcelReader;
import excel.ExcelWriter;
import model.Student;
import model.University;
import serialization.JsonUtil;

import java.util.List;

public class Main {
    private static final String inputFileName = "src/main/resources/universityInfo.xlsx";
    private static final String outputFileName = "src/main/resources/statistics.xlsx";
    private static final List<University> universities = ExcelReader.readUniversities(inputFileName);
    private static final List<Student> students = ExcelReader.readStudents(inputFileName);

    public static void main(String[] args) {
        //sort();
        //testSerialization();
        ExcelWriter.writeStatisticsToFile(StatisticsGatherer.gatherStatistics(students, universities), outputFileName);
    }

    private static void testSerialization() {
        String studentsJson = JsonUtil.serialize(students);
        System.out.println("Students in JSon:");
        System.out.println(studentsJson);
        System.out.println("\nUniversities in JSon:");
        String universitiesJson = JsonUtil.serialize(universities);
        System.out.println(universitiesJson);

        List<Student> students1 = JsonUtil.deserializeStudents(studentsJson);
        List<University> universities1 = JsonUtil.deserializeUniversities(universitiesJson);
        assert students.size() == students1.size();
        assert universities.size() == universities1.size();

        System.out.println("\nEach student in JSon:");
        students.forEach(student -> {
            String studentJson = JsonUtil.serialize(student);
            System.out.println(studentJson);
            System.out.println(JsonUtil.deserializeStudent(studentJson));
        });

        System.out.println("\nEach university in JSon:");
        universities.forEach(university -> {
            String universityJson = JsonUtil.serialize(university);
            System.out.println(universityJson);
            System.out.println(JsonUtil.deserializeUniversity(universityJson));
        });
    }

    private static void sort() {
        System.out.println("Students:");
        for (StudentComparatorType studentComparatorType : StudentComparatorType.values()) {
            System.out.println("Sorted by " + studentComparatorType + ":");
            students.stream()
                    .sorted(studentComparatorType.getComparator())
                    .forEach(System.out::println);
        }

        System.out.println("\nUniversities:");
        for (UniversityComparatorType universityComparatorType : UniversityComparatorType.values()) {
            System.out.println("Sorted by " + universityComparatorType + ":");
            universities.stream()
                    .sorted(universityComparatorType.getComparator())
                    .forEach(System.out::println);
        }
    }
}
