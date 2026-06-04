package automation.pages.website.achievementprofile;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class AchievementProfileLocator extends BaseLocator {

    public final By cardPortfolio = getElementByXpath("(//div[@class='simple-portfolio-card'])[1]");
    public final By cardAchievements = getElementById("achievement-0");
    public final By cardAssessment = getElementById("assessment-0");
    public final By seeAllAssessment = getElementByXpath("(//button[@id='s-button'])[3]");
    public final By cardPrograms = getElementByXpath("(//div[@class='simple-finished-program-card'])[1]");
    public final By seeAllPrograms = getElementByXpath("(//button[@id='s-button'])[4]");
    public final By cardDetailPortfolio = getElementByXpath("//div[@class='detail-info-portfolio mt-5']");
    public final By certificate = getElementById("sertifikat-regular-page");
    public final By seeAllAchievement = getElementByXpath("(//button[@id='s-button'])[2]");
    public final By detailAchievement = getElementById("certificate-link-0");
    public final By detailAssessment = getElementById("detail-portfolio-0");
    public final By programDetail = getElementById("breadcrumb-program-detail");
    public final By detailPrograms = getElementByXpath("(//button[@id='s-button'])[1]");
    public final By textPrograms = getElementByXpath("//p[contains(text(), 'Programs')]");
    public final By totalCardAssessment = getElementByXpath("//a[contains(@id, 'assessment')]//p[contains(@class, 'release-text')]");

    public static AchievementProfileLocator newInstance() {
        return new AchievementProfileLocator();
    }

    public By getCardAssessment(int i){
        return getElementByXpath("(//a[contains(@id, 'assessment')]//p[contains(@class, 'release-text')])[" + i + "]");
    }
}
