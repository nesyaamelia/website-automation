package automation.pages.website.manageprofile.passwordsetting;

import automation.data.User;
import automation.pages.base.BaseModel;

public class PasswordModel extends BaseModel {

    public String oldPassword;
    public String newPassword;
    public String renewPassword;
    public boolean isValidPassword;

    public static PasswordModel newInstance() {
        return new PasswordModel();
    }

    public PasswordModel charPasswordLowerCase() {
        oldPassword = System.getenv().getOrDefault("TEST_OLD_PASSWORD", "OldPassword1");
        newPassword = System.getenv().getOrDefault("TEST_NEW_PASSWORD", "NewPassword1");
        renewPassword = System.getenv().getOrDefault("TEST_NEW_PASSWORD", "NewPassword1");
        return this;
    }

    public PasswordModel passwordOnlyNumber() {
        oldPassword = System.getenv().getOrDefault("TEST_OLD_PASSWORD", "OldPassword1");
        newPassword = System.getenv().getOrDefault("TEST_NEW_PASSWORD_SIMPLE", "NewPass12");
        renewPassword = System.getenv().getOrDefault("TEST_NEW_PASSWORD_SIMPLE", "NewPass12");
        return this;
    }

    public PasswordModel passwordLessThanRequirement() {
        oldPassword = System.getenv().getOrDefault("TEST_OLD_PASSWORD", "OldPassword1");
        newPassword = "varcha";
        renewPassword = "varcha";
        return this;
    }

    public PasswordModel passwordLowerAndNumber() {
        oldPassword = System.getenv().getOrDefault("TEST_OLD_PASSWORD", "OldPassword1");
        newPassword = "varcha1234";
        renewPassword = "varcha1234";
        return this;
    }

    public PasswordModel passwordUperAndNumber() {
        oldPassword = System.getenv().getOrDefault("TEST_OLD_PASSWORD", "OldPassword1");
        newPassword = "VARCHA1234";
        renewPassword = "VARCHA1234";
        return this;
    }

    public PasswordModel combineInputPassword() {
        oldPassword = System.getenv().getOrDefault("TEST_OLD_PASSWORD", "OldPassword1");
        newPassword = "varCHAR";
        renewPassword = "varchar";
        return this;
    }

    public PasswordModel passwordUppercase() {
        oldPassword = System.getenv().getOrDefault("TEST_OLD_PASSWORD", "OldPassword1");
        newPassword = "VARCHAR";
        renewPassword = "VARCHAR";
        return this;
    }

    public PasswordModel passwordNotMatched() {
        User newUser = new User();
        oldPassword = System.getenv().getOrDefault("TEST_OLD_PASSWORD", "OldPassword1");
        newPassword = newUser.generateUniquePassword;
        renewPassword = "VARCHAR";
        return this;
    }

    public PasswordModel validChangePassword() {
        User newUser = new User();
        isValidPassword = true;
        oldPassword = System.getenv().getOrDefault("TEST_OLD_PASSWORD", "OldPassword1");
        newPassword = newUser.generateUniquePassword + newUser.randomNumber;
        renewPassword = newUser.generateUniquePassword + newUser.randomNumber;
        return this;
    }

    public PasswordModel oldPasswordIncorrect() {
        User newUser = new User();
        oldPassword = System.getenv().getOrDefault("TEST_OLD_PASSWORD_ALT", "OldPassword2");
        newPassword = newUser.generateUniquePassword + newUser.randomNumber;
        renewPassword = newUser.generateUniquePassword + newUser.randomNumber;
        return this;
    }

    public PasswordModel newPasswordIncorrect() {
        oldPassword = System.getenv().getOrDefault("TEST_OLD_PASSWORD", "OldPassword1");
        newPassword = System.getenv().getOrDefault("TEST_NEW_PASSWORD_MIX", "NewPass123");
        renewPassword = System.getenv().getOrDefault("TEST_NEW_PASSWORD_MIX", "NewPass123");
        return this;
    }

    public PasswordModel moreThan8Chars() {
        User newUser = new User();
        isValidPassword = true;
        oldPassword = System.getenv().getOrDefault("TEST_OLD_PASSWORD", "OldPassword1");
        newPassword = newUser.generateUniquePassword + newUser.randomNumber;
        renewPassword = newUser.generateUniquePassword + newUser.randomNumber;
        return this;
    }

    public PasswordModel onlyOldField() {
        isValidPassword = true;
        oldPassword = System.getenv().getOrDefault("TEST_OLD_PASSWORD", "OldPassword1");
        newPassword = "";
        renewPassword = "";
        return this;
    }
}