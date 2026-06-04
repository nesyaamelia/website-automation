package automation.pages.website.homepage.kariermu.srp;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class SrpLocator extends BaseLocator {

    public static final String loadingProcess = "Loading...";

    public final By counterShow = getElementByXpath("//*[@class=\"font-paragraph-5 search-count\"]");
    public final By priceCard = getElementByXpath("//*[@class=\"program-card-price-discounted font-label-md-sb-lh24\"]");
    public final By buttonShowAllFilterLocation = getElementByXpath("(//*[@id=\"show-all-options-button\"])[2]");
    public final By buttonClosePopup = getElementByXpath("//*[@class=\"close-icon\"]");
    public final By buttonSearchBar = getElementByXpath("(//*[contains(@class, 'is-search-icon')])[1]");
    public final By buttonSearchPopup = getElementByXpath("(//*[contains(@class, 'is-search-icon')])[2]");
    public final By invalidKeywordText = getElementByXpath("//*[@class=\"empty-state__texts inter-medium_small-normal\"]");
    public final By checkboxOffline = getElementByXpath("//span[text()=\"Offline\"]");
    public final By checkboxOfflineNonPlatinum = getElementByXpath("//span[text()=\"Tatap Muka Offline\"]");
    public final By checkboxWebinar = getElementByXpath("//span[text()=\"Webinar\"]");
    public final By offlineLocation = getElementByXpath("(//div[@class='inter-medium_small-normal'])[1]");
    public final By offlineLocation2 = getElementByXpath("//span[text()='Kab. Bekasi']");
    public final By cardProgram = getElementByXpath("//*[@class=\"program-card-body\"]/div[1]");
    public final By lowestPrice = getElementByXpath("(//*[@id=\"s-input\"])[1]");
    public final By highestPrice = getElementByXpath("(//*[@id=\"s-input\"])[2]");
    public final By starRating = getElementByXpath("//*[@class=\"program-card-rating\"]");
    public final By titleLocationModal = getElementByXpath("//*[@class=\"font-subtitle-sb-2 title\"]");
    public final By resultSearchLocation = getElementByXpath("//*[@class=\"inter-medium_small-normal\"]");
    public final By clearKeyword = getElementByXpath("//button[@class='s-searchbar-icon is-close-icon v-popper--has-tooltip']");
    public final By chipsSorting = getElementById("button-show-selection");
    public final By sortingPenilaian = getElementById("select--rating");
    public final By sortingTerbaru = getElementById("select--id");
    public final By sortingHargaTertinggi = getElementById("select--price");
    public final By sortingHargaTerendah = getElementById("select-price");
    public final By searchbarPopup = getElementById("s-searchbar");
    public final By cardRecomendation = getElementById("program-card");
    public final By buttonApplyFilter = getElementById("button-apply-selection");
    public final By bubbleTagFilter = getElementByXpath("//*[@class=\"s-tag s-tag-filter is-neutral\"]");
    public final By textShowAllFilterInstitution = getElementByXpath("(//*[@class=\"desktop-side-filter\"]/section)[1]/div[2]/div/button");
    public final By checkboxContainer = getElementByXpath("//*[@class=\"checkbox-container\"]/div");
    public final By emptyDataSearchContainer = getElementByXpath("//div[contains(text(), \"Nama  belum tersedia.\")]");
    public final By buttonCloseModalPopup = getElementById("button-close-modal");
    public final By loadingSpinner = getElementById("loading-spinner");
    public final By textChangeKeyword = getElementByXpath("//*[contains(text(), \"Ganti Kata Kunci\")]");
    public final By selectProgramRecommendation = getElementByXpath("(//*[@class=\"program-recommendation-desktop\"]//*[contains(@class, \"program-recommendation-card\")])[1]");
    public final By emptyStateOtherLocation = getElementByXpath("(//*[@class=\"empty-state\"])[2]");
    public final By seeOtherProgram = getElementByXpath("//*[contains(text(), \"Lihat Program Lainnya\")]");
    public final By searchBar = getElementById("searchbar");
    public final By carouselBannerHomepage = getElementById("carousel-dots-container");
    public final By programCardTitle = getElementByXpath("(//*[contains(@class, \"program-card-title\")])[1]");
    public final By programCardRecommendation = getElementByXpath("//*[contains(@class, \"program-recommendation-card\")]");
    public final By buttonResetFilterOnModal = getElementById("button-reset-selection");
    public final By buttonShowAllIndustry = getElementByXpath("(//*[@id=\"show-all-options-button\"])[4]");
    public final By listIndustry = getElementByXpath("(//label[@class='custom-control-label'])[1]");
    public final By emptyStateOnPopup = getElementByXpath("//*[@class=\"empty-data font-paragraph-5\"]");
    public final By buttonPrevious = getElementById("s-filter-previous-button");
    public final By buttonClearFilter = getElementById("s-filter-clear-all-btn");
    public final By buttonRemovePills = getElementByXpath("(//*[@id='remove-item'])[1]");
    public final By tagFilter = getElementByClassName("s-tag-filter");
    public final By ribbonWebinar = getElementByClassName("ribbon-webinar");
    public final By buttonShowAllMinimumEducation = getElementByXpath("(//*[@id=\"show-all-options-button\"])[3]");
    public final By selectFilterMinimumEducation = getElementByXpath("(//div[@class='checkbox-items'])[1]");
    public final By minimumAgeButton = getElementById("desktop-select-min-age");
    public final By maximalAgeButton = getElementById("desktop-select-max-age");
    public final By selectFilterMinimumAge = getElementByXpath("(//div[@class='checkbox-items'])[1]");
    public final By selectFilterMaximumAge = getElementByXpath("(//div[@class='checkbox-items'])[2]");
    public final By showModalFilter = getElementByCssSelector("div.modal.fade.show");
    public final By filterTag = getElementByCssSelector("#s-tag>span");

    public static SrpLocator newInstance() {
        return new SrpLocator();
    }

    public By textRating(int j) {
        return getElementByXpath("(//*[@class=\"rating-number font-label-sm-rg\"])[" + j + "]");
    }

    public By cardProgramLocation(int q) {
        return getElementByXpath("(//*[@class=\"d-flex font-label-sm-rg program-offline-locations\"])[" + q + "]/div/span");
    }

    public By tagFilter(int i) {
        return getElementByXpath("(//*[@class=\"s-tag s-tag-filter is-neutral\"])[" + i + "]");
    }

    public By selectIndustry(int i) {
        return getElementByXpath("(//div[@class='inter-medium_small-normal'])[" + i + "]");
    }

    public By tagFilterCardType(int i, String type) {
        return getElementByXpath("(//*[@id=\"program-card\"])[" + i + "]/div/div[@class=\"program-ribbon-wrapper font-label-6 ribbon-" + type + "\"]");
    }

    public By getPriceProgram(int i) {
        return getElementByXpath("(//*[@class=\"program-card-price-discounted font-label-md-sb-lh24\"])[" + i + "]");
    }

    public By priceProgram(int i) {
        return getElementByXpath("(//*[@class=\"program-card-price-discounted font-label-md-sb-lh24\"])[" + i + "]");
    }

    public static String selectFilter(int i) {
        return "document.querySelector('.desktop-side-filter > section:nth-child(1) > div:nth-child(2) > div > div > label:nth-child(" + i + ") > input').click()";
    }

    public static String selectFilterOnModalPopup(int i) {
        return "document.querySelector('.checkbox-container > div:nth-child(" + i + ") > div > input').click()";
    }

    public By filterByName(String name) {
        return getElementByXpath("//span[text()='" + name + "']/following-sibling::input");
    }

    public By listTagRibbonWebinar(int i) {
        return getElementByXpath("(//*[contains(@class, \"ribbon-webinar\")])[" + i + "]");
    }
}