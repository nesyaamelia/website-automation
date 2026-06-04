package automation.pages.website.manageprofile.passwordsetting;

import automation.pages.base.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordPage extends BasePage<PasswordModel, PasswordLocator> implements PasswordSteps {

    public static PasswordPage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new PasswordPage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = PasswordModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = PasswordLocator.newInstance();
    }

    @Override
    public PasswordPage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void changeUserPassword(PasswordModel passwordModel) {
        closePopupInputWhatsapp();
        getElement().waitUntilClick(mLocator.buttonCollapse);
        getElement().waitUntilClick(mLocator.buttonEditPassword);
        getElement().waitUntilSetText(mLocator.inputPassword(1), passwordModel.oldPassword);
        getElement().waitUntilSetText(mLocator.inputPassword(2), passwordModel.newPassword);
        getElement().waitUntilSetText(mLocator.inputPassword(3), passwordModel.renewPassword);
        getElement().waitUntilClick(mLocator.buttonSubmitPassword);
    }

    @Override
    public void verifyValidPassword() {
        try {
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.snackbarSuccess));
            boolean isShowSnackbarSuccess = getElement().verifyElementPresent(mLocator.snackbarSuccess);
            logAndAssertTrue(isShowSnackbarSuccess, "edit new password");
        } catch (TimeoutException t) {
            boolean isLogoutAfterChangePassword = getElement().verifyElementPresent(mLocator.buttonLoginNavbar);
            logAndAssertTrue(isLogoutAfterChangePassword, "edit new password");
        }
    }

    @Override
    public void verifyInvalidPassword() {
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.alertErrorInputField));
        boolean snackbarInvalid = getElement().verifyElementPresent(mLocator.alertErrorInputField);
        logAndAssertTrue(snackbarInvalid, "show alert invalid password");
    }

    private void closePopupInputWhatsapp() {
        if (getElement().verifyElementPresent(mLocator.buttonCloseModal)) {
            getElement().click(mLocator.buttonCloseModal);
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonCloseModal));
        } else {
            logInfo("Popup whatsapp not appears");
        }
    }
}