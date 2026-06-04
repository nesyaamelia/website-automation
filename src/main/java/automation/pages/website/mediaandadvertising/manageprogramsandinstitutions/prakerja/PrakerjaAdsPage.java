package automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.prakerja;

import automation.pages.base.BaseDashboardPage;
import automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.database.ManageProgramInstitutionDBHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PrakerjaAdsPage extends BaseDashboardPage<PrakerjaAdsModel, PrakerjaAdsLocator> implements PrakerjaAdsSteps {

    public static PrakerjaAdsPage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new PrakerjaAdsPage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = PrakerjaAdsModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = PrakerjaAdsLocator.newInstance();
    }

    @Override
    public PrakerjaAdsPage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void selectSection(PrakerjaAdsModel prakerjaAdsModel){
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.selectPlatform));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.selectPlatform));
        boolean isPlatformClickable = getElement().isClickable(mLocator.selectPlatform);
        logAndAssertTrue(isPlatformClickable, "Click platform card");
        getElement().click(mLocator.selectPlatform);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.selectPage));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.selectPage));
        stepsHelper.selectCustomDropdown(mLocator.selectPage, mLocator.inputPage, prakerjaAdsModel.pageType.page);
        getElement().waitUntilClick(mLocator.selectSection);
        getElement().waitUntilClick(mLocator.inputSection(prakerjaAdsModel.sectionType.section));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.tickerInfo));
        boolean isTickerAppear = getElement().verifyElementPresent(mLocator.tickerInfo);
        logAndAssertTrue(isTickerAppear, "Ticker successfully appear" +
                "</br> depend - ADKR-1446: Ensure dropdown field Halaman able to click" +
                "</br> depend - ADKR-1447: Ensure success select Beranda on dropdown field Halaman" +
                "</br> depend - ADKR-1449: Ensure success select Program Unggulan on dropdown field Section");
    }

    @Override
    public void addProgram(PrakerjaAdsModel prakerjaAdsModel){
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.selectSearchProgram));
        removeExistProgram(prakerjaAdsModel.programName);
        stepsHelper.scrollToTop();
        stepsHelper.selectCustomDropdown(mLocator.selectSearchProgram, mLocator.dropdownInput, prakerjaAdsModel.programName);
        getElement().clickHandlerJs(mLocator.addProgram);
        getElement().clickHandlerJs(mLocator.buttonSave);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupSaveChanges));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonSaveOnPopup));
        getElement().clickHandlerJs(mLocator.buttonSaveOnPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.alertSnackbar));
        boolean isSnackbarAppear = getElement().verifyElementPresent(mLocator.alertSnackbar);
        logAndAssertTrue(isSnackbarAppear, "Snackbar Perubahan berhasil disimpan appear" +
                "</br> depend - ADKR-1458: Ensure success add program on section program unggulan" +
                "</br> depend - ADKR-1487: Ensure button Simpan Perubahan able to click" +
                "</br> depend - ADKR-1488: Ensure button Simpan on popup simpan perubahan able to click");
        String webModel = prakerjaAdsModel.programName;
        String list = ManageProgramInstitutionDBHelper.getProgramName(prakerjaAdsModel.programName);
        logAndAssertEqual(webModel, list, "validate program name");
    }

    @Override
    public void removeProgram(PrakerjaAdsModel prakerjaAdsModel){
        getElement().clickHandlerJs(mLocator.buttonDeleteOnList);
        boolean isEnabled = getElement().verifyElementPresent(mLocator.buttonDeleteOnList);
        logAndAssertTrue(isEnabled, "button hapus able to click");
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupRemoveProgram));
        getElement().clickHandlerJs(mLocator.buttonDeleteOnPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonSave));
        boolean isButtonSaveOn = getElement().verifyElementPresent(mLocator.buttonSave);
        logAndAssertTrue(isButtonSaveOn, "Button save on after delete program");
    }

    @Override
    public void adsProgram(){
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonAds(1)));
        getElement().scrollToElement(mLocator.buttonAds(1));
        getElement().click(mLocator.buttonAds(1));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupAds));
        getElement().clickHandlerJs(mLocator.buttonAdsOnPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.tagAds));
        boolean isTagAppear = getElement().verifyElementPresent(mLocator.tagAds);
        logAndAssertTrue(isTagAppear, "Tagging Ad successfully appear" +
                "</br> depend - ADKR-1472: Ensure button Ads on program unggulan table able to click" +
                "</br> depend - ADKR-2474: Ensure button Iklankan on Popup Iklan Program Unggulan able to click");
        getElement().click(mLocator.buttonSave);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupSaveChanges));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonSaveOnPopup));
        getElement().click(mLocator.buttonSaveOnPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.alertSnackbar));
    }

    @Override
    public void removeAllProgramAndAddProgram(PrakerjaAdsModel prakerjaAdsModel){
        getElement().clickHandlerJs(mLocator.buttonRemoveAll);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupRemoveAll));
        getElement().clickHandlerJs(mLocator.buttonDeleteOnPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.emptyState));
        boolean isShownEmptyState = getElement().verifyElementPresent(mLocator.emptyState);
        logAndAssertTrue(isShownEmptyState, "Successfully remove all program" + prakerjaAdsModel.logDepend);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.selectSearchProgram));
        stepsHelper.selectCustomDropdown(mLocator.selectSearchProgram, mLocator.dropdownInput, prakerjaAdsModel.programName);
        getElement().clickHandlerJs(mLocator.addProgram);
        getElement().clickHandlerJs(mLocator.buttonSave);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupSaveChanges));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonSaveOnPopup));
        getElement().clickHandlerJs(mLocator.buttonSaveOnPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.alertSnackbar));
    }

    @Override
    public void cancelRemoveProgram(PrakerjaAdsModel prakerjaAdsModel){
        getElement().clickHandlerJs(mLocator.buttonRemoveAll);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupRemoveAll));
        getElement().clickHandlerJs(mLocator.buttonCancelOnPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.listProgramOnTable(0)));
        boolean isProgramStillAppear = getElement().verifyElementPresent(mLocator.listProgramOnTable(0));
        logAndAssertTrue(isProgramStillAppear, "Success cancel remove program from list");
    }

    private void removeExistProgram(String programName) {
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.waitTable));
        int totalRows = getElement().getSize(mLocator.totalRow);
        for (int i = 1; i <= totalRows; i++) {
            String getName = getElement().getText(mLocator.listProgramName(i));
            if (programName.equalsIgnoreCase(getName)) {
                getElement().clickHandlerJs(mLocator.listProgramDelete(i));
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupRemoveProgram));
                getElement().clickHandlerJs(mLocator.buttonDeleteOnPopup);
                break;
            }
        }
    }
}
