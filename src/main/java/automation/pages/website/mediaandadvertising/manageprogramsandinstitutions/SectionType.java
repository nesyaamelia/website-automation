package automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions;

public enum SectionType {
    PROGRAM_UNGGULAN("Program Unggulan"),
    PROGRAM_RECOMMENDATION("Program Rekomendasi"),
    PROGRAM_OFFLINE("Program Offline"),
    PROGRAM_WEBINAR("Program Webinar"),
    PROGRAM_SPL("Program SPL"),
    FAVORITE_INSTITUTION("Lembaga Favorit");

    public final String section;

    SectionType(String listSection){
        this.section = listSection;
    }
}
