package automation.pages.website.paymentmethodprakerja;

import automation.pages.base.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPrakerjaPage extends BasePage<PaymentPrakerjaModel, PaymentPrakerjaLocator> implements PaymentPrakerjaStep {

    public static PaymentPrakerjaPage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new PaymentPrakerjaPage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = PaymentPrakerjaModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = PaymentPrakerjaLocator.newInstance();
    }

    @Override
    public PaymentPrakerjaPage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void userPayWithPrakerjaId(PaymentPrakerjaModel paymentPrakerjaModel) {
        getElement().allowPushNotif();
        if (getElement().verifyElementPresent(mLocator.buttonSelectSchedule)) {
            getElement().click(mLocator.buttonSelectSchedule);
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonSelectSchedule));
        }
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.getTextProgramName));
        String getProgramName = getElement().getText(mLocator.getTextProgramName);
        if (getProgramName.isEmpty()) {
            for (int i = 1; i <= 5; i++) {
                getElement().refresh();
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.getTextProgramName));
                getProgramName = getElement().getText(mLocator.getTextProgramName);
                if (!getProgramName.isEmpty()) {
                    break;
                }
            }
        }
        boolean isShowCard = getElement().verifyElementPresent(mLocator.cardGroup);
        boolean isShowPaymentInformation = getElement().verifyElementPresent(mLocator.paymentInformationPrakerja);
        logAndAssertTrue(isShowPaymentInformation && isShowCard, "show payment information");
        int getSizeInstruction = getElement().getSize(mLocator.getSizePaymentInstruction);
        for (int i = 1; i <= getSizeInstruction; i++) {
            String getTextInstruction = getElement().getText(mLocator.getTextPaymentInstruction(i));
            logPassed("Instruction: " + getTextInstruction);
        }
        String getValuePrakerjaID = getElement().getAttributeFrom(mLocator.inputTextPrakerjaId, "value");
        boolean isDisableButtonRedeem = getValuePrakerjaID.equalsIgnoreCase("") && !getElement().isEnabled(mLocator.buttonContinuePayment);
        logAndAssertTrue(isDisableButtonRedeem, "disable button redeem code before enter code redeem");
        if (paymentPrakerjaModel.helpPrakerja) {
            getHelpPrakerja();
        } else {
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.inputTextPrakerjaId));
            getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.inputTextPrakerjaId));
            getProgramName = getElement().getText(mLocator.getTextProgramName);
            if (!getProgramName.equalsIgnoreCase("")) {
                logPassed("Program name present");
            } else {
                getElement().refresh();
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.inputTextPrakerjaId));
                getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.inputTextPrakerjaId));
            }
            getElement().waitUntilSetText(mLocator.inputTextPrakerjaId, paymentPrakerjaModel.prakerjaID);
            getElement().click(mLocator.buttonContinuePayment);
            if (paymentPrakerjaModel.isInvalidId) {
                String getAlertMessage = getElement().getText(mLocator.errorText);
                logAndAssertEqual("Pastikan sesuai dengan nomor Prakerja Anda (16 digit)", getAlertMessage, "validate input prakerja id" +
                        "</br>depend - KMWA-9226: Checkout - Validate showing alert if user fill field Nomor Kartu Prakerja then click button Lanjutkan Pembayaran but Nomor Kartu Prakerja is less than 16 digit" +
                        "</br>depend - KMWA-6748: Prakerja Offline - If user input wrong number \"nomor kartu prakerja\" can show alert \"Nomor harus sama seperti pada kartu (16 digit).\"");
                return;
            }
            handleError400(paymentPrakerjaModel);
        }
    }

    @Override
    public void inputOtpPrakerja(PaymentPrakerjaModel paymentPrakerjaModel) {
        inputOtp(paymentPrakerjaModel);
        stepsHelper.delay(globalVariable.midDelay);
        if (getElement().verifyElementPresent(mLocator.failedBookingQuota)) {
            getElement().click(mLocator.iconCloseModal);
        }
        if (getElement().verifyElementPresent(mLocator.errorGeneral)) {
            getElement().click(mLocator.buttonBackErrorGeneral);
            getElement().click(mLocator.buttonCloseModalBox);
            getElement().refresh();
            getElement().setText(mLocator.inputTextPrakerjaId, paymentPrakerjaModel.prakerjaID);
            getElement().click(mLocator.buttonContinuePayment);
            inputOtp(paymentPrakerjaModel);
            if (getElement().verifyElementPresent(mLocator.failedBookingQuota)) {
                getElement().click(mLocator.buttonBackToProgram);
            }
        }
    }

    @Override
    public void redeemCode(PaymentPrakerjaModel paymentPrakerjaModel) {
        String winHandleBefore = webDriver.getWindowHandle();
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.closeButtonPopupRedeem));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.closeButtonPopupRedeem));
        if (paymentPrakerjaModel.closeRedeem) {
            getElement().click(mLocator.closeButtonPopupRedeem);
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.closeButtonPopupRedeem));
            boolean isCloseRedeem = getElement().verifyElementNotPresent(mLocator.closeButtonPopupRedeem);
            logAndAssertTrue(isCloseRedeem, "close popup redeem code");
            return;
        }
        String getValue = getElement().getAttributeFrom(mLocator.inputTextRedeemCode, "value");
        boolean isValidateRedeem = getValue.equalsIgnoreCase("") && !getElement().isEnabled(mLocator.buttonSubmitRedeem);
        logAndAssertTrue(isValidateRedeem, "validate before redeem");
        if (paymentPrakerjaModel.isRedeem) {
            getElement().waitUntilSetText(mLocator.inputTextPrakerjaId, paymentPrakerjaModel.redeemCodeSuccess);
            if (paymentPrakerjaModel.isDeleteInputCode) {
                getElement().waitUntilClick(mLocator.iconClearRedeemCode);
                getValue = getElement().getAttributeFrom(mLocator.inputTextPrakerjaId, "value");
                boolean isDeleteRedeemCode = getValue.equalsIgnoreCase("") && !getElement().isEnabled(mLocator.buttonSubmitRedeem);
                logAndAssertTrue(isDeleteRedeemCode, "delete redeem code");
            } else {
                getElement().waitUntilClick(mLocator.buttonSubmitRedeem);
                if (paymentPrakerjaModel.isValid) {
                    switchWindowHandle();
                    getElement().waitUntilClick(mLocator.buttonGoToFaceRecognition);
                    switchWindowHandle();
                    getElement().waitUntilClick(mLocator.buttonRedirectAfterFaceRecognition);
                    getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonRedirectAfterFaceRecognition));
                    webDriver.switchTo().window(winHandleBefore);
                    getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.alertSuccessEnroll));
                    boolean isShowSuccessRedeem = getElement().verifyElementPresent(mLocator.alertSuccessEnroll);
                    logAndAssertTrue(isShowSuccessRedeem, "redeem code prakerja");
                } else if (getElement().verifyElementPresent(mLocator.alertErrorRedeemCode)) {
                    String getMessage = getElement().getText(mLocator.alertErrorRedeemCode).replaceAll("Prakerja: ", "");
                    logAndAssertEqual(paymentPrakerjaModel.errorMessage.toLowerCase(), getMessage.toLowerCase(), "show error message");
                }
            }
        }
    }

    @Override
    public void verifyDetailTransactionPrakerja() {
        try {
            getElement().waitUntilClick(mLocator.buttonDetailCard);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonInvoice));
            getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonInvoice));
            boolean isShowDetail = getElement().verifyElementPresent(mLocator.groupName) && getElement().verifyElementPresent(mLocator.creatorCardVoucher);
            logAndAssertTrue(isShowDetail, "show detail transaction");
        } catch (TimeoutException t) {
            logAndAssertFalse(true, "show detail card");
        }
    }

    private void inputOtp(PaymentPrakerjaModel paymentPrakerjaModel) {
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.inputOtp(1)));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.inputOtp(1)));
        String getOtp = paymentPrakerjaModel.otpPrakerja;
        for (int i = 1; i <= getOtp.length(); i++) {
            String otp = Character.toString(getOtp.charAt(i - 1));
            /* ini ada kebutuhan di delay supaya tidak kena validasi error */
            stepsHelper.delay(1000);
            getElement().waitUntilSetText(mLocator.inputOtp(i), otp);
        }
    }

    private void getHelpPrakerja() {
        String mainWindow = webDriver.getWindowHandle();
        getElement().click(mLocator.textFaqPrakerja);
        getElement().switchWindow();
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.iconSearch));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.iconSearch));
        String getTitlePage = webDriver.getTitle();
        logAndAssertEqual("Karier.mu & Karier.mu Prakerja - Panduan Karier.mu", getTitlePage, "switch window");
        webDriver.close();
        webDriver.switchTo().window(mainWindow);
    }

    private void handleError400(PaymentPrakerjaModel paymentPrakerjaModel) {
        try {
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.inputOtp(1)));
        } catch (TimeoutException timeoutException) {
            if (getElement().verifyElementPresent(mLocator.errorGeneral)) {
                getElement().click(mLocator.buttonBackErrorGeneral);
                getElement().refresh();
                getElement().waitUntilSetText(mLocator.inputTextPrakerjaId, paymentPrakerjaModel.prakerjaID);
                getElement().click(mLocator.buttonContinuePayment);
                if (paymentPrakerjaModel.isInvalidId) {
                    String getAlertMessage = getElement().getText(mLocator.errorText);
                    logAndAssertEqual("Pastikan sesuai dengan nomor Prakerja Anda (16 digit).", getAlertMessage, "validate input prakerja id" +
                            "</br>depend - KMWA-9224: Checkout - Validate showing alert if user fill field Nomor Kartu Prakerja then click button Lanjutkan Pembayaran but Nomor Kartu Prakerja is less than 16 digit");
                }
            }
        }
    }

    private void switchWindowHandle() {
        // tunggu sampai window barunya kebuka
        stepsHelper.delay(globalVariable.midDelay);
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
        }
    }
}