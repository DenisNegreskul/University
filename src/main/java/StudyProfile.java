public enum StudyProfile {
    MEDICINE("Медицина"),
    ENGINEERING("Инженерное дело"),
    PROGRAMMING("Программирование"),
    HISTORY("История"),
    PHILOSOPHY("Философия"),
    PSYCHOLOGY("Психология"),
    PHYSICS("Физика"),
    LINGUISTICS("Лингвистика"),
    MATHEMATICS("Математика"),
    ECONOMICS("Экономика");

    private final String profileName;

    public String getProfileName() {
        return profileName;
    }

    StudyProfile(String profileName) {
        this.profileName = profileName;
    }
}
