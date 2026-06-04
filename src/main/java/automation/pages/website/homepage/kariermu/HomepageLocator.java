package automation.pages.website.homepage.kariermu;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class HomepageLocator extends BaseLocator {

    public final By cardPilihan = getElementByXpath("//*[contains(text(), \"Pilihan Tim Karier.mu\")]");
    public final By cardTitle = getElementByXpath("(//*[@class=\"card-label font-subtitle-4 overlay-text\"])[1]");
    public final By cardImage = getElementByXpath("//*[@class=\"card-content-container overlay-container\"]/img");
    public final By programPilihanTitle = getElementByXpath("//*[@class=\"program-name font-title-1 desktop\"]");
    public final By programPilihanDate = getElementByXpath("//*[@class=\"program-update-date font-body-3\"]");
    public final By programPilihanDescription = getElementByXpath("//*[@class=\"program-description font-subtitle-3\"]");
    public final By programTitle = getElementByXpath("(//*[@class=\"program-name font-subtitle-1\"])[1]");
    public final By programImage = getElementByXpath("(//*[@class=\"program-info-left\"])[1]/img");
    public final By programDescription = getElementByXpath("(//*[@class=\"program-info-left\"])[1]/img");
    public final By buttonDetailProgram = getElementByXpath("(//*[@class=\"goto-btn-desktop\"])[1]");
    public final By titleOnProgramDetail = getElementByXpath("//*[@class=\"mb-md-4 mb-0 font-title-1\"]");
    public final By searchBar = getElementById("searchbar");
    public final By buttonSearch = getElementByXpath("//*[@alt=\"search-icon\"]");
    public final By bannerKariermuJobs = getElementById("banner-kariermu-jobs");
    public final By buttonKariermuJobs = getElementById("banner-kariermu-jobs-button");
    public final By buttonBantuan = getElementById("help-page");
    public final By buttonMitraKolaborasi = getElementById("mitra-microsite");
    public final By titleMitraKolaborasiPage = getElementByXpath("//*[@class=\"font-title-4 section-title\"]");
    public final By buttonBlog = getElementById("blog-page");
    public final By textDetailTicker = getElementById("s-ticker-action");
    public final By searchCount = getElementByXpath("//*[contains(@class, \"search-count\")]");
    public final By sectionPilihanRekomendasi = getElementByXpath("//*[contains(text(), \"Pilihan Rekomendasi\")]");
    public final By programRecommendation = getElementByXpath("//*[@id=\"program-recommendation-list-wrapper\"]/div");
    public final By carouselBannerHomepage = getElementById("carousel-dots-container");
    public final By programCardTitle = getElementByXpath("(//*[contains(@class, \"program-card-title\")])[1]");
    public final By imageUser = getElementByXpath("//*[@id=\"avatar-button\"]/div/img");
    public final By inputWhatsappNumber = getElementByXpath("//*[@class=\"input\"]");
    public final By buttonSubmit = getElementById("button-submit");
    public final By errorBorder = getElementByXpath("//*[contains(@class, \"is-error error-border\")]");
    public final By imagePopupWhatsapp = getElementByXpath("//*[@alt=\"Notifikasi Ponsel\"]");
    public final By getPhoneNumberWhatsapp = getElementByXpath("//*[@class=\"tag-wording\"]/strong");

    public static HomepageLocator newInstance() {
        return new HomepageLocator();
    }
}