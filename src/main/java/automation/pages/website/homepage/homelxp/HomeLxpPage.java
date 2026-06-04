package automation.pages.website.homepage.homelxp;

import automation.pages.base.BasePage;
import automation.pages.base.EmptyModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class HomeLxpPage extends BasePage<EmptyModel, HomeLxpLocator> implements HomeLxpSteps {
    public static HomeLxpPage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new HomeLxpPage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = EmptyModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = HomeLxpLocator.newInstance();
    }

    @Override
    public HomeLxpPage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void seeAllLearningInProgress(){
        getElement().waitUntilClick(mLocator.seeAllLearningInProgress);
        boolean programmuPageAppear = getElement().handleElementPresent(mLocator.programmuPage, 5);
        logAndAssertTrue(programmuPageAppear, "success redirect to programmu page");
    }

    @Override
    public void openActivityScheduleEvent(){
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.sectionScheduleEvents));
        getElement().scrollToElementIgnoreNavbar(mLocator.sectionScheduleEvents);
        getElement().click(mLocator.tabUpcomingActivity);
        getElement().waitUntilClick(mLocator.cardScheduleEvent);
        getElement().waitUntilClick(mLocator.buttonOpenActivity);
        boolean learningPageAppear = getElement().handleElementPresent(mLocator.modalLearningPage, 5);
        logAndAssertTrue(learningPageAppear, "success open schedule event" +
                "</br> depend - KMWA-12280: Scheduled Events - modal Activity Details appear when user click card Upcoming Activity / Upcoming Deadline" +
                "</br> depend - KMWA-12282: Scheduled Events - redirect to Halaman Belajar when user click button 'Open Activity' in modal Activity Details");
    }

    @Override
    public void addToGoogleCalenderScheduleEvent(){
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.sectionScheduleEvents));
        getElement().scrollToElementIgnoreNavbar(mLocator.sectionScheduleEvents);
        getElement().click(mLocator.tabUpcomingActivity);
        getElement().waitUntilClick(mLocator.cardScheduleEvent);
        getElement().waitUntilClick(mLocator.buttonAddToCalender);
        getElement().waitUntilClick(mLocator.buttonAddToGoogleCalender);
        getElement().switchToTab(1);
        String getTitleGoogleCalender = webDriver.getTitle();
        logAndAssertEqual("Shareable Online Calendar and Scheduling - Google Calendar", getTitleGoogleCalender, "navigate to google calender" +
                "</br> depend - KMWA-12283: Scheduled Events - redirect to Popup Add Activity to Calendar when user click button 'Add to Calender' in modal Activity Details" +
                "</br> depend - KMWA-12284: Scheduled Events - user add activities on Google Calender when click button 'Add to Google Calender' on popup Add Activity to Calender");
    }

    @Override
    public void downloadCalender(){
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.sectionScheduleEvents));
        getElement().scrollToElementIgnoreNavbar(mLocator.sectionScheduleEvents);
        getElement().click(mLocator.tabUpcomingActivity);
        getElement().waitUntilClick(mLocator.cardScheduleEvent);
        getElement().waitUntilClick(mLocator.buttonAddToCalender);
        getElement().click(mLocator.buttonDownloadCalender);
        boolean isSuccessDownload = getElement().handleElementPresent(mLocator.toastSuccessDownloadCalendar, 5);
        logAndAssertTrue(isSuccessDownload, "success download calendar");
        stepsHelper.delay(globalVariable.midDelay);
        File folder = new File(System.getProperty("user.dir") + "/files/download/");
        stepsHelper.delay(globalVariable.shortDelay);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String fileName = file.getName();
                boolean fileExist = fileName.contains(".ics");
                logAndAssertTrue(fileExist, "Download file calender");
            }
        }
        for (File file : listOfFiles) {
            logInfo("Deleted filename :" + file.getName());
            boolean delete = file.delete();
            logAndAssertTrue(delete, "Delete file after verify the file is success download");
        }
    }

    @Override
    public void seeAllLearningAchievements(){
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.sectionLearningAchievements));
        getElement().scrollToElementIgnoreNavbar(mLocator.sectionLearningAchievements);
        getElement().click(mLocator.seeAllLearningAchievement);
        boolean isAchievementPageAppear = getElement().handleElementPresent(mLocator.tabOverview, 5);
        logAndAssertTrue(isAchievementPageAppear, "success redirect to achievement profile page");
    }

    @Override
    public void selectAreasOfExpertise(){
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.sectionLearningAchievements));
        getElement().scrollToElementIgnoreNavbar(mLocator.sectionLearningAchievements);
        getElement().click(mLocator.cardAreasOfExpertise);
        boolean isAreasOfExpertiseAppear = getElement().handleElementPresent(mLocator.tabOverview, 5);
        logAndAssertTrue(isAreasOfExpertiseAppear, "remain of card areas of expertise appear");
    }

    @Override
    public void selectRemainCertificates(){
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.sectionLearningAchievements));
        getElement().scrollToElementIgnoreNavbar(mLocator.sectionLearningAchievements);
        getElement().click(mLocator.cardListCertificates);
        getElement().waitUntilClick(mLocator.detailCertificate);
        getElement().switchToTab(1);
        boolean isCertificatesAppear = getElement().handleElementPresent(mLocator.certificatePage, 5);
        logAndAssertTrue(isCertificatesAppear, "remain of card certificates appear" +
                "</br> depend - KMWA-12297: Learning Achivements - redirect to profile page tab achievements when user click button '+X Certificates'" +
                "</br> depend - KMWA-12299: Learning Achivements - open new tab and certificates appear when user click card certificates");
    }

    @Override
    public void seeAllPopularCourses(){
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.sectionPopularCourses));
        getElement().scrollToElementIgnoreNavbar(mLocator.sectionPopularCourses);
        getElement().click(mLocator.seeAllPopularCourses);
        boolean isSrpAppear = getElement().handleElementPresent(mLocator.searcResultPage, 5);
        logAndAssertTrue(isSrpAppear, "success redirect to SRP");
    }
}
