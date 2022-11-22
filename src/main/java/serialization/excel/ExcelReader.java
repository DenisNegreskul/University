package serialization.excel;

import model.Student;
import model.StudyProfile;
import model.University;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    private static final Logger logger = LoggerFactory.getLogger(ExcelReader.class);

    private ExcelReader() {
    }

    public static List<Student> readStudents(Path fileName) {
        List<Student> students = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(fileName.toString()))) {
            logger.info("Reading students from {} started", fileName);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Student student = new Student();
                student.setUniversityId(row.getCell(0).getStringCellValue())
                        .setFullName(row.getCell(1).getStringCellValue())
                        .setCurrentCourseNumber((int) row.getCell(2).getNumericCellValue())
                        .setAvgExamScore(row.getCell(3).getNumericCellValue());
                students.add(student);
            }
        } catch (IOException e) {
            logger.error("Reading students from {} failed", fileName, e);
            throw new RuntimeException(e);
        }
        logger.info("Reading students from {} ended successfully", fileName);
        return students;
    }

    public static List<University> readUniversities(Path fileName) {
        List<University> universities = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(fileName.toString()))) {
            logger.info("Reading universities from {} started", fileName);
            XSSFSheet sheet = workbook.getSheetAt(1);
            Iterator<Row> rowIterator = sheet.rowIterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                University university = new University();
                university.setId(row.getCell(0).getStringCellValue())
                        .setFullName(row.getCell(1).getStringCellValue())
                        .setShortName(row.getCell(2).getStringCellValue())
                        .setYearOfFoundation((int) row.getCell(3).getNumericCellValue())
                        .setMainProfile(StudyProfile.valueOf(row.getCell(4).getStringCellValue()));
                universities.add(university);
            }
        } catch (IOException e) {
            logger.error("Reading universities from {} failed", fileName, e);
            throw new RuntimeException(e);
        }
        logger.info("Reading universities from {} ended successfully", fileName);
        return universities;
    }
}
