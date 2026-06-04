package automation.pages.website.homepage.kariermu.b2b;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class HomepageB2BLocator extends BaseLocator {

    public static final String urlBusiness = "business";
    public static final String titleSalesTalent = "Raih Peluang Kerja dengan Lebih Optimal";
    public static final String urlSalesTalent = "talent/blast";
    public static final String urlTalentTeacher = "teacher-talent";
    public static final String urlPrakerja = "prakerja";
    public static final String titleLearningProgram = "Curated Learning Program";
    public static final String titleFormLeads = "Ready For Growth?";

    public By businessBanner = getElementByClassName("business-banner");
    public By buttonLearnMore = getElementById("learn-more-solution");
    public By hrefLearnMore = getElementByXpath("//*[@href=\"#learn-more\"]");
    public By navbarLogo = getElementById("navbar-logo");
    public By businessHomepage = getElementById("kariermu-logo-wrapper");
    public By pageTalentTeacher = getElementByXpath("//*[@href=\"https://kariermu.id/TeacherTalent\"]");
    public By searchTrainingProgram = getElementById("search-training-program");
    public By prakerjaLogo = getElementById("prakerja-logo");
    public By buttonSearchOtherProgram = getElementById("search-other-programs");
    public By kariermuLogo = getElementById("kariermu-logo");
    public By inputFullName = getElementById("input-name");
    public By inputEmail = getElementById("input-email");
    public By inputWhatsapp = getElementById("input-whatsapp");
    public By inputInstitution = getElementById("input-insitution");
    public By inputRole = getElementById("input-role");
    public By inputInterestedProgram = getElementById("input-interested-program");
    public By buttonContactUs = getElementById("contact-us");
    public By snackbarSuccess = getElementByXpath("//*[@class=\"snackbar-atom-wrapper snackbar-atom-wrapper--success snackbar-atom-wrapper--5s\"]");
    public By alertError = getElementByClassName("is-error");
    public By emailSubscription = getElementById("email-subscription");
    public By buttonSubscription = getElementById("subsribe");
    public By subscriptionSuccess = getElementByClassName("subscribe-success");
    public By closeBanner = getElementById("s-popup-close");

    public static HomepageB2BLocator newInstance() {
        return new HomepageB2BLocator();
    }

    public By containsClassTitle(int i) {
        return getElementByXpath("(//*[contains(@class, \"business-title\")])[" + i + "]");
    }

    public By containsText(String text) {
        return getElementByXpath("//*[contains(text(), \"" + text + "\")]");
    }

    public By registerTalent(int i) {
        return getElementByXpath("(//*[@id=\"register-sales-talent\"])[" + i + "]");
    }
}
