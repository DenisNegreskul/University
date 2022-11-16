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
            font.setFontHeight(15);
            XSSFCellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setFont(font);

            int rowIndex = 0;
            XSSFRow titleRow = sheet.createRow(rowIndex++);
            XSSFCell cell = titleRow.createCell(0);
            cell.setCellValue("Профиль обучения");
            cell.setCellStyle(titleStyle);
            cell = titleRow.createCell(1);
            cell.setCellValue("Средняя оценка за экзамен");
            cell.setCellStyle(titleStyle);
            cell = titleRow.createCell(2);
            cell.setCellValue("Количество студентов");
            cell.setCellStyle(titleStyle);
            cell = titleRow.createCell(3);
            cell.setCellValue("Количество университетов");
            cell.setCellStyle(titleStyle);
            cell = titleRow.createCell(4);
            cell.setCellValue("Названия университетов");
            cell.setCellStyle(titleStyle);

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
