package automation.pages.website.manageprofile.workplacedata;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class WorkplaceLocator extends BaseLocator {

    public static final String textSubjectIndustry = "Teknologi";
    public static final String textApply = "Terapkan";

    public final By inputSubjectIndustry = getElementById("subject-industry");
    public final By buttonInstitution = getElementById("desktop-instituion-button");
    public final By buttonEditInstitution = getElementById("edit-institution");
    public final By inputInstitution = getElementById("institution");
    public final By inputNuptk = getElementById("nuptk");
    public final By inputYearEntryStudy = getElementById("entry-year");
    public final By inputNim = getElementById("nim");
    public final By snackbarSuccess = getElementByClassName("snackbar-atom-wrapper--success");
    public final By buttonSubmit = getElementById("mobile-submit-button");
    public final By buttonCloseModal = getElementByClassName("s-modal-close-icon");
    public final By dataNotFound = getElementByXpath("(//*[@class=\"not-found\"])[1]");
    public final By inputNutpk = getElementByXpath("//*[@class=\"k-input full-width\"]/div/input");
    public final By inputWrapper = getElementByXpath("//*[@class=\"dp__input_wrap\"]/div/div/input");
    public final By dropdownNutpk = getElementByXpath("(//*[contains(@class, \"dp__overlay_cell\")])[1]");

    public static WorkplaceLocator newInstance() {
        return new WorkplaceLocator();
    }

    public By containsText(String text) {
        return getElementByXpath("//*[contains(text(), \"" + text + "\")]");
    }

    public By containsTextWithIndex(String text, int i) {
        return getElementByXpath("(//*[contains(text(), \"" + text + "\")])[" + i + "]");
    }

    public By selectYear(int year) {
        return getElementByXpath("//*[@data-year=\"" + year + "\"]");
    }
}
