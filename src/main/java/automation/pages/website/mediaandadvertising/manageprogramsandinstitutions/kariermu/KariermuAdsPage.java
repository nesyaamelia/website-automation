package automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.kariermu;

import automation.pages.base.BaseDashboardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KariermuAdsPage extends BaseDashboardPage<KariermuAdsModel, KariermuAdsLocator> implements KariermuAdsSteps {

    public static KariermuAdsPage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new KariermuAdsPage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = KariermuAdsModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = KariermuAdsLocator.newInstance();
    }

    @Override
    public KariermuAdsPage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void selectSection(KariermuAdsModel kariermuAdsModel){
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.selectPlatform));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.selectPlatform));
        boolean isPlatformClickable = getElement().isClickable(mLocator.selectPlatform);
        logAndAssertTrue(isPlatformClickable, "Click platform card");
        getElement().click(mLocator.selectPlatform);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.selectPage));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.selectPage));
        stepsHelper.selectCustomDropdown(mLocator.selectPage, mLocator.inputPage, kariermuAdsModel.pageType.page);
        getElement().waitUntilClick(mLocator.selectSection);
        getElement().waitUntilClick(mLocator.inputSection(kariermuAdsModel.sectionType.section));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.tickerInfo));
        boolean isTickerAppear = getElement().verifyElementPresent(mLocator.tickerInfo);
        logAndAssertTrue(isTickerAppear, "Ticker successfully appear" +
                "</br> depend - ADKR-1446: Ensure dropdown field Halaman able to click" +
                "</br> depend - ADKR-1447: Ensure success select Beranda on dropdown field Halaman" +
                "</br> depend - ADKR-1449: Ensure success select Program Unggulan on dropdown field Section");
    }

    @Override
    public void addProgram(KariermuAdsModel kariermuAdsModel){
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.selectSearchProgram));
        removeExistProgram(kariermuAdsModel.programName);
        stepsHelper.scrollToTop();
        stepsHelper.selectCustomDropdown(mLocator.selectSearchProgram, mLocator.dropdownInput, kariermuAdsModel.programName);
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
    }

    @Override
    public void removeProgram(KariermuAdsModel kariermuAdsModel){
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
    public void nonAdsProgram(){
        getElement().clickHandlerJs(mLocator.buttonAds(1));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupAds));
        getElement().clickHandlerJs(mLocator.buttonAdsOnPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.tagAds));
        getElement().click(mLocator.buttonSave);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupSaveChanges));
        getElement().clickHandlerJs(mLocator.buttonSaveOnPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.alertSnackbar));
        getElement().clickHandlerJs(mLocator.buttonNonAds);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupNonAds));
        getElement().clickHandlerJs(mLocator.buttonNonAdsOnPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonAds(1)));
        boolean isButtonAdsOn = getElement().verifyElementPresent(mLocator.buttonAds(1));
        logAndAssertTrue(isButtonAdsOn, "Successfully take down ads" +
                "</br> depend - ADKR-1478: Ensure button Turunkan Ads on program unggulan table able to click" +
                "</br> depend - ADKR-1482: Ensure button Turunkan on Popup Turunkan Iklan Program Unggulan able to click");
        getElement().click(mLocator.buttonSave);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupSaveChanges));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonSaveOnPopup));
        getElement().click(mLocator.buttonSaveOnPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.alertSnackbar));
    }

    @Override
    public void removeAllProgramAndAddProgram(KariermuAdsModel kariermuAdsModel){
        getElement().clickHandlerJs(mLocator.buttonRemoveAll);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupRemoveAll));
        getElement().clickHandlerJs(mLocator.buttonDeleteOnPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.emptyState));
        boolean isShownEmptyState = getElement().verifyElementPresent(mLocator.emptyState);
        logAndAssertTrue(isShownEmptyState, "Successfully remove all program" + kariermuAdsModel.logDepend);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.selectSearchProgram));
        stepsHelper.selectCustomDropdown(mLocator.selectSearchProgram, mLocator.dropdownInput, kariermuAdsModel.programNames);
        getElement().clickHandlerJs(mLocator.addProgram);
        getElement().clickHandlerJs(mLocator.buttonSave);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupSaveChanges));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonSaveOnPopup));
        getElement().clickHandlerJs(mLocator.buttonSaveOnPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.alertSnackbar));
    }

    @Override
    public void cancelRemoveProgram(KariermuAdsModel kariermuAdsModel){
        getElement().clickHandlerJs(mLocator.buttonRemoveAll);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupRemoveAll));
        getElement().clickHandlerJs(mLocator.buttonCancelOnPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.listProgramOnTable(0)));
        boolean isProgramStillAppear = getElement().verifyElementPresent(mLocator.listProgramOnTable(0));
        logAndAssertTrue(isProgramStillAppear, "Success cancel remove program from list");
    }

    @Override
    public void cancelOnPopupAdsProgram(){
        getElement().clickHandlerJs(mLocator.buttonAds(1));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.popupAds));
        getElement().clickHandlerJs(mLocator.buttonCancelAdsOnPopup);
        boolean isButtonSaveDisable = getElement().verifyElementPresent(mLocator.buttonSave);
        logAndAssertTrue(isButtonSaveDisable, "Button save disable to click" +
                "</br> depend - ADKR-1476: Ensure button Batalkan on Popup Iklan Program Unggulan able to click" +
                "</br> depend - ADKR-1480: Ensure button Batalkan on Popup Turunkan Iklan Program Unggulan able to click");
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