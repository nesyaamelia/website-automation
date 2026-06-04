package automation.pages.website.manageprofile.passwordsetting;

import automation.pages.base.BaseSteps;

public interface PasswordSteps extends BaseSteps<PasswordPage, PasswordModel> {

    void changeUserPassword(PasswordModel passwordModel);
    void verifyValidPassword();
    void verifyInvalidPassword();
}