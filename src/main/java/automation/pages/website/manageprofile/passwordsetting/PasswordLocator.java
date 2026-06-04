package automation.pages.website.manageprofile.passwordsetting;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class PasswordLocator extends BaseLocator {

    public final By inputEmail = getElementById("input-email");
    public final By alert = getElementByXpath("//*[@role=\"alert\"]");
    public final By formType = getElementById("input-form_type");
    public final By modalBody = getElementByXpath("//*[@class=\"modal-body\"]");
    public final By imageUser = getElementByXpath("//*[@id=\"avatar-button\"]/div/img");
    public final By buttonCloseModal = By.className("s-modal-close-icon");
    public final By alertErrorInputField = By.xpath("//*[@class=\"s-input-hint is-error\"]");
    public final By buttonCollapse = getElementByXpath("(//*[@class=\"collapse-button\"])[2]");
    public final By buttonEditPassword = getElementById("desktop-password-setting-button");
    public final By buttonSubmitPassword = getElementById("submit-password");
    public final By snackbarSuccess = getElementByClassName("snackbar-atom-wrapper--success");
    public final By buttonLoginNavbar = getElementById("navbar-login-button");

    public static PasswordLocator newInstance() {
        return new PasswordLocator();
    }

    public By inputPassword(int i) {
        return getElementByXpath("(//*[@type=\"password\"])[" + i + "]");
    }
}