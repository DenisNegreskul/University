import model.Student;
import model.StudyProfile;
import model.University;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    private ExcelReader() {
    }

    public static List<Student> readStudents(String fileName) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(fileName))) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            rowIterator.next();
            List<Student> students = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Student student = new Student();
                student.setUniversityId(row.getCell(0).getStringCellValue())
                        .setFullName(row.getCell(1).getStringCellValue())
                        .setCurrentCourseNumber((int) row.getCell(2).getNumericCellValue())
                        .setAvgExamScore(row.getCell(3).getNumericCellValue());
                students.add(student);
            }
            return students;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<University> readUniversities(String fileName) {
        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(fileName))) {
            XSSFSheet sheet = workbook.getSheetAt(1);
            Iterator<Row> rowIterator = sheet.rowIterator();
            rowIterator.next();
            List<University> universities = new ArrayList<>();
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
            return universities;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
