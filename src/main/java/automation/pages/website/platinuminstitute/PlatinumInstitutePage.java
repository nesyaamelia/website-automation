package automation.pages.website.platinuminstitute;

import automation.pages.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlatinumInstitutePage extends BasePage<PlatinumInstituteModel, PlatinumInstituteLocator> implements PlatinumInstituteStep {

    public static PlatinumInstitutePage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new PlatinumInstitutePage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = PlatinumInstituteModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = PlatinumInstituteLocator.newInstance();
    }

    @Override
    public PlatinumInstitutePage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void searchResultPageLembagaPlatinum() {
        getElement().waitUntilClick(mLocator.showMore);
        getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.showMore));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.programCard));
        String titleSrp = webDriver.getTitle();
        boolean isTitleSrpPlatinum = titleSrp.contains("Pencarian Program");
        logAndAssertTrue(isTitleSrpPlatinum, "search srp platinum");
    }

    @Override
    public void filterOfflineLocationInstitution() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.lokasiOffline);
        getElement().waitUntilClick(mLocator.offlineLocation);
        boolean isSuccessFilter = getElement().handleElementPresent(mLocator.chipsFilter, 5);
        logAndAssertTrue(isSuccessFilter, "Success filter offline location" +
                "</br>depend - KMWA-9903: SRP - Ensure success choose multiple filter by Lokasi Offline");
    }

    @Override
    public void filterPendidikanMinimalSd() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.pendidikanMinimalSd);
        logInfo("Success Filter by Pendidikan Minimal SD");
    }

    @Override
    public void filterPendidikanMinimalSma() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.pendidikanMinimalSma);
        logInfo("Success Filter by Pendidikan Minimal SMA");
    }

    @Override
    public void filterPendidikanMinimalD4() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.pendidikanMinimalD4);
        logInfo("Success Filter by Pendidikan Minimal D4");
    }

    @Override
    public void filterBidangStudi() {
        stepsHelper.delay(globalVariable.shortDelay);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.bidangStudi));
        logInfo("Success Filter by Bidang Studi");
    }

    @Override
    public void filterBidangStudiFromPopup() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.buttonShowAllBidangStudi);
        if (getElement().verifyElementPresent(mLocator.titleBidangStudiModal)) {
            getElement().click(mLocator.bidangStudi2);
            getElement().click(mLocator.buttonApplyFilter);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.cardProgramBidangStudi));
        } else {
            logFailed("Failed show popup", null);
        }
    }

    @Override
    public void searchBidangStudiInvalid() {
        getElement().waitUntilClick(mLocator.buttonShowAllBidangStudi);
        getElement().waitUntilClick(mLocator.searchBarPopupBidangStudi);
        getElement().setText(mLocator.searchBarPopupBidangStudi, mModel.keywordPopupInvalid);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.buttonSearchPopupBidangStudi));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.invalidKeywordText));
        boolean isShowFieldOfStudy = getElement().verifyElementPresent(mLocator.invalidKeywordText);
        logAndAssertTrue(isShowFieldOfStudy, "validate invalid keyword");
    }

    @Override
    public void searchBidangStudiValid() {
        getElement().waitUntilClick(mLocator.buttonShowAllBidangStudi);
        getElement().waitUntilClick(mLocator.searchBarPopupBidangStudi);
        getElement().setText(mLocator.searchBarPopupBidangStudi, mModel.keywordPopupValid);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.buttonSearchPopupBidangStudi));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.resultSearchBidangStudi));
        String getResultSearch = getElement().getText(mLocator.resultSearchBidangStudi);
        logAndAssertEqual(mModel.keywordPopupValid, getResultSearch.toLowerCase(), "show search result");
    }

    @Override
    public void filterUsiaMinimal() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.usiaMinimal);
        getElement().verifyElementPresent(mLocator.modalUsiaMinimal);
        getElement().click(mLocator.selectUsiaMinimal);
        getElement().click(mLocator.buttonApplyFilter);
        logInfo("Success Filter by Usia Minimal");
    }

    @Override
    public void filterUsiaMaksimal() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.usiaMaksimal);
        getElement().verifyElementPresent(mLocator.modalUsiaMaksimal);
        getElement().click(mLocator.selectUsiaMaksimal);
        getElement().click(mLocator.buttonApplyFilter);
        logInfo("Success Filter by Usia Maksimal");
    }

    @Override
    public void filterUsiaMinimalMaksimal() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.usiaMinimal);
        getElement().verifyElementPresent(mLocator.modalUsiaMinimal);
        getElement().click(mLocator.selectUsiaMinimal);
        getElement().click(mLocator.buttonApplyFilter);
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.usiaMaksimal);
        getElement().verifyElementPresent(mLocator.modalUsiaMaksimal);
        getElement().click(mLocator.selectUsiaMaksimal);
        getElement().click(mLocator.buttonApplyFilter);
        logInfo("Success Filter by Usia Minimal - Usia Maksimal");
    }

    @Override
    public void closeModal() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.usiaMinimal);
        getElement().verifyElementPresent(mLocator.modalUsiaMinimal);
        getElement().click(mLocator.closeModal);
        logInfo("Modal closed");
    }

    @Override
    public void buttonReset() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.buttonShowAllBidangStudi);
        getElement().verifyElementPresent(mLocator.titleBidangStudiModal);
        getElement().click(mLocator.selectBidangStudi);
        getElement().click(mLocator.selectBidangStudi2);
        getElement().click(mLocator.buttonReset);
        logInfo("clears all selected filters");
    }

    @Override
    public void filterTipeProgramPilihan() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.programPilihan);
        logInfo("Success Filter by Tipe Program Pilihan");
    }

    @Override
    public void filterTipeProgramPrakerja() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.programPrakerja);
        logInfo("Success Filter by Tipe Program Prakerja");
    }

    @Override
    public void selectLembagaPopup() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.textShowAll);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.selectLembagaPopup));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.selectLembagaPopup));
        getElement().click(mLocator.selectLembagaPopup);
        getElement().click(mLocator.buttonApply);
        getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.selectLembagaPopup));
        logInfo("Success Select Institution on Popup");
    }

    @Override
    public void showAllButtonBidangIndustri() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.buttonShowAllBidangStudi);
        logInfo("Click button tampilkan semua");
    }

    @Override
    public void closeButtonPills() {
        stepsHelper.delay(globalVariable.shortDelay);
        selectLembagaPopup();
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonClosePills));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonClosePills));
        getElement().click(mLocator.buttonClosePills);
        logInfo("Pills Closed");
    }

    @Override
    public void buttonResetUsiaMinimal() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.usiaMinimal);
        getElement().verifyElementPresent(mLocator.modalUsiaMinimal);
        getElement().click(mLocator.selectUsiaMinimal);
        getElement().click(mLocator.buttonReset);
        logInfo("Reset Usia Minimal");
    }

    @Override
    public void buttonResetUsiaMaksimal() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.usiaMaksimal);
        getElement().verifyElementPresent(mLocator.modalUsiaMaksimal);
        getElement().click(mLocator.selectUsiaMaksimal);
        getElement().click(mLocator.buttonReset);
        logInfo("Reset Usia Maksimal");
    }

    @Override
    public void closeModalUsiaMinimal() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.usiaMinimal);
        getElement().verifyElementPresent(mLocator.modalUsiaMinimal);
        getElement().click(mLocator.closeModal);
        logInfo("Modal Closed");
    }

    @Override
    public void closeModalUsiaMaksimal() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.usiaMaksimal);
        getElement().verifyElementPresent(mLocator.modalUsiaMaksimal);
        getElement().click(mLocator.closeModal);
        logInfo("Modal Closed");
    }
}
