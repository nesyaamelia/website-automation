package automation.pages.website.manageprofile;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class EditProfileLocator extends BaseLocator {

    public static final String textGender = "Jenis Kelamin";
    public static final String textMan = "Laki-laki";
    public static final String lastEducation = "S1";
    public static final String textSubjectIndustry = "Teknologi";
    public static final String textPostalCode = "16421";
    public static final String selectLearningObjective = "Meningkatkan jenjang karier";

    public final By modalContent = getElementByXpath("//*[@class=\"modal-content\"]");
    public final By inputEmail = getElementById("input-email");
    public final By textYear = getElementByXpath("//*[text()=\"1993\"]");
    public final By textMonth = getElementByXpath("//*[text()=\"Nov\"]");
    public final By textDate = getElementByXpath("//*[text()=\"16\"]");
    public final By alert = getElementByXpath("//*[@role=\"alert\"]");
    public final By uploadImage = getElementById("button-pick-photo");
    public final By formType = getElementById("input-form_type");
    public final By iconChangeImage = getElementByXpath("//*[@class=\"badge badge-on-image\"]");
    public final By buttonCropImage = getElementById("button-crop");
    public final By modalBody = getElementByXpath("//*[@class=\"modal-body\"]");
    public final By imageUser = getElementByXpath("//*[@id=\"avatar-button\"]/div/img");
    public final By textLihatProfil = getElementById("account-settings");
    public final By inputWhatsappNumber = By.id("input-whatsapp");
    public final By buttonCloseModal = By.className("s-modal-close-icon");
    public final By tickerWhatsappNumber = By.className("is-info");
    public final By alertErrorInputField = By.xpath("//*[@class=\"s-input-hint is-error\"]");
    public final By buttonSubmitWhatsappNumber = By.id("web-submit-input-whatsapp");
    public final By inputFieldValue = getElementByClassName("s-input-element");
    public final By buttonChangeAdditionalData = getElementById("change-additional-data");
    public final By buttonSubmit = getElementById("mobile-submit-button");
    public final By inputEducationField = getElementById("education");
    public final By inputSubjectIndustry = getElementById("subject-industry");
    public final By inputPostalCode = getElementById("postal-code");
    public final By inputFullAddress = getElementById("full-address");
    public final By inputAboutMe = getElementById("about-me");
    public final By emailVerifiedInfo = getElementByClassName("user-info-email-verified-badge");
    public final By buttonChangePreference = getElementById("change-preferences");
    public final By inputDistrict = getElementById("district");
    public final By inputLearningObjective = getElementById("learning-objective");
    public final By listLabelEditProfile = getElementByXpath("//*[@class=\"font-paragraph-5 label-field\"]");
    public final By snackbarSuccess = getElementByClassName("snackbar-atom-wrapper--success");
    public final By buttonModalSubmit = getElementById("modal-submit-button");
    public final By buttonPrevious = getElementByClassName("mx-btn-icon-double-left");
    public final By buttonCurrentYear = getElementByClassName("mx-btn-current-year");
    public final By buttonBirthDate = getElementById("birth-date");
    public final By inputBirthDate = getElementById("s-input");
    public final By getYearOnTable = getElementByXpath("//*[@class=\"mx-table mx-table-year\"]");
    public final By buttonUploadPhotoProfile = getElementByXpath("(//*[@alt=\"avatar\"])[4]");
    public final By alertDanger = getElementByClassName("alert-danger");
    public final By inputUsername = getElementById("edit-personal-username");
    public final By inputFullName = getElementById("edit-personal-name");
    public final By inputPhoneNumber = getElementById("edit-personal-phone");
    public final By buttonApplyDate = getElementByXpath("//div[contains(text(), 'Terapkan')]/..");
    public final By buttonCloseDropdown = getElementByXpath("//div[@class = 's-dropdown-options-wrapper hard-shadow']/preceding-sibling::div//img");
    public final By emptyStateData = getElementByXpath("(//*[@class=\"not-found\"])[1]");
    public final By listDownloadFieldOfStudy = getElementByXpath("//*[@class=\"s-dropdown-options combobox\"]/div/label");
    public final By showMenuSecurityAccount = getElementById("collapse-security");
    public final By menuSettingPin = getElementById("desktop-pin-setting-button");
    public final By buttonEditPin = getElementById("edit-pin");
    public final By filledLatestPin = getElementByXpath("(//*[contains(@class, \"pin-input single-input form-control filled\")])[6]");
    public final By buttonErrorMessageModal = getElementById("error-message-modal-close-button");
    public final By countdownTimer = getElementByClassName("s-countdown-timer");
    public final By selectToday = getElementByClassName("dp__today");

    public static EditProfileLocator newInstance() {
        return new EditProfileLocator();
    }

    public By containsText(String text) {
        return getElementByXpath("//*[contains(text(), \"" + text + "\")]");
    }

    public By getDropdownOption(String option){
        return getElementByXpath("//span[contains(text(), '"+option+"')]/following-sibling::input | //label[text() = '"+option+"']/../..");
    }

    public By containsTextWithIndex(String text, int i) {
        return getElementByXpath("(//*[contains(text(), \"" + text + "\")])[" + i + "]");
    }

    public By getLabelNameEditProfile(int i) {
        return getElementByXpath("(//*[@class=\"font-paragraph-5 label-field\"])[" + i + "]");
    }

    public By disableSelectedListFieldOfStudy(int i) {
        return getElementByXpath("//*[@class=\"s-dropdown-options combobox\"]/div/label[" + i + "][contains(@class, \"is-disabled\")]");
    }

    public By selectListFieldOfStudy(int i) {
        return getElementByXpath("//*[@class=\"s-dropdown-options combobox\"]/div/label[" + i + "]/input");
    }

    public By inputPinPoint(int j) {
        return getElementByXpath("//*[@id=\"pin-input-" + j + "\"]");
    }
}