package excel;

import model.Statistics;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

public class ExcelWriter {

    private ExcelWriter() {
    }

    public static void writeStatisticsToFile(Collection<Statistics> statisticsCollection, String fileName) {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {

            XSSFSheet sheet = workbook.createSheet("Статистика");
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(18);
            XSSFCellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setFont(font);

            int rowIndex = 0;
            XSSFRow titleRow = sheet.createRow(rowIndex++);
            titleRow.setRowStyle(titleStyle);
            titleRow.createCell(0).setCellValue("Профиль обучения");
            titleRow.createCell(1).setCellValue("Средняя оценка за экзамен");
            titleRow.createCell(2).setCellValue("Количество студентов");
            titleRow.createCell(3).setCellValue("Количество университетов");
            titleRow.createCell(4).setCellValue("Названия университетов");

            for (Statistics statistics : statisticsCollection) {
                XSSFRow row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(statistics.getStudyProfile().getProfileName());
                row.createCell(1).setCellValue(statistics.getAvgExamScore());
                row.createCell(2).setCellValue(statistics.getAmountOfStudents());
                row.createCell(3).setCellValue(statistics.getAmountOfUniversities());
                row.createCell(4).setCellValue(statistics.getUniversityNames().toString());
            }

            workbook.write(fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
