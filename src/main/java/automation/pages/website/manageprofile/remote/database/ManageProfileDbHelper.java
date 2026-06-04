package automation.pages.website.manageprofile.remote.database;

import automation.pages.website.manageprofile.remote.database.table.UserAccount;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import org.testng.Assert;
import sekolahmu.datasource.database.SekolahmuDatabase;

import java.sql.SQLException;
import java.util.List;

public class ManageProfileDbHelper {

    public static int getUserId(String email) {
        List<UserAccount> list = null;
        try {
            String getYearLevel = "SELECT id FROM db_accounts.user u WHERE email = \"" + email + "\"";
            list = SekolahmuDatabase.selectQuery(UserAccount.class, getYearLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert list != null;
        return list.get(0).id.intValue();
    }

    public static String getCodeFromEmailOtp(String email) {
        List<UserAccount> list = null;
        try {
            String getOtp = "SELECT o.otp FROM db_accounts.otp_new o " +
                    "LEFT JOIN db_accounts.user u ON o.user_id = u.id " +
                    "LEFT JOIN db_accounts.otp_purpose op on o.otp_purpose_id = op.id " +
                    "WHERE u.email = \"" + email + "\" ORDER BY o.otp_created_at DESC limit 1";
            list = SekolahmuDatabase.selectQuery(UserAccount.class, getOtp);
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        assert list != null;
        return list.get(0).otp;
    }

    public static int otpPurposeId(String email) {
        List<UserAccount> list = null;
        try {
            String getOtp = "SELECT o.otp_purpose_id FROM db_accounts.otp_new o " +
                    "LEFT JOIN db_accounts.user u ON o.user_id = u.id " +
                    "LEFT JOIN db_accounts.otp_purpose op on o.otp_purpose_id = op.id " +
                    "WHERE u.email = \"" + email + "\"";
            list = SekolahmuDatabase.selectQuery(UserAccount.class, getOtp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert list != null;
        return list.get(0).otpPurposeId.intValue();
    }

    public static List<UserAccount> getPrimaryDataUser(String email) {
        List<UserAccount> list = null;
        try {
            list = SekolahmuDatabase.selectQuery(UserAccount.class, ManageProfileQuery.getPrimaryDataUser(email));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert list != null;
        return list;
    }

    public static void updateInstitutionId(String email) {
        try {
            SekolahmuDatabase.update(ManageProfileQuery.insertInstitutionId(email));
        } catch (CommunicationsException c) {
            try {
                SekolahmuDatabase.update(ManageProfileQuery.insertInstitutionId(email));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean restorePinToDefault(int userId) {
        try {
            return SekolahmuDatabase.update(ManageProfileQuery.updatePinUser(userId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUnfreezePinAccount(){
        List<UserAccount> list;
        try {
            list = SekolahmuDatabase.selectQuery(UserAccount.class, ManageProfileQuery.getQueryAvailableUnfreezeAccount());
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        if (list.isEmpty()) Assert.fail("email not found");
        return list.get(0).email;
    }
}