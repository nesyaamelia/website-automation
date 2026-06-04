package automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.database;

import automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.database.table.Program;
import sekolahmu.datasource.database.SekolahmuDatabase;

public class ManageProgramInstitutionDBHelper {

    public static String getProgramName(String programName){
        String list = null;
        try {
            list = SekolahmuDatabase.selectQuery(Program.class, ManageProgramInstitutionQuery.getProgramName(programName)).get(0).programName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert list !=null;
        return list;
    }
}
