package automation.pages.website.manageprofile;

import automation.config.Host;
import automation.data.User;
import automation.network.kelolauser.response.CreateUserResponse;
import automation.pages.base.BasePage;
import automation.pages.website.accountmenu.AccountMenuPage;
import automation.pages.website.accountmenu.AccountMenuStep;
import automation.pages.website.accountmenu.AccountMenuType;
import automation.pages.website.homepage.navigationbar.NavigationBarPage;
import automation.pages.website.homepage.navigationbar.NavigationBarSteps;
import automation.pages.website.homepage.navigationbar.NavigationType;
import automation.pages.website.poin.remote.api.PointApi;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static automation.pages.website.manageprofile.EditProfileLocator.*;

public class EditProfilePage extends BasePage<EditProfileModel, EditProfileLocator> implements EditProfileSteps {

    private String getSourceDefaultImage;
    private NavigationBarSteps navigationBarSteps;
    private AccountMenuStep accountMenuStep;

    public static EditProfilePage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new EditProfilePage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = EditProfileModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = EditProfileLocator.newInstance();
    }

    @Override
    public EditProfilePage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        navigationBarSteps = NavigationBarPage.newInstance(webDriver, webDriverWait);
        accountMenuStep = AccountMenuPage.newInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void initReport(ExtentTest extentTest) {
        super.initReport(extentTest);
        navigationBarSteps.initReport(extentTest);
        accountMenuStep.initReport(extentTest);
    }

    @Override
    public void fillPrimaryDataUser(EditProfileModel editProfileModel, String email) {
        closePopupInputWhatsapp();
        if (editProfileModel.isEmptyValue) {
            submitAndValidateEmptyValue(mLocator.inputUsername, "validate empty data username");
            submitAndValidateEmptyValue(mLocator.inputFullName, "validate empty data full name");
            if (mModel.selectPrimaryDataUser(email).isEmailVerified && getElement().verifyElementPresent(mLocator.emailVerifiedInfo)) {
                logPassed("Email is verify");
            }
            submitAndValidateEmptyValue(mLocator.inputPhoneNumber, "validate empty data phone number");
        } else {
            if (editProfileModel.isNegativeData) {
                try {
                    getElement().waitUntilClick(mLocator.inputUsername);
                } catch (ElementClickInterceptedException e) {
                    ((JavascriptExecutor) webDriver).executeScript("arguments[0].clck();", getElement().findElement(mLocator.inputUsername));
                }
                fillNegativeData(mLocator.inputFieldValue, editProfileModel.username, "validate input negative username");
                getElement().waitUntilClick(mLocator.inputFullName);
                fillNegativeData(mLocator.inputFieldValue, editProfileModel.fullName, "validate input negative fullname");
                getElement().waitUntilClick(mLocator.inputPhoneNumber);
                fillNegativeData(mLocator.inputFieldValue, editProfileModel.phoneNumber, "validate input negative phonenumber");
            } else {
                inputFieldPersonalDataUser(mLocator.inputUsername, editProfileModel.username, "username");
                inputFieldPersonalDataUser(mLocator.inputFullName, editProfileModel.fullName, "nama lengkap");
                if (mModel.selectPrimaryDataUser(email).isEmailVerified && getElement().verifyElementPresent(mLocator.emailVerifiedInfo)) {
                    logPassed("Email is verify");
                }
                inputFieldPersonalDataUser(mLocator.inputPhoneNumber, editProfileModel.phoneNumber, "nomor ponsel");
            }
        }
    }

    @Override
    public void fillAdditionalDataUser(EditProfileModel editProfileModel) {
        closePopupInputWhatsapp();
        getElement().clickHandlerJs(mLocator.buttonChangeAdditionalData);
        getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonChangeAdditionalData));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonSubmit));
        boolean isDisabledButtonSubmit = getElement().isDisabled(mLocator.buttonSubmit);
        logAndAssertTrue(isDisabledButtonSubmit, "disable button submit");
        getElement().waitUntilClick(mLocator.containsText(textGender));
        getElement().clickHandlerJs(mLocator.getDropdownOption(textMan));
        getElement().waitUntilClick(mLocator.inputBirthDate);
        getElement().waitUntilClick(mLocator.selectToday);
        getElement().clickHandlerJs(mLocator.inputEducationField);
        getElement().clickHandlerJs(mLocator.getDropdownOption(lastEducation));
        if (editProfileModel.roleType != null && editProfileModel.roleType.equals(UserYearLevelType.PROFESSIONAL)) {
            getElement().clickHandlerJs(mLocator.inputSubjectIndustry);
            getElement().waitUntilSetText(mLocator.inputSubjectIndustry, mModel.industry);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.containsTextWithIndex(textSubjectIndustry, 1)));
            getElement().clickHandlerJs(mLocator.getDropdownOption(textSubjectIndustry));
        }
        getElement().clickHandlerJs(mLocator.inputPostalCode);
        getElement().waitUntilSetText(mLocator.inputPostalCode, editProfileModel.postalCode);

        if (editProfileModel.isNegativeData) {
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.emptyStateData));
            boolean isShowEmptyResult = getElement().verifyElementPresent(mLocator.emptyStateData);
            logAndAssertTrue(isShowEmptyResult, "show empty result");
            return;
        }

        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.containsText(textPostalCode)));
        getElement().clickHandlerJs(mLocator.getDropdownOption(textPostalCode));
        getElement().waitUntilClick(mLocator.inputFullAddress);
        getElement().waitUntilSetText(mLocator.inputFullAddress, editProfileModel.address);
        getElement().waitUntilClick(mLocator.inputAboutMe);
        getElement().waitUntilSetText(mLocator.inputAboutMe, editProfileModel.aboutMe);
        getElement().waitUntilClick(mLocator.buttonSubmit);
        validateSnackbarSuccessEditData("edit additional information");
    }

    @Override
    public void fillPreferencesDataUser(EditProfileModel editProfileModel) {
        boolean isDisableFieldOfStudy = false;
        closePopupInputWhatsapp();
        getElement().clickHandlerJs(mLocator.buttonChangePreference);
        if (editProfileModel.isParseFieldOfStudy) {
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.inputSubjectIndustry));
            String getAttribute = getElement().getAttributeFrom(mLocator.inputSubjectIndustry, "class");
            logAndAssertTrue(getAttribute.contains("selected"), "select field of study from register page");
            return;
        }
        getElement().clickHandlerJs(mLocator.inputSubjectIndustry);
        if (editProfileModel.isInvalidFieldOfStudy) {
            getElement().setText(mLocator.inputSubjectIndustry, editProfileModel.fieldOfStudy);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.emptyStateData));
            boolean showEmptyState = getElement().verifyElementPresent(mLocator.emptyStateData);
            logAndAssertTrue(showEmptyState, "show empty state");
            return;
        }
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.listDownloadFieldOfStudy));
        int getListFieldOfStudy = getElement().getSize(mLocator.listDownloadFieldOfStudy);
        logAndAssertEqual(150, getListFieldOfStudy, "show list field of study" +
                "</br>depend - KMWA-11978: [Profile] Successfully display 150 data entries in the \"Bidang Studi/Industri\" field");
        for (int i = 1; i <= getListFieldOfStudy; i++) {
            if (i > 5) {
                isDisableFieldOfStudy = getElement().verifyElementPresent(mLocator.disableSelectedListFieldOfStudy(i));
            } else {
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.selectListFieldOfStudy(i)));
            }
        }
        logAndAssertTrue(isDisableFieldOfStudy, "field disable after select maximum (5) field of study");
        getElement().clickHandlerJs(mLocator.buttonCloseDropdown);
        getElement().clickHandlerJs(mLocator.inputDistrict);
        getElement().setText(mLocator.inputDistrict, mModel.district);
        String getDistrict = getElement().getAttributeFrom(mLocator.inputDistrict, "value");
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.containsText(getDistrict)));
        getElement().clickHandlerJs(mLocator.getDropdownOption(getDistrict));
        getElement().click(mLocator.inputLearningObjective);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.containsTextWithIndex(selectLearningObjective, 1)));
        getElement().clickHandlerJs(mLocator.getDropdownOption(selectLearningObjective));
        getElement().waitUntilClick(mLocator.buttonSubmit);
        validateSnackbarSuccessEditData("edit preferences information");
    }

    @Override
    public void fillWhatsappNumberOnPopup(EditProfileModel editProfileModel) {
        try {
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonCloseModal));
            getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonCloseModal));
        } catch (TimeoutException t) {
            if (getElement().verifyElementNotPresent(mLocator.buttonCloseModal)) {
                navigationBarSteps.navigationTo(NavigationType.PROGRAMMU);
                closePopupInputWhatsapp();
                accountMenuStep.navigateToMenu(AccountMenuType.LIHAT_PROFIL);
            }
        }
        if (editProfileModel.isCloseWhatsappNumber) {
            closePopupInputWhatsapp();
        } else {
            if (editProfileModel.isRefresh) {
                getElement().refresh();
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.inputWhatsappNumber));
                getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.inputWhatsappNumber));
                boolean inputWhatsappNumber = getElement().verifyElementPresent(mLocator.inputWhatsappNumber);
                logAndAssertTrue(inputWhatsappNumber, "refresh popup whatsapp number");
                return;
            } else if (editProfileModel.isGoBack) {
                getElement().navigateToUrl(Host.getKariermu());
                navigationBarSteps.navigationTo(NavigationType.PROGRAMMU);
                closePopupInputWhatsapp();
                accountMenuStep.navigateToMenu(AccountMenuType.LIHAT_PROFIL);
                getElement().allowPushNotif();
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.inputWhatsappNumber));
                boolean inputWhatsappNumber = getElement().verifyElementPresent(mLocator.inputWhatsappNumber);
                logAndAssertTrue(inputWhatsappNumber, "go back popup whatsapp number");
                return;
            }
            String infoNumber = getElement().getText(mLocator.tickerWhatsappNumber).replaceAll("\\D", "");
            if (!editProfileModel.isGetWhatsappNumber) {
                getElement().setText(mLocator.inputWhatsappNumber, editProfileModel.whatsappNumber);
                if (Pattern.matches("\\d+", editProfileModel.whatsappNumber) || Pattern.matches("\\W+", editProfileModel.whatsappNumber)) {
                    getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.alertErrorInputField));
                    String errorMessage = getElement().getText(mLocator.alertErrorInputField);
                    logAndAssertEqual("Format tidak sesuai. Periksa dan coba lagi.", errorMessage, "handle alert error input whatsapp number");
                } else {
                    logFailed("pattern not equals", null);
                    boolean inputWhatsappNumber = getElement().verifyElementNotPresent(mLocator.alertErrorInputField);
                    logAndAssertTrue(inputWhatsappNumber, "pattern not equals");
                }
                return;
            } else {
                getElement().setText(mLocator.inputWhatsappNumber, infoNumber);
            }
            getElement().waitUntilClick(mLocator.buttonSubmitWhatsappNumber);
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonSubmitWhatsappNumber));
            getElement().refresh();
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonChangeAdditionalData));
            getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonChangeAdditionalData));
            boolean successSavePhoneNumber = getElement().verifyElementNotPresent(mLocator.inputWhatsappNumber);
            logAndAssertTrue(successSavePhoneNumber, "save input whatsapp number" + editProfileModel.logDepend);
        }
    }

    @Override
    public void validateFieldNIK(EditProfileModel editProfileModel) {
        closePopupInputWhatsapp();
        boolean isHideModalContent = getElement().verifyElementNotPresent(mLocator.modalContent);
        logAndAssertTrue(isHideModalContent, "hide modal content on profile");
        ArrayList<String> listField = new ArrayList<>();
        int getSizeFieldLabel = getElement().getSize(mLocator.listLabelEditProfile);
        for (int i = 1; i <= getSizeFieldLabel; i++) {
            String getLabelName = getElement().getText(mLocator.getLabelNameEditProfile(i));
            listField.add(getLabelName);
        }
        logAndAssertEqual(String.valueOf(mModel.validateNik), String.valueOf(listField), "field equals");
    }

    @Override
    public void changePhotoProfileUser(EditProfileModel editProfileModel) {
        closePopupInputWhatsapp();
        getSourceDefaultImage = getElement().getAttributeFrom(mLocator.buttonUploadPhotoProfile, "src");
        getElement().waitUntilClick(mLocator.iconChangeImage);
        stepsHelper.delay(globalVariable.midDelay);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.uploadImage));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.uploadImage));
        getElement().dropFile(new File(System.getProperty("user.dir") + editProfileModel.pathImage), getElement().findElement(mLocator.uploadImage));
    }

    @Override
    public void verifyValidUploadImageUser() {
        try {
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonCropImage));
        } catch (TimeoutException t) {
            logInfo("button crop not appears");
        }
        getElement().click(mLocator.buttonCropImage);
        getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.modalBody));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonChangeAdditionalData));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonChangeAdditionalData));
        String getNewSourceDefaultImage = getElement().getAttributeFrom(mLocator.buttonUploadPhotoProfile, "src");
        boolean isUploadAvatar = !getSourceDefaultImage.equalsIgnoreCase(getNewSourceDefaultImage);
        logAndAssertTrue(isUploadAvatar, "upload avatar" +
                "</br>depend - KMWA - 6419: [Edit Profile] Verify user success edit photo avatar");
    }

    @Override
    public void verifyInvalidUploadImageUser() {
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.alertDanger));
        boolean isDangerAlert = getElement().verifyElementPresent(mLocator.alertDanger);
        logAndAssertTrue(isDangerAlert, "validate upload image");
    }

    @Override
    public void cancelEditPrimaryDataUser(EditProfileModel editProfileModel) {
        String getCurrentUrl;
        closePopupInputWhatsapp();
        getElement().waitUntilClick(mLocator.inputUsername);
        getElement().waitUntilClick(mLocator.buttonCloseModal);
        getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonCloseModal));
        getCurrentUrl = getElement().getUrl().replaceAll(Host.getKariermuApp(), "");
        logAndAssertEqual("account/personal", getCurrentUrl, "back to edit profile page");
        getElement().waitUntilClick(mLocator.inputFullName);
        getElement().waitUntilClick(mLocator.buttonCloseModal);
        getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonCloseModal));
        getCurrentUrl = getElement().getUrl().replaceAll(Host.getKariermuApp(), "");
        logAndAssertEqual("account/personal", getCurrentUrl, "back to edit profile page");
        getElement().waitUntilClick(mLocator.inputPhoneNumber);
        getElement().waitUntilClick(mLocator.buttonCloseModal);
        getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonCloseModal));
        getCurrentUrl = getElement().getUrl().replaceAll(Host.getKariermuApp(), "");
        logAndAssertEqual("account/personal", getCurrentUrl, "back to edit profile page");
    }

    @Override
    public void createPinUser(CreateUserResponse createUserResponse) {
        PointApi pointApi = new PointApi();
        String status = pointApi.poinUserCreatePinUser(createUserResponse.data.email);
        logAndAssertEqual("ok", status.toLowerCase(), "create pin");
    }

    @Override
    public void updatePinUser(EditProfileModel editProfileModel) {
        closePopupInputWhatsapp();
        getElement().waitUntilClick(mLocator.showMenuSecurityAccount);
        getElement().waitUntilClick(mLocator.menuSettingPin);
        if (editProfileModel.isInvalidPin) {
            getElement().waitUntilClick(mLocator.buttonEditPin);
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonEditPin));
            for (int i = 1; i <= 5; i++) {
                stepsHelper.delay(globalVariable.shortDelay);
                for (int j = 0; j < 6; j++) {
                    String pin = Character.toString(editProfileModel.inputPinUser.charAt(i - 1));
                    getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.inputPinPoint(j)));
                    getElement().setText(mLocator.inputPinPoint(j), pin);
                    if (j == 5) {
                        getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.filledLatestPin));
                    }
                }
            }
            getElement().waitUntilClick(mLocator.buttonErrorMessageModal);
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonErrorMessageModal));
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.countdownTimer));
            getElement().refresh();
            closePopupInputWhatsapp();
            if (getElement().verifyElementPresent(mLocator.buttonEditPin)) {
                getElement().waitUntilClick(mLocator.buttonEditPin);
                getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonEditPin));
            }
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.countdownTimer));
            boolean showTimerFreezeInputPin = getElement().verifyElementPresent(mLocator.countdownTimer);
            logAndAssertTrue(showTimerFreezeInputPin, "show timer freeze input pin");
        }
    }

    private void closePopupInputWhatsapp() {
        if (getElement().verifyElementPresent(mLocator.buttonCloseModal)) {
            getElement().click(mLocator.buttonCloseModal);
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonCloseModal));
            boolean isClosePopup = getElement().verifyElementNotPresent(mLocator.buttonCloseModal);
            logAndAssertTrue(isClosePopup, "close popup whatsapp");
        } else {
            logInfo("Popup whatsapp not appears");
        }
    }

    private void inputFieldPersonalDataUser(By by, String value, String fieldName) {
        fillFieldPrimary(by, value);
        try {
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonModalSubmit));
            boolean isShowSnackbarSuccess = getElement().verifyElementPresent(mLocator.snackbarSuccess);
            logAndAssertTrue(isShowSnackbarSuccess, "update " + fieldName);
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.snackbarSuccess));
        } catch (TimeoutException t) {
            logInfo("Button submit still appears");
            if (getElement().verifyElementPresent(mLocator.alertErrorInputField)) {
                String getErrorMessage = getElement().getText(mLocator.alertErrorInputField);
                logInfo("Message: " + getErrorMessage);
                if (getErrorMessage.equalsIgnoreCase("Username sudah digunakan. Silakan gunakan username lain.")) {
                    getElement().refresh();
                    getElement().waitUntilClick(mLocator.inputUsername);
                    getElement().clearText(mLocator.inputFieldValue);
                    User userModel = new User();
                    String username = userModel.firstName + userModel.lastName + userModel.firstName + "New1";
                    getElement().waitUntilSetText(mLocator.inputFieldValue, username);
                    getElement().waitUntilClick(mLocator.buttonModalSubmit);
                    try {
                        getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonModalSubmit));
                        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.snackbarSuccess));
                        boolean isShowSnackbarSuccess = getElement().verifyElementPresent(mLocator.snackbarSuccess);
                        logAndAssertTrue(isShowSnackbarSuccess, "update " + fieldName);
                        getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.snackbarSuccess));
                    } catch (TimeoutException p) {
                        loopingHandleError400(by, value, fieldName);
                    }
                } else {
                    logFailed("Message: " + getErrorMessage, null);
                }
            } else {
                loopingHandleError400(by, value, fieldName);
            }
        }
    }

    private void loopingHandleError400(By by, String value, String fieldName) {
        for (int i = 1; i <= 5; i++) {
            getElement().click(mLocator.buttonCloseModal);
            fillFieldPrimary(by, value);
            try {
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.snackbarSuccess));
                boolean isShowSnackbarSuccess = getElement().verifyElementPresent(mLocator.snackbarSuccess);
                logAndAssertTrue(isShowSnackbarSuccess, "update " + fieldName);
                getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.snackbarSuccess));
                break;
            } catch (TimeoutException o) {
                logInfo("Popup still appears, go to loop for handling error 400");
            }
        }
    }

    private void fillFieldPrimary(By by, String value) {
        try {
            getElement().waitUntilClick(by);
        } catch (StaleElementReferenceException | TimeoutException | ElementClickInterceptedException c) {
            if (getElement().verifyElementNotPresent(by)) {
                getElement().refresh();
                getElement().waitUntilClick(by);
            } else {
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(by));
            }
        }
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonModalSubmit));
        getElement().clearText(mLocator.inputFieldValue);
        getElement().waitUntilSetText(mLocator.inputFieldValue, value);
        getElement().waitUntilClick(mLocator.buttonModalSubmit);
    }

    private void validateSnackbarSuccessEditData(String message) {
        boolean isShowSnackbarSuccess = false;
        try {
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.snackbarSuccess));
            isShowSnackbarSuccess = getElement().verifyElementPresent(mLocator.snackbarSuccess);
            logAndAssertTrue(isShowSnackbarSuccess, message);
        } catch (TimeoutException t) {
            logAndAssertFalse(isShowSnackbarSuccess, message);
        }
    }

    private void submitAndValidateEmptyValue(By by, String message) {
        try {
            getElement().waitUntilClick(by);
        } catch (StaleElementReferenceException s) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(by));
        }
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.inputFieldValue));
        getElement().clearText(mLocator.inputFieldValue);
        String getAttributeInput = getElement().getAttributeFrom(mLocator.inputFieldValue, "value");
        boolean isButtonDisabled = getElement().isDisabled(mLocator.buttonModalSubmit);
        logAndAssertTrue(getAttributeInput.equalsIgnoreCase("") && isButtonDisabled, message);
        getElement().click(mLocator.buttonCloseModal);
    }

    private void fillNegativeData(By by, String value, String log) {
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(by));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(by));
        getElement().clearText(by);
        getElement().waitUntilSetText(by, value);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.alertErrorInputField));
        boolean isAlertError = getElement().verifyElementPresent(mLocator.alertErrorInputField);
        logAndAssertTrue(isAlertError, log);
        getElement().click(mLocator.buttonCloseModal);
    }
}