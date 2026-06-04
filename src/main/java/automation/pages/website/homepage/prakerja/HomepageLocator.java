package automation.pages.website.homepage.prakerja;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class HomepageLocator extends BaseLocator {

    public static final String selectProgramFilter = "Journey Beli Program Prakerja";

    public final By programCard = getElementById("program-card");
    public final By imageStaticBanner = getElementById("static-banner-img");
    public final By buttonSeeAllMitra = getElementById("btn-to-all-mitra-list");
    public final By textProgramNameProgramDetail = getElementByXpath("//*[@class=\"mb-md-4 mb-0 font-title-1\"]");
    public final By textInstitutionNameInstitutionDetail = getElementByXpath("(//*[@class=\"title-value gotham-bold\"])[1]");
    public final By sectionProgramCategoryList = getElementByXpath("//*[@class=\"program-category-list\"]");
    public final By programCategoryTitle = getElementByXpath("//*[contains(text(), \"Data & Teknologi\")]");
    public final By buttonChevronRight = getElementByXpath("(//*[@id=\"scroll-pagination-next-btn\"])[1]");
    public final By buttonChevronLeft = getElementByXpath("//*[@id=\"scroll-pagination-prev-btn\"]");
    public final By buttonAllPrpgramOffline = getElementByXpath("(//*[@id=\"btn-route-to-search-page\"])[2]");
    public final By programOfflineList = getElementByXpath("(//*[@class=\"program-card card-div\" or @class=\"program-ribbon-wrapper font-label-6 ribbon-offline\"])[1]");
    public final By buttonAllProgramRecommendation = getElementByXpath("(//*[@id=\"btn-route-to-search-page\"])[1]");
    public final By getTextProgramNameRecommendation = getElementByXpath("(//*[@class=\"program-card program-card-recommendation\"]/div[2]/div)[1]");
    public final By getTextInstitution = getElementByXpath("(//*[@id=\"favourite-institution-card\"])[1]");
    public final By programNamePopup = getElementByXpath("//*[@class=\"font-paragraph-4 main-program-name main-font-color\"]");
    public final By seeAllProgramOnline = getElementByXpath("(//*[@id=\"btn-route-to-search-page\"])[3]");
    public final By cardResultProgram = getElementByXpath("(//*[@id=\"program-card\"][1]//*[@class=\"program-ribbon-wrapper font-label-6 ribbon-webinar\"])[1]");
    public final By cardProgramOnline = getElementByXpath("//*[contains(text(), \"Pilihan Program Online\")]");
    public final By sectionProgramOffline = getElementById("program-offline");
    public final By modalLocationProgramOffline = getElementByXpath("//*[@class=\"s-accordion mb-4 s-accordion-card s-accordion-md\"]");
    public final By iconLocationProgramOffline = getElementByXpath("//*[@class=\"s-modal-close-icon\"]");
    public final By listLocationProgramOffline = getElementByXpath("(//*[@class=\"s-accordion mb-4 s-accordion-card s-accordion-md\"])[1]");
    public final By getLocationProgramOffline = getElementByXpath("(//*[@class=\"location-title\"]/p)[1]");
    public final By totalLocationAvailable = getElementByXpath("(//*[@class=\"location-title\"]/p[2])[1]");
    public final By listNameProgramOffline = getElementByXpath("(//*[@class=\"s-accordion-content\"]/div[1]/a)[1]");
    public final By getSizeListLocationProgramOffline = getElementByXpath("(//*[@class=\"member-program font-paragraph-3 main-font-color\"])[1]");
    public final By voucherBanner = getElementById("voucher-banner");
    public final By programCategoryList = getElementByXpath("//*[@id=\"program-category-list-container\"]/a");
    public final By activeSelectedPills = getElementByXpath("//*[@class=\"tag-category font-subtitle-4 nuxt-link-active variant-default variant-default-active\"]");
    public final By sectionPencarianPopuler = getElementById("program-category");
    public final By titleSearchKeyword = getElementByXpath("//*[@class=\"font-paragraph-5 search-count\"]/span/b");
    public final By emptyStateSearch = getElementByXpath("//*[@class=\"font-label-xl-sb empty-state__title\"]");
    public final By tagCategoryProgram = getElementById("tag-category");
    public final By emptyStateMessage = getElementByXpath("//*[@class=\"empty-state-wrapper\"]");
    public final By inputKeywordSearchCategory = getElementByXpath("//*[@name=\"program-search\"]");
    public final By titleRedeemVoucher = getElementByClassName("voucher-title");
    public final By buttonRedeemFromBanner = getElementById("voucher-banner");
    public final By sectionLogin = getElementById("modal-login-form");
    public final By redeemVoucherTitle = getElementById("redeem-voucher");
    public final By staticBanner = getElementById("static-banner");
    public final By logoKariermu = getElementByXpath("//*[@class=\"kariermu-footer-logo\"]");
    public final By buttonCareer = getElementById("career-page");
    public final By buttonMitraKolaborasi = getElementById("mitra-microsite");
    public final By titleMitraKolaborasiPage = getElementByXpath("//*[@class=\"font-title-4 section-title\"]");
    public final By titleApaKataMitra = getElementByXpath("(//*[@class=\"font-title-4 section-title text-md-center mx-md-auto\"])[2]");
    public final By mailInfo = getElementByXpath("//*[@class=\"mail-info\"]");
    public final By chevronApaKataMitra = getElementByXpath("//*[@alt=\"chevron-right\"]");
    public final By titleDownloadAplikasi = getElementByXpath("//*[@class=\"section-download-apps\"]/h5");
    public final By buttonDownloadAplikasi = getElementByXpath("//*[@class=\"download-apps\"]");
    public final By buttonDetailTicker = getElementById("s-ticker-action");
    public final By tickerInformation = getElementByXpath("//*[@class=\"is-info k-ticker\"]");
    public final By sizeProgramOffline = getElementByXpath("//*[@id=\"program-offline-list-wrapper\"]//*[@class=\"program-card-title font-label-md-sb-lh24\"]");
    public final By sectionProgramRecommendation = getElementByXpath("//*[contains(text(), \"Program Rekomendasi\")]");
    public final By inputTextSearchBar = getElementById("searchbar");
    public final By textPopularSearch = getElementByXpath("//*[@class=\"search-recommendation\"]/div/div/a");
    public final By nameCardTitle = getElementByXpath("//p[contains(@class, \"card-title\")]");
    public final By buttonRouteToSrp = getElementById("btn-route-to-search-page");
    public final By buttonArrowLocationOffline = getElementByXpath("//button[@class=\"s-accordion-title flex justify-content-between align-items-center\"]");
    public final By groupLocationOffline = getElementByXpath("//*[@class=\"offline-group-accordion\"]/a[1]");
    public final By titleInstitutionPage = getElementByXpath("//*[contains(text(), \"Pilihan Lembaga\")]");
    public final By buttonSeeMoreMitraPage = getElementByXpath("//*[contains(text(), \"Tampilkan Lainnya\")]");
    public final By totalInstitution = getElementById("institution-logo");
    public final By ribbonWebinar = getElementByClassName("ribbon-webinar");
    public final By buttonShareProgram = getElementById("share-program-btn");
    public final By logoKariermuNavbar = getElementById("kariermu-logo");
    public final By programList = getElementById("program-category-list-container");
    public final By sectionNearestSchedule = getElementByXpath("//*[contains(text(), \"Program dengan Jadwal Terdekat\")]");
    public final By programNearestSchedule = getElementByXpath("(//*[@id='program-card'])[1]");
    public final By buttonHomepage = getElementById("navbar-logo");
    public final By tabOffline = getElementByXpath("//button[@class='tab-btn']");
    public final By inputSearchInstitution = getElementByXpath("(//input[@class='input-search'])[1]");
    public final By buttonSearch = getElementByXpath("//form[@class='search-bar-web']//img");
    public final By totalNearestScheduleCard = getElementByCssSelector("div#program-closest-schedule div#program-card");
    public final By buttonExploreOtherTraining = getElementById("prakerja-banner-button");
    public final By searchBarSrp = getElementById("searchbar");

    public static HomepageLocator newInstance() {
        return new HomepageLocator();
    }

    public By programCategoryTitle(int i) {
        return getElementByXpath("//*[@id=\"program-category-list-container\"]/a[" + i + "]/p");
    }

    public By selectProgramCategoryList(int i) {
        return getElementByXpath("//*[@id=\"program-category-list-container\"]/a[" + i + "]");
    }

    public By listKeySearch(int i) {
        return getElementByXpath("//*[@class=\"search-recommendation\"]/div[1]/div/a[" + i + "]");
    }

    public By programName(int i) {
        return getElementByXpath("(//*[@class=\"program-card-title font-label-md-sb-lh24\"])[" + i + "]");
    }

    public By nameProgramOffline(int i) {
        return getElementByXpath("(//*[@id=\"program-offline-list-wrapper\"]//*[@class=\"program-card-title font-label-md-sb-lh24\"])[" + i + "]");
    }

    public By mitraName(int i) {
        return getElementByXpath("(//p[contains(@class, \"card-title\")])[" + i + "]");
    }

    public By mitraColaborationClass(String text) {
        return getElementByXpath("//*[contains(@class, \"button-extra-large\")]//div[contains(text(), \"" + text + "\")]");
    }

    public By getInstitutionCard(int i){
        return getElementByXpath("(//div[@id='institution-logo'])["+i+"]");
    }

    public By textContains(String text) {
        return getElementByXpath("//*[contains(text(), \"" + text + "\")]");
    }

    public By indexRibbonWebinar(int i) {
        return getElementByXpath("(//*[contains(@class, \"ribbon-webinar\")])[" + i + "]");
    }

    public By getRating(int i){
        return getElementByXpath("(//div[@id='program-closest-schedule']//span[contains(@class, 'rating-number')])[" + i + "]");
    }
}