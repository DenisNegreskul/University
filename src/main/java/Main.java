import com.google.gson.reflect.TypeToken;
import comparator.StudentComparatorType;
import comparator.UniversityComparatorType;
import model.Student;
import model.University;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import serialization.SerializationStructure;
import serialization.excel.ExcelReader;
import serialization.excel.ExcelWriter;
import serialization.json.JsonConverter;
import serialization.xml.XMLConverter;
import statistics.Statistics;
import statistics.StatisticsGatherer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Path INPUT_EXCEL = Paths.get("src/main/resources/universityInfo.xlsx");
    private static final Path OUTPUT_EXCEL = Paths.get("excelOutput/statistics.xlsx");
    private static final Path OUTPUT_XML = Paths.get("XMLs/output");
    private static final Path OUTPUT_JSON = Paths.get("JSONs/output");
    private static final List<University> universities = ExcelReader.readUniversities(INPUT_EXCEL);
    private static final List<Student> students = ExcelReader.readStudents(INPUT_EXCEL);

    public static void main(String[] args) {
        sortStudents(StudentComparatorType.AVG_EXAM_SCORE);
        sortUniversities(UniversityComparatorType.FULL_NAME);

        List<Statistics> statistics = (List<Statistics>) StatisticsGatherer.gatherStatistics(students, universities);
        createDirectories(OUTPUT_EXCEL);
        ExcelWriter.writeStatisticsToFile(statistics, OUTPUT_EXCEL);

        SerializationStructure structure = new SerializationStructure(students, universities, statistics);
        createDirectories(OUTPUT_XML);
        XMLConverter.serializeToXML(structure, OUTPUT_XML);

        createDirectories(OUTPUT_JSON);
        JsonConverter.serializeToJson(structure, OUTPUT_JSON);
    }

    private static void sortStudents(StudentComparatorType fieldToCompare) {
        students.sort(fieldToCompare.getComparator());
        logger.info("Students were sorted by " + fieldToCompare);
    }

    private static void sortUniversities(UniversityComparatorType fieldToCompare) {
        universities.sort(fieldToCompare.getComparator());
        logger.info("Universities were sorted by " + fieldToCompare);
    }

    private static void createDirectories(Path directory) {
        try {
            Files.createDirectories(directory.getParent());
        } catch (IOException e) {
            logger.error("Failed to create " + directory.getParent());
            throw new RuntimeException(e);
        }
    }


    private static void testSerialization() {
        logger.debug("Testing Serialization:");
        String studentsJson = JsonConverter.convertToJson(students);
        logger.debug("Students in JSon:");
        logger.debug(studentsJson);
        logger.debug("Universities in JSon:");
        String universitiesJson = JsonConverter.convertToJson(universities);
        logger.debug(universitiesJson);

        List<Student> students1 = JsonConverter.convertFromJson(studentsJson, new TypeToken<>(){});
        List<University> universities1 = JsonConverter.convertFromJson(universitiesJson, new TypeToken<>(){});
        assert students.size() == students1.size();
        assert universities.size() == universities1.size();

        logger.debug("Each student in JSon:");
        students.forEach(student -> {
            String studentJson = JsonConverter.convertToJson(student);
            logger.debug(studentJson);
            logger.debug(JsonConverter.convertFromJson(studentJson, Student.class).toString());
        });

        logger.debug("Each university in JSon:");
        universities.forEach(university -> {
            String universityJson = JsonConverter.convertToJson(university);
            logger.debug(universityJson);
            logger.debug(JsonConverter.convertFromJson(universityJson, University.class).toString());
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

        logger.debug("Universities:");
        for (UniversityComparatorType universityComparatorType : UniversityComparatorType.values()) {
            logger.debug("Sorted by " + universityComparatorType + ":");
            universities.stream()
                    .sorted(universityComparatorType.getComparator())
                    .forEach(university -> logger.debug(university.toString()));
        }
    }
}
