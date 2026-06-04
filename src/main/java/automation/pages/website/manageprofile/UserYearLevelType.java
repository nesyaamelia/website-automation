package automation.pages.website.manageprofile;

public enum UserYearLevelType {

    PROFESSIONAL(17),
    TEACHER(40),
    COLLEGE(12),
    PARENT(11),
    STUDENT(9);

    public final int yearLevel;

    UserYearLevelType(int level) {
        this.yearLevel = level;
    }
}