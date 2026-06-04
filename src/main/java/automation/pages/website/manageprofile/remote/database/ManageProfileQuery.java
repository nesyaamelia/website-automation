package automation.pages.website.manageprofile.remote.database;

public class ManageProfileQuery {

    public static String getPrimaryDataUser(String email) {
        return "SELECT username, name, is_email_verified, phone  FROM db_accounts.`user` u WHERE email = \"" + email + "\"";
    }

    public static String insertInstitutionId(String email) {
        return "UPDATE db_accounts.`user` SET institution_id = {INSTITUTION_ID} WHERE email = \"" + email + "\"";
    }

    public static String updatePinUser(int userId) {
        return "UPDATE db_accounts.user_pin \n" +
                "SET pin_code = \"31d0e9d6a3944c6eef324c1df9ba66849713792f3f8d223a5cbbb194cb638a15afbff7608212b45c0746386417f6269a90c3f1123d6345e98d962f8bfafaf86d\"\n" +
                "WHERE user_id = " + userId + " ORDER BY id DESC LIMIT 1";
    }

    public static String getQueryAvailableUnfreezeAccount(){
        return "SELECT\n" +
                "  u.email\n" +
                "FROM\n" +
                "    db_accounts.user u\n" +
                "LEFT JOIN\n" +
                "    db_accounts.user_pin up\n" +
                "    ON u.id = up.user_id\n" +
                "WHERE\n" +
                "    (u.email LIKE 'test.user+%'\n" +
                "    OR u.email LIKE 'qa.engineer+%')\n" +
                "    AND up.status = 1\n" +
                "    AND up.pin_attempt = 0\n" +
                "LIMIT 1;";
    }
}