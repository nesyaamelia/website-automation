package automation.pages.website.homepage.homelxp;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class HomeLxpLocator extends BaseLocator {

    public final By seeAllLearningInProgress = getElementByXpath("(//a[@class='section-header__button'])[1]");
    public final By programmuPage = getElementByXpath("//p[contains(text(), 'Programmu')]");
    public final By tabUpcomingActivity = getElementById("tab-schedule-events-upcoming_activity");
    public final By cardScheduleEvent = getElementByXpath("//div[contains(@id,'card-schedule-event')]/button");
    public final By buttonOpenActivity = getElementById("open-activity-btn");
    public final By buttonAddToCalender = getElementById("add-to-calendar-btn");
    public final By modalLearningPage = getElementByCssSelector("div.modal.fade.show");
    public final By sectionScheduleEvents = getElementByXpath("//p[contains(text(), 'Scheduled Events')]");
    public final By buttonAddToGoogleCalender = getElementById("add-to-google-calendar");
    public final By buttonDownloadCalender = getElementById("add-to-device-calendar");
    public final By toastSuccessDownloadCalendar = getElementByClassName("s-toast-content");
    public final By seeAllLearningAchievement = getElementByXpath("(//button[@class='section-header__button'])[1]");
    public final By tabOverview = getElementById("tab-profile-overview");
    public final By cardAreasOfExpertise = getElementByXpath("(//div[@class='remaining-text'])[1]");
    public final By cardListCertificates = getElementByXpath("(//div[@class='remaining-text'])[2]");
    public final By sectionLearningAchievements = getElementByXpath("//p[contains(text(), 'Learning Achievements')]");
    public final By detailCertificate = getElementById("certificate-link-0");
    public final By certificatePage = getElementById("sertifikat-regular-page");
    public final By sectionPopularCourses = getElementByXpath("//p[contains(text(), 'Top 5 Popular Courses')]");
    public final By seeAllPopularCourses = getElementByXpath("(//button[@class='section-header__button'])[2]");
    public final By searcResultPage = getElementByXpath("//div[@class='sort-filter-count']");


    public static HomeLxpLocator newInstance() {
        return new HomeLxpLocator();
    }

}
