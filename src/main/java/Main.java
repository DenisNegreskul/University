public class Main {
    public static void main(String[] args) {
        University university = new University("1", "Высшая Школа Экономики",
                "ВШЭ", 1992, StudyProfile.ECONOMICS);
        Student student = new Student("Иванов Иван Иванович", "1", 3, 5);
        System.out.println(university);
        System.out.println(student);
    }
}
