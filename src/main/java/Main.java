public class Main {
    private static final String fileName = "universityInfo.xlsx";
    public static void main(String[] args) {
        System.out.println(ExcelReader.readStudents(fileName));
        System.out.println(ExcelReader.readUniversities(fileName));
    }
}
