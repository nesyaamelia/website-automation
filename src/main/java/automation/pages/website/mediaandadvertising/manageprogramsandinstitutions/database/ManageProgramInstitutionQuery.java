package automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.database;

public class ManageProgramInstitutionQuery {

    public static String getProgramName(String programName){
        return "SELECT name FROM db_programs.program WHERE name = '"+programName+"';";
    }
}
