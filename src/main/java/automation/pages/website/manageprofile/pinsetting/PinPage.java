package automation.pages.website.manageprofile.pinsetting;

import automation.pages.base.BasePage;
import automation.pages.website.manageprofile.remote.database.ManageProfileDbHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static automation.pages.website.manageprofile.pinsetting.PinLocator.*;

public class PinPage extends BasePage<PinModel, PinLocator> implements PinSteps {

    public static PinPage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new PinPage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = PinModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = PinLocator.newInstance();
    }

    @Override
    public PinPage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void createNewPinPoints() {
        navigateToPinPage();
        inputPinPoints(mModel.newPin);
        inputPinPoints(mModel.newPin);
        validateSuccessUpdatePin("create pin");
    }

    @Override
    public void updatePinPoints(PinModel pinModel) {
        navigateToPinPage();
        int userId = globalVariable.websiteConfiguration.user.userPinId;
        boolean isSuccessUpdate = ManageProfileDbHelper.restorePinToDefault(userId);
        assert isSuccessUpdate;
        getElement().waitUntilClick(mLocator.buttonEditPin);
        inputPinPoints(mModel.oldPin);
        inputPinPoints(pinModel.newPin);
        if (pinModel.isFailed){
            boolean isAppearError = getElement().verifyTextPresent("PIN baru yang Anda masukkan sama dengan PIN lama.");
            logAndAssertTrue(isAppearError, "alert appear");
            return;
        }
        inputPinPoints(pinModel.newPin);
        validateSuccessUpdatePin("update pin");
    }

    @Override
    public void freezePinUser(){
        navigateToPinPage();
        getElement().waitUntilClick(mLocator.buttonEditPin);
        for (int i = 1; i <= 5 ; i++) {
            inputPinPoints(mModel.invalidPin);
            stepsHelper.delay(globalVariable.shortDelay);
        }
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupFreezePin));
        boolean isSuccess = getElement().verifyElementPresent(mLocator.popupFreezePin);
        logAndAssertTrue(isSuccess, "popup appear" +
                "depend - KMWA-11832 : popup freeze appear if input wrong pin 5 times");
        getElement().clickHandlerJs(mLocator.buttonModalConfirm);
        boolean isCountdownPresent = getElement().verifyTextPresent("Coba lagi dalam");
        logAndAssertTrue(isCountdownPresent, "show countdown" +
                "depend - KMWA-11833 : wording countdown appear when user click button mengerti on popup pengaturan PIN diberhentikan sementara");
    }

    private void navigateToPinPage() {
        if (getElement().verifyElementPresent(mLocator.buttonCloseModal)) {
            getElement().click(mLocator.buttonCloseModal);
        } else {
            logInfo("Popup whatsapp not appears");
        }
        getElement().waitUntilClick(mLocator.buttonCollapse);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonCollapse));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonCollapse));
        if (getElement().verifyElementNotPresent(mLocator.buttonCollapse)) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.containsTextWithIndex(textCollapseButton, 2)));
        }
        getElement().waitUntilClick(mLocator.buttonPinSetting);
    }

    private void inputPinPoints(String otp) {
        stepsHelper.delay(globalVariable.shortDelay);
        for (int i = 1; i <= 6; i++) {
            char inputPin = otp.charAt(i - 1);
            getElement().waitUntilSetText(mLocator.inputPinPoint(i), String.valueOf(inputPin));
        }
    }

    private void validateSuccessUpdatePin(String message) {
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.snackbarSuccess));
        boolean isShowSnackbarSuccess = getElement().verifyElementPresent(mLocator.snackbarSuccess);
        logAndAssertTrue(isShowSnackbarSuccess, message);
        getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.snackbarSuccess));
    }
}