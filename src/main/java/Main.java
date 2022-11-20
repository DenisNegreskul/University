import comparator.StudentComparatorType;
import comparator.UniversityComparatorType;
import excel.ExcelReader;
import excel.ExcelWriter;
import model.Student;
import model.University;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import serialization.JsonUtil;
import statistics.StatisticsGatherer;

import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String inputFileName = "src/main/resources/universityInfo.xlsx";
    private static final String outputFileName = "src/main/resources/statistics.xlsx";
    private static final List<University> universities = ExcelReader.readUniversities(inputFileName);
    private static final List<Student> students = ExcelReader.readStudents(inputFileName);

    public static void main(String[] args) {
        //testSorting();
        //testSerialization();
        ExcelWriter.writeStatisticsToFile(StatisticsGatherer.gatherStatistics(students, universities), outputFileName);
    }

    private static void testSerialization() {
        logger.debug("Testing Serialization:");
        String studentsJson = JsonUtil.serialize(students);
        logger.debug("Students in JSon:");
        logger.debug(studentsJson);
        logger.debug("\nUniversities in JSon:");
        String universitiesJson = JsonUtil.serialize(universities);
        logger.debug(universitiesJson);

        List<Student> students1 = JsonUtil.deserializeStudents(studentsJson);
        List<University> universities1 = JsonUtil.deserializeUniversities(universitiesJson);
        assert students.size() == students1.size();
        assert universities.size() == universities1.size();

        logger.debug("\nEach student in JSon:");
        students.forEach(student -> {
            String studentJson = JsonUtil.serialize(student);
            logger.debug(studentJson);
            logger.debug(JsonUtil.deserializeStudent(studentJson).toString());
        });

        logger.debug("\nEach university in JSon:");
        universities.forEach(university -> {
            String universityJson = JsonUtil.serialize(university);
            logger.debug(universityJson);
            logger.debug(JsonUtil.deserializeUniversity(universityJson).toString());
        });
    }

    private static void testSorting() {
        logger.debug("Testing sorting:");
        logger.debug("Students:");
        for (StudentComparatorType studentComparatorType : StudentComparatorType.values()) {
            logger.debug("Sorted by " + studentComparatorType + ":");
            students.stream()
                    .sorted(studentComparatorType.getComparator())
                    .forEach(student -> logger.debug(student.toString()));
        }

        logger.debug("\nUniversities:");
        for (UniversityComparatorType universityComparatorType : UniversityComparatorType.values()) {
            logger.debug("Sorted by " + universityComparatorType + ":");
            universities.stream()
                    .sorted(universityComparatorType.getComparator())
                    .forEach(university -> logger.debug(university.toString()));
        }
    }
}
