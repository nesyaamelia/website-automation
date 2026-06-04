package automation.pages.website.homepage.navigationbar;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class NavigationBarLocator extends BaseLocator {

    public static final String prakerjaLogo = "prakerja-logo";
    public static final String kariermuLogo = "kariermu-logo";

    public final By textShareProrgam = getElementByXpath("//*[@class=\"d-flex align-items-center m-auto button-font-style button-link-local\"]");
    public final By kelasPrakerja = getElementByXpath("(//*[text()=\"Kelas Prakerja\"])[1]");
    public final By getProgramName = getElementByXpath("//*[@class=\"mb-md-4 mb-0 font-title-1\"]");
    public final By textBantuan = getElementById("faq-button");
    public final By iconTanyamu = getElementById("tanyamu_icon");
    public final By textProgrammu = getElementById("programmu-button");
    public final By shoppingChartIcon = getElementById("cart-icon");
    public final By iconNotification = getElementById("notification_icon");
    public final By textHeadingPage = getElementByClassName("pagetitle");
    public final By textProductKami = getElementByXpath("(//*[contains(text(), \"Produk Kami\")])[3]");
    public final By textJobKariermu = getElementByXpath("(//a[contains(text(), \"Lowongan Kerja\")])[3]");
    public final By inputWhatsappNumberHomepage = getElementById("input-whatsapp-number");
    public final By placeholderInputEmail = getElementByXpath("//*[@placeholder=\"Masukan e-mail kamu\"]");
    public final By buttonBanner = getElementById("banner-button-0");
    public final By logoKariermu = getElementById("kariermu-logo");
    public final By logoKariermuAtActivityPage = getElementByXpath("(//*[@class=\"kariermu-logo\"])[2]");
    public final By textForBusiness = getElementById("business-button");
    public final By textForTalent = getElementById("talent-button");
    public final By textTeacherTalent = getElementById("teacher-talent-button");
    public final By textSalesTalent = getElementById("sales-talent-button");
    public final By textPrakerja = getElementById("prakerja-navbar-button");
    public final By textProfile = getElementById("profile-button");
    public final By textHome = getElementById("home-button");

    public static NavigationBarLocator newInstance() {
        return new NavigationBarLocator();
    }

    public String executeLogo(String logo) {
        return "document.querySelector('[id=\"" + logo + "\"]').click()";
    }
}