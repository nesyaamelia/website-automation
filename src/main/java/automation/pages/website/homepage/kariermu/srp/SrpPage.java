package automation.pages.website.homepage.kariermu.srp;

import automation.pages.base.BasePage;
import automation.pages.website.homepage.kariermu.SortingSearchType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

import static automation.pages.website.homepage.kariermu.srp.SrpLocator.loadingProcess;

public class SrpPage extends BasePage<SrpModel, SrpLocator> implements SrpSteps {

    public static SrpPage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new SrpPage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = SrpModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = SrpLocator.newInstance();
    }

    @Override
    public SrpPage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void sortingSearch(SortingSearchType sortingSearchType) {
        stepsHelper.delay(globalVariable.shortDelay);
        switch (sortingSearchType) {
            case PENILAIAN_TERTINGGI:
                getElement().click(mLocator.chipsSorting);
                getElement().waitUntilClick(mLocator.sortingPenilaian);
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.starRating));
                int getSizeRating = getElement().getSize(mLocator.starRating);
                stepsHelper.delay(globalVariable.shortDelay);
                for (int j = 1; j <= getSizeRating; j++) {
                    String getTextRating = getElement().getText(mLocator.textRating(j));
                    int index = getTextRating.indexOf(" ");
                    String rating = getTextRating.substring(0, index);
                    logPassed("Success sorting by rating (" + rating + ")");
                }
                break;
            case TERBARU:
                getElement().click(mLocator.chipsSorting);
                getElement().waitUntilClick(mLocator.sortingTerbaru);
                break;
            case HARGA_TERTINGGI:
                if (getElement().verifyElementPresent(mLocator.chipsSorting)) {
                    getElement().click(mLocator.chipsSorting);
                    getElement().waitUntilClick(mLocator.sortingHargaTertinggi);
                    stepsHelper.delay(globalVariable.shortDelay);
                    if (getElement().verifyElementPresent(mLocator.priceCard)) {
                        int getPriceCard = getElement().getSize(mLocator.priceCard);
                        if (getPriceCard > 1) {
                            String getPrice1 = getElement().getText(mLocator.priceProgram(1)).replaceAll("\\D+", "");
                            String getPrice2 = getElement().getText(mLocator.priceProgram(2)).replaceAll("\\D+", "");
                            if (Integer.parseInt(getPrice1) > Integer.parseInt(getPrice2) || Integer.parseInt(getPrice1) == Integer.parseInt(getPrice2)) {
                                logPassed("Price card 1: " + getPrice1 + ", greater than price card 2: " + getPrice2);
                            } else {
                                logFailed("Price sort randomly", null);
                            }
                        } else {
                            logInfo("Card only 1");
                        }
                    } else {
                        logFailed("Card not found", null);
                    }
                }
                break;
            case HARGA_TERENDAH:
                if (getElement().verifyElementPresent(mLocator.chipsSorting)) {
                    getElement().click(mLocator.chipsSorting);
                    getElement().waitUntilClick(mLocator.sortingHargaTerendah);
                    stepsHelper.delay(globalVariable.shortDelay);
                    if (getElement().verifyElementPresent(mLocator.priceCard)) {
                        int getPriceCard = getElement().getSize(mLocator.priceCard);
                        if (getPriceCard > 1) {
                            String getPrice1 = getElement().getText(mLocator.priceProgram(1));
                            String getPrice2 = getElement().getText(mLocator.priceProgram(2));
                            if (getPrice1.contains("GRATIS") && getPrice2.contains("GRATIS")) {
                                logPassed("Price card 1: " + getPrice1 + ", lower than price card 2: " + getPrice2);
                            } else {
                                logFailed("Price sort randomly", null);
                            }
                        } else {
                            logInfo("Card only 1");
                        }
                    } else {
                        logFailed("Card not found", null);
                    }
                }
                break;
            default:
                if (getElement().isEnabled(mLocator.chipsSorting)) {
                    logPassed("Shorting enable to click");
                } else {
                    logFailed("Shorting not able to click", null);
                }
                break;
        }
    }

    @Override
    public void filterByInstitution(SrpModel srpModel) {
        stepsHelper.delay(globalVariable.shortDelay);
        if (srpModel.selectFilterInstitution > 1) {
            for (int i = 1; i <= srpModel.selectFilterInstitution; i++) {
                getElement().executeJavascript(SrpLocator.selectFilter(i));
                stepsHelper.delay(globalVariable.shortDelay);
                if (getElement().verifyElementPresent(mLocator.tagFilter(i))) {
                    String buttonFilterSelected = getElement().getText(mLocator.tagFilter(i));
                    logPassed("Success select filter: " + buttonFilterSelected);
                } else {
                    logFailed("Failed select filter", null);
                }
            }

        } else {
            getElement().executeJavascript(SrpLocator.selectFilter(1));
            stepsHelper.delay(globalVariable.shortDelay);
            if (getElement().verifyElementPresent(mLocator.bubbleTagFilter)) {
                String buttonFilterSelected = getElement().getText(mLocator.bubbleTagFilter);
                logPassed("Success select filter: " + buttonFilterSelected);
            } else {
                logFailed("Failed select filter", null);
            }
        }
        stepsHelper.delay(globalVariable.shortDelay);
        if (srpModel.showAll) {
            if (getElement().isEnabled(mLocator.textShowAllFilterInstitution)) {
                getElement().click(mLocator.textShowAllFilterInstitution);
                logPassed("Button show all able to click");
                if (srpModel.selectInstitutionFromPopup) {
                    stepsHelper.delay(globalVariable.shortDelay);
                    if (getElement().verifyElementPresent(mLocator.checkboxContainer)) {
                        if (srpModel.searchInstitutionModal) {
                            getElement().setText(mLocator.searchbarPopup, srpModel.keywordPopupInstitution);
                            getElement().setText(mLocator.searchbarPopup, Keys.chord(Keys.ENTER));
                            stepsHelper.delay(globalVariable.shortDelay);
                            if (getElement().verifyElementPresent(mLocator.checkboxContainer)) {
                                logPassed("Search with keyword: " + srpModel.keywordPopupInstitution + " is present");
                            } else if (getElement().verifyElementPresent(mLocator.emptyDataSearchContainer)) {
                                logInfo("Search with keyword: " + srpModel.keywordPopupInstitution + " data not found");
                            }
                        }
                        if (srpModel.closeModal) {
                            getElement().click(mLocator.buttonCloseModalPopup);
                            stepsHelper.delay(globalVariable.shortDelay);
                            if (getElement().verifyElementNotPresent(mLocator.buttonApplyFilter)) {
                                logPassed("Success close modal popup filter institution");
                            } else {
                                logFailed("Modal not close", null);
                            }
                        } else {
                            int getListInstitution = getElement().getSize(mLocator.checkboxContainer);
                            getElement().executeJavascript(SrpLocator.selectFilterOnModalPopup(getListInstitution));
                            getElement().waitUntilClick(mLocator.buttonApplyFilter);
                            logPassed("Success select institution on popup" +
                                    "</br>depend - 7455: ensureButtonTerapkanOnPopupLembagaAbleToClick");
                        }
                    } else {
                        logFailed("List institution not found", null);
                    }

                }
            } else {
                logFailed("Button show all not able to click", null);
            }
        }
    }

    @Override
    public void filterByTypeActivity(SrpModel srpModel) {
        boolean isShowTagOffline = false;
        stepsHelper.delay(globalVariable.shortDelay);
        String getCurrentHost = getElement().getUrl();
        if (getCurrentHost.contains("pattimura") && srpModel.searchKeywordProgram.equalsIgnoreCase("offline")) {
            getElement().click(mLocator.checkboxOffline);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.tagFilter));
            getElement().waitUntilLoadingProcess(loadingProcess);
            int listProgram = getElement().getSize(mLocator.cardRecomendation);
            for (int i = 1; i <= listProgram; i++) {
                isShowTagOffline = getElement().verifyElementPresent(mLocator.tagFilterCardType(i, srpModel.searchKeywordProgram));
                if (!isShowTagOffline) {
                    break;
                }
            }
            logAndAssertTrue(isShowTagOffline, "filter program offline");
        } else if (srpModel.searchKeywordProgram.equalsIgnoreCase("offline")) {
            getElement().click(mLocator.checkboxOfflineNonPlatinum);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.tagFilter));
            getElement().waitUntilLoadingProcess(loadingProcess);
            int listProgram = getElement().getSize(mLocator.cardRecomendation);
            for (int i = 1; i <= listProgram; i++) {
                isShowTagOffline = getElement().verifyElementPresent(mLocator.tagFilterCardType(i, srpModel.searchKeywordProgram));
                if (!isShowTagOffline) {
                    break;
                }
            }
            logAndAssertTrue(isShowTagOffline, "filter program offline");
        } else if (srpModel.searchKeywordProgram.equalsIgnoreCase("webinar")) {
            boolean isShowWebinar = false;
            getElement().click(mLocator.checkboxWebinar);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.ribbonWebinar));
            int listProgram = getElement().getSize(mLocator.ribbonWebinar);
            for (int i = 1; i <= listProgram; i++) {
                isShowWebinar = getElement().verifyElementPresent(mLocator.listTagRibbonWebinar(i));
                if (!isShowWebinar) {
                    break;
                }
            }
            logAndAssertTrue(isShowWebinar, "show program webinar");
        } else if (getCurrentHost.contains("pattimura") && srpModel.searchKeywordProgram.equalsIgnoreCase("offline and webinar")) {
            boolean isShowProgram = false;
            getElement().click(mLocator.checkboxOffline);
            getElement().click(mLocator.checkboxWebinar);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.tagFilter));
            getElement().waitUntilLoadingProcess("Loading...");
            int listProgram = getElement().getSize(mLocator.cardRecomendation);
            for (int i = 1; i <= listProgram; i++) {
                isShowProgram = getElement().verifyElementPresent(mLocator.tagFilterCardType(i, "webinar"))
                        || getElement().verifyElementPresent(mLocator.tagFilterCardType(i, "offline"));
                if (!isShowProgram) {
                    break;
                }
            }
            logAndAssertTrue(isShowProgram, "show type activity");
        } else if (srpModel.searchKeywordProgram.equalsIgnoreCase("offline and webinar")) {
            boolean isShowProgram = false;
            getElement().click(mLocator.checkboxOfflineNonPlatinum);
            getElement().click(mLocator.checkboxWebinar);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.tagFilter));
            getElement().waitUntilLoadingProcess("Loading...");
            int listProgram = getElement().getSize(mLocator.cardRecomendation);
            for (int i = 1; i <= listProgram; i++) {
                isShowProgram = getElement().verifyElementPresent(mLocator.tagFilterCardType(i, "webinar"))
                        || getElement().verifyElementPresent(mLocator.tagFilterCardType(i, "offline"));
                if (!isShowProgram) {
                    break;
                }
            }
            logAndAssertTrue(isShowProgram, "show type activity");
        }
    }

    @Override
    public void filterInvalidKeyword(SrpModel srpModel) {
        getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.carouselBannerHomepage));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.programCardTitle));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.programCardTitle));
        if (srpModel.searchInvalid) {
            getElement().clearText(mLocator.searchBar);
            getElement().setText(mLocator.searchBar, srpModel.searchKeywordProgram);
            getElement().clickHandlerJs(mLocator.buttonSearchBar);
        }
        stepsHelper.delay(globalVariable.shortDelay);
        if (getElement().verifyElementPresent(mLocator.loadingSpinner)) {
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.loadingSpinner));
        } else {
            logInfo("Spinner not appears");
        }
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.textChangeKeyword));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.textChangeKeyword));
        if (getElement().verifyElementPresent(mLocator.invalidKeywordText) && getElement().verifyElementPresent(mLocator.textChangeKeyword)) {
            String message = getElement().getText(mLocator.invalidKeywordText);
            logPassed("Message: " + message +
                    "</br>depend - 7497: ensureButtonGantiKataKunciAppearOnEmptyStateAndAbleToClick" +
                    "</br>depend = 7501: ensureEmptyStateAppearWhenProblemWhileProcessingTheSearch");
        } else {
            logFailed("Empty state not appears", null);
        }
        if (getElement().verifyElementPresent(mLocator.programCardRecommendation)) {
            logPassed("Program rekomendasi appears");
            if (srpModel.isSelectProgramRekomendasi) {
                getElement().click(mLocator.selectProgramRecommendation);
            }
            if (srpModel.isStopSelectProgramRekomendasi) {
                return;
            }
        } else {
            logFailed("Program rekomendasi not appears", null);
        }
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().scrollToElement(mLocator.seeOtherProgram);
        if (getElement().verifyElementPresent(mLocator.seeOtherProgram)) {
            if (srpModel.isSelectOtherProgram) {
                getElement().click(mLocator.seeOtherProgram);
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.programCardTitle));
                getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.programCardTitle));
                String getTitle = getElement().getText(mLocator.counterShow);
                if (Pattern.matches("Menampilkan \\d+ - \\d+ dari total \\d+ pencarian", getTitle)) {
                    logPassed("Title srp: " + getTitle);
                }
            }
            logPassed("Button other program is enable to click");
        } else {
            logFailed("Button other program not appears", null);
        }
    }

    @Override
    public void filterPrice(SrpModel srpModel) {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().scrollToElement(mLocator.lowestPrice);
        switch (srpModel.filterPrice) {
            case "low price":
                getElement().setText(mLocator.lowestPrice, srpModel.priceFrom);
                break;
            case "high price":
                getElement().setText(mLocator.highestPrice, srpModel.priceTo);
                break;
            default:
                getElement().setText(mLocator.lowestPrice, srpModel.priceFrom);
                getElement().setText(mLocator.highestPrice, srpModel.priceTo);
        }
        getElement().sendKeys(mLocator.highestPrice, Keys.ENTER);
        stepsHelper.delay(globalVariable.shortDelay);
        boolean isEmptyStateOtherLocation = getElement().verifyElementPresent(mLocator.emptyStateOtherLocation);
        if (isEmptyStateOtherLocation) {
            logAndAssertTrue(isEmptyStateOtherLocation, "Data not found with price from: " + srpModel.priceFrom + "- to: " + srpModel.priceTo);
        } else if (getElement().verifyElementPresent(mLocator.cardRecomendation)) {
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.priceCard));
            int getSizePriceProgram = getElement().getSize(mLocator.priceCard);
            for (int i = 1; i <= getSizePriceProgram; i++) {
                String getPrice = getElement().getText(mLocator.getPriceProgram(i));
                switch (srpModel.filterPrice) {
                    case "low price":
                        boolean isShowLowerPrice = getPrice.contains("GRATIS") || getPrice.contains("Sudah Ikut") || Integer.parseInt(getPrice.replaceAll("\\D+", "")) >= Integer.parseInt(srpModel.priceFrom);
                        logAndAssertTrue(isShowLowerPrice, "show lower price");
                        break;
                    case "high price":
                        boolean isShowHighPrice = getPrice.contains("GRATIS") || getPrice.contains("Sudah Ikut") || Integer.parseInt(getPrice.replaceAll("\\D+", "")) <= Integer.parseInt(srpModel.priceTo);
                        logAndAssertTrue(isShowHighPrice, "show high price");
                        break;
                }
            }
        }
    }

    @Override
    public void filterOfflineLocation() {
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.offlineLocation2);
        getElement().click(mLocator.buttonShowAllFilterLocation);
        if (getElement().verifyElementPresent(mLocator.titleLocationModal)) {
            getElement().click(mLocator.offlineLocation);
            getElement().click(mLocator.buttonApplyFilter);
            stepsHelper.delay(globalVariable.midDelay);
            int getSizeOfflineCard = getElement().getSize(mLocator.cardProgram);
            for (int i = 1; i <= getSizeOfflineCard; i++) {
                String getTextLocation = getElement().getText(mLocator.cardProgramLocation(i));
                if (getTextLocation.contains("Kota Yogyakarta") || getTextLocation.contains("Kab. Bekasi") || getTextLocation.contains("Tersedia di")) {
                    logPassed("Successfully filter by Offline Location");
                } else {
                    logInfo("Cannot filter by Offline Location ");
                }
            }
        } else {
            logFailed("Wrong filter implemented", null);
        }
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().click(mLocator.buttonShowAllFilterLocation);
        getElement().click(mLocator.searchbarPopup);
        getElement().setText(mLocator.searchbarPopup, mModel.keywordPopupLocation);
        getElement().clickHandlerJs(mLocator.buttonSearchPopup);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.resultSearchLocation));
        String getResultSearchLocation = getElement().getText(mLocator.resultSearchLocation);
        if (getResultSearchLocation.contains("Kota")) {
            logPassed("Successfully show result search on popup location");
        } else {
            logFailed("Error search result", null);
        }
        getElement().click(mLocator.searchbarPopup);
        getElement().clickHandlerJs(mLocator.clearKeyword);
        getElement().setText(mLocator.searchbarPopup, mModel.keywordPopupInvalid);
        getElement().clickHandlerJs(mLocator.buttonSearchPopup);
        stepsHelper.delay(globalVariable.shortDelay);
        if (getElement().verifyElementPresent(mLocator.invalidKeywordText)) {
            logPassed("Data Location not found");
        } else {
            logInfo("Data location successfully show");
        }
        getElement().click(mLocator.buttonClosePopup);
        stepsHelper.delay(globalVariable.shortDelay);
        if (getElement().verifyElementNotPresent(mLocator.titleLocationModal)) {
            logPassed("Successfully close popup location");
        } else {
            logFailed("Unable to close popup location", null);
        }
    }

    @Override
    public void filterIndustry() {
        getElement().waitUntilClick(mLocator.buttonShowAllIndustry);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.listIndustry));
        getElement().click(mLocator.searchbarPopup);
        getElement().setText(mLocator.searchbarPopup, mModel.keywordPopupInvalid);
        getElement().setText(mLocator.buttonSearchPopup, Keys.chord(Keys.ENTER));
        boolean isEmptyStateAppear = getElement().verifyElementPresent(mLocator.emptyStateOnPopup);
        logAndAssertTrue(isEmptyStateAppear, "empty state appear when search invalid keyword");
        getElement().clearText(mLocator.searchbarPopup);
        getElement().setText(mLocator.searchbarPopup, mModel.keywordPopupIndustry);
        getElement().setText(mLocator.buttonSearchPopup, Keys.chord(Keys.ENTER));
        getElement().waitUntilClick(mLocator.selectIndustry(1));
        getElement().click(mLocator.selectIndustry(2));
        getElement().click(mLocator.buttonApplyFilter);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.bubbleTagFilter));
        boolean isSuccessFilter = getElement().verifyElementPresent(mLocator.bubbleTagFilter);
        logAndAssertTrue(isSuccessFilter, "success filter by bidang industri" +
                "</br>depend - KMWA-7476: SRP - Ensure success choose multiple filter by Lokasi Offline" +
                "</br>depend - KMWA-7478: SRP - Ensure button Tampilkan Semua on the Bidang Studi/Industri filter section able to click" +
                "</br>depend - KMWA-7480: SRP - Ensure checkbox on Bidang Studi/Industri popup able to click" +
                "</br>depend - KMWA-7482: SRP - Ensure button Terapkan on Popup Bidang Studi/Industri able to click" +
                "</br>depend - KMWA-7486: SRP - Ensure pills appear when user apply filter and sorting" +
                "</br>depend - KMWA-7484: SRP - Ensure search valid keyword on Popup Bidang Studi/Industri" +
                "</br>depend - KMWA-7485: SRP - Ensure search invalid keyword on Popup Bidang Studi/Industri");
    }

    @Override
    public void deleteFilter() {
        for (int i = 0; i < mModel.listFilter.length; i++) {
            getElement().clickHandlerJs(mLocator.filterByName(mModel.listFilter[i]));
        }
        getElement().clickHandlerJs(mLocator.buttonPrevious);
        getElement().clickHandlerJs(mLocator.buttonRemovePills);
        getElement().clickHandlerJs(mLocator.buttonClearFilter);
        boolean isFilterDeleted = getElement().verifyElementNotPresent(mLocator.buttonClearFilter);
        logAndAssertTrue(isFilterDeleted, "success delete all select filter" +
                "</br>depend - KMWA-7487: SRP - Ensure button close on pills able to click" +
                "</br>depend - KMWA-7488: SRP - Ensure button Hapus Semua appear if user apply more than 1 filters" +
                "</br>depend - KMWA-7490: SRP - Ensure Chevron of pills able to clic");
    }

    @Override
    public void resetItemSelected() {
        getElement().waitUntilClick(mLocator.buttonShowAllFilterLocation);
        getElement().waitUntilClick(mLocator.offlineLocation);
        getElement().click(mLocator.buttonResetFilterOnModal);
        getElement().click(mLocator.buttonClosePopup);
        getElement().waitUntilClick(mLocator.buttonShowAllIndustry);
        getElement().waitUntilClick(mLocator.selectIndustry(1));
        getElement().click(mLocator.buttonResetFilterOnModal);
        WebElement checkbox = getElement().findElement(mLocator.selectIndustry(1));
        boolean isChecked = !checkbox.isSelected();
        logAndAssertTrue(isChecked, "success reset filter on modal" +
                "</br>depend - KMWA-7481: SRP - Ensure button Reset on Popup Bidang Studi/Industri able to click" +
                "</br>depend - KMWA-7483: SRP - Ensure button X on Popup Bidang Studi/Industri able to click");
    }

    @Override
    public void filterByMinimumEducation(SrpModel srpModel){
        openFilterModal(mLocator.buttonShowAllMinimumEducation);
        if (srpModel.isSelectFilter){
            getElement().click(mLocator.selectFilterMinimumEducation);
            getElement().click(mLocator.buttonApplyFilter);
            boolean filterFromPopup = getElement().verifyElementPresent(mLocator.cardProgram);
            logAndAssertTrue(filterFromPopup, "success filter and tag appear" +
                    "</br>depend - KMWA-9255: SRP - Ensure button Tampilkan Semua on the Pendidikan Minimal filter section able to click" +
                    "</br>depend - KMWA-9256: SRP - Ensure button Terapkan on Popup Pendidikan Minimal able to click" +
                    "</br>depend - KMWA-9282: SRP - Ensure pendidikan minimal is selected on popup when user klik button \"Tampilkan Semua\"");
        } else if (srpModel.resetFilter) {
            getElement().waitUntilClick(mLocator.buttonResetFilterOnModal);
            getElement().click(mLocator.buttonApplyFilter);
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.showModalFilter));
            int totalTagFilter = getElement().getSize(mLocator.tagFilter);
            logAndAssertEqual(0, totalTagFilter, "success reset filter selected");
        } else if (srpModel.closeModal) {
            getElement().waitUntilClick(mLocator.buttonCloseModalPopup);
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.showModalFilter));
            boolean isDisappearModal = getElement().verifyElementNotPresent(mLocator.showModalFilter);
            logAndAssertTrue(isDisappearModal, "success close modal");
        }
    }

    @Override
    public void filterByMinimumAndMaximumAge(SrpModel srpModel){
        openFilterModal(mLocator.minimumAgeButton);
        if (srpModel.isSelectFilter){
            getElement().waitUntilClick(mLocator.selectFilterMinimumAge);
            getElement().click(mLocator.buttonApplyFilter);
            boolean isFilterApply = getElement().verifyElementPresent(mLocator.cardProgram);
            logAndAssertTrue(isFilterApply, "success filter minimum age and tag appear");
            getElement().scrollToElementIgnoreNavbar(mLocator.maximalAgeButton);
            getElement().waitUntilClick(mLocator.maximalAgeButton);
            getElement().waitUntilClick(mLocator.selectFilterMaximumAge);
            getElement().click(mLocator.buttonApplyFilter);
            boolean filterByMinimumAndMaximumAge = getElement().verifyElementPresent(mLocator.cardProgram);
            logAndAssertTrue(filterByMinimumAndMaximumAge, "success filter minimum age amd maximum age" +
                    "</br>depend - KMWA-9272: SRP - Ensure success select filter by Usia Minimal" +
                    "</br>depend - KMWA-9273: SRP - Ensure success select filter by Usia Maksimal" +
                    "</br>depend - KMWA-9274: SRP - Ensure success select filter by Usia Minimal and Usia Maksimal");
        } else if (srpModel.resetFilter) {
            String beforeReset = getElement().getText(mLocator.filterTag);
            getElement().waitUntilClick(mLocator.buttonResetFilterOnModal);
            getElement().click(mLocator.buttonApplyFilter);
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.showModalFilter));
            String afterReset = getElement().getText(mLocator.filterTag);
            logAndAssertTrue(!beforeReset.equals(afterReset), "success reset filter");
        } else if (srpModel.closeModal) {
            getElement().waitUntilClick(mLocator.buttonCloseModalPopup);
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.showModalFilter));
            boolean isDisappearModal = getElement().verifyElementNotPresent(mLocator.showModalFilter);
            logAndAssertTrue(isDisappearModal, "success close modal");
        }
    }

    private void openFilterModal(By by){
        getElement().scrollToElementIgnoreNavbar(by);
        getElement().click(by);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.showModalFilter));
    }

}