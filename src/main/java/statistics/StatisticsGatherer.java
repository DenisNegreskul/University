package statistics;

import model.Student;
import model.University;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;

public class StatisticsGatherer {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsGatherer.class);

    private StatisticsGatherer() {
    }

    public static Collection<Statistics> gatherStatistics(Collection<Student> students, Collection<University> universities) {
        logger.info("Gathering statistics started");
        List<Statistics> statisticsList = new ArrayList<>();
        universities.stream()
                .map(University::getMainProfile)
                .distinct()
                .forEach(studyProfile -> {
                    Statistics statistics = new Statistics();
                    statisticsList.add(statistics);
                    statistics.setStudyProfile(studyProfile);

                    List<String> universityIDList = universities.stream()
                            .filter(university -> university.getMainProfile().equals(studyProfile))
                            .map(University::getId)
                            .toList();
                    List<Student> studentList = students.stream()
                            .filter(student -> universityIDList.contains(student.getUniversityId()))
                            .toList();
                    statistics.setAmountOfStudents(studentList.size());

                    OptionalDouble averageScore = studentList.stream()
                            .mapToDouble(Student::getAvgExamScore)
                            .average();
                    statistics.setAvgExamScore(BigDecimal.valueOf(averageScore.orElse(0))
                            .setScale(2, RoundingMode.HALF_EVEN).doubleValue());

                    List<String> universityNamesList = universities.stream()
                            .filter(university -> university.getMainProfile().equals(studyProfile))
                            .map(University::getShortName)
                            .toList();
                    statistics.setUniversityNames(universityNamesList);
                    statistics.setAmountOfUniversities(universityNamesList.size());
                });
        logger.info("Gathering statistics ended successfully");
        return statisticsList;
    }
}
