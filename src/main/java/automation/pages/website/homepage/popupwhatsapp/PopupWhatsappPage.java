package automation.pages.website.homepage.popupwhatsapp;

import automation.pages.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

public class PopupWhatsappPage extends BasePage<PopupWhatsappModel, PopupWhatsappLocator> implements PopupWhatsappSteps {

    public static PopupWhatsappPage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new PopupWhatsappPage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = PopupWhatsappModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = PopupWhatsappLocator.newInstance();
    }

    @Override
    public PopupWhatsappPage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void enrollFreeProgram() {
        getElement().navigateToUrl(mModel.urlProgram);
        try {
            getElement().waitUntilClick(mLocator.enrollProgramButton);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonStartLearning));
        } catch (TimeoutException t) {
            getElement().refresh();
            getElement().waitUntilClick(mLocator.enrollProgramButton);
        }
    }

    @Override
    public void closeStartLearning() {
        try {
            getElement().waitUntilClick(mLocator.buttonStartLearning);
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonStartLearning));
        } catch (Exception e) {
            logAndAssertTrue(getElement().verifyElementNotPresent(mLocator.buttonStartLearning), "button start learning not appears");
        }
    }

    @Override
    public void closeCoachMark() {
        try {
            getElement().waitUntilClick(mLocator.buttonCloseModalCoachmark);
            getElement().waitUntilClick(mLocator.buttonShepherd);
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.buttonCloseModalCoachmark));
            getElement().waitUntilClick(mLocator.buttonShepherd);
        }
    }

    @Override
    public void inputPhoneNumber(PopupWhatsappModel popupWhatsappModel) {
        try {
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonCloseModal));
            getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonCloseModal));
            if (popupWhatsappModel.isCloseWhatsappNumber) {
                getElement().click(mLocator.buttonCloseModal);
                getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonCloseModal));
                boolean inputWhatsappNumber = getElement().verifyElementNotPresent(mLocator.inputWhatsappNumber);
                logAndAssertTrue(inputWhatsappNumber, "is close popup whatsapp number");
            } else {
                String infoNumber = getElement().getText(mLocator.tickerWhatsappNumber).replaceAll("\\D", "");
                boolean isShowPhoneNumber = getElement().verifyElementPresent(mLocator.tickerWhatsappNumber);
                logAndAssertTrue(isShowPhoneNumber, "show phone number" +
                        "</br>depend - KMWA-11227: Free Payment - tagging ticker with phone number existing appear when user already have phone number");
                if (!popupWhatsappModel.isGetWhatsappNumber) {
                    getElement().setText(mLocator.inputWhatsappNumber, popupWhatsappModel.whatsappNumber);
                    if (Pattern.matches("\\d+", popupWhatsappModel.whatsappNumber) || Pattern.matches("\\W+", popupWhatsappModel.whatsappNumber)) {
                        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.alertErrorInputField));
                        String errorMessage = getElement().getText(mLocator.alertErrorInputField);
                        logAndAssertEqual("Format tidak sesuai. Periksa dan coba lagi.", errorMessage, "handle alert error input whatsapp number");
                    } else {
                        boolean inputWhatsappNumber = getElement().verifyElementNotPresent(mLocator.alertErrorInputField);
                        logAndAssertTrue(inputWhatsappNumber, "pattern not equals");
                    }
                    return;
                } else {
                    getElement().setText(mLocator.inputWhatsappNumber, infoNumber);
                }
                getElement().waitUntilClick(mLocator.buttonSubmitWhatsappNumber);
                getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonSubmitWhatsappNumber));
                boolean successSavePhoneNumber = getElement().verifyElementNotPresent(mLocator.inputWhatsappNumber);
                logAndAssertTrue(successSavePhoneNumber, "is success save input whatsapp number" +
                        "</br>depend - KMWA-11223: Free Payment - Modal input nomor whatsapp appear when user success free payment" +
                        "</br>depend - KMWA-11224: Free Payment - Success input phone number on popup verifikasi nomor whatsapp" +
                        "</br>depend - KMWA-11226: Free Payment - Success save phone number when user click simpan on popup verifikasi nomor whatsapp" +
                        "</br>depend - KMWA-11231: Free Payment - User input phone number between 10 until 15" +
                        "</br>depend - KMWA-11232: Free Payment - popup verifikasi only appear once on success payment");
            }
        } catch (TimeoutException t) {
            boolean popupWhatsapp = getElement().verifyElementNotPresent(mLocator.inputWhatsappNumber);
            logAndAssertFalse(popupWhatsapp, "Handle popup whatsapp");
        }
    }
}