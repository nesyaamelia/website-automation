package automation.pages.website.manageprofile.pinsetting;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class PinLocator extends BaseLocator {

    public static final String textCollapseButton = "collapse-button";

    public final By buttonCloseModal = By.className("s-modal-close-icon");
    public final By buttonCollapse = getElementByXpath("(//*[@class=\"collapse-button\"])[2]");
    public final By buttonPinSetting = getElementById("desktop-pin-setting-button");
    public final By buttonEditPin = getElementById("edit-pin");
    public final By snackbarSuccess = getElementByClassName("snackbar-atom-wrapper--success");
    public final By popupFreezePin = getElementByXpath("//div[@class='header-container']");
    public final By buttonModalConfirm = getElementById("error-message-modal-close-button");

    public static PinLocator newInstance() {
        return new PinLocator();
    }

    public By containsText(String text) {
        return getElementByXpath("//*[contains(text(), \"" + text + "\")]");
    }

    public By containsTextWithIndex(String text, int i) {
        return getElementByXpath("(//*[contains(text(), \"" + text + "\")])[" + i + "]");
    }

    public By inputPinPoint(int i) {
        return getElementByXpath("(//input[contains(@class, \"pin-input\")])[" + i + "]");
    }
}