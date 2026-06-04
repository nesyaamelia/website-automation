package automation.pages.website.achievementprofile;

import automation.pages.base.BasePage;
import automation.pages.base.EmptyModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AchievementProfilePage extends BasePage<EmptyModel, AchievementProfileLocator> implements AchievementProfileSteps {
    public static AchievementProfilePage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new AchievementProfilePage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = EmptyModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = AchievementProfileLocator.newInstance();
    }

    @Override
    public AchievementProfilePage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void selectPortfolio(){
        getElement().waitUntilClick(mLocator.cardPortfolio);
        boolean isDetailPortfolioAppear = getElement().handleElementPresent(mLocator.cardDetailPortfolio, 5);
        logAndAssertTrue(isDetailPortfolioAppear, "success redirect to detail portfolio and card detail portfolio appear" +
                "</br> depend - KMWA-12162: Halaman Capaian - redirect to detail portofolio page when user click card on tab portofolio" +
                "</br> depend - KMWA-12148: Halaman Capaian - section areas of expertise appear when user has completed the program 100%");
    }

    @Override
    public void selectAchievementsOnTabOverview(){
        getElement().waitUntilClick(mLocator.cardAchievements);
        getElement().switchToTab(1);
        boolean isCertificateAppear = getElement().handleElementPresent(mLocator.certificate, 5);
        logAndAssertTrue(isCertificateAppear, "success redirect and certificate appear");
    }

    @Override
    public void seeAllAchievements(){
        getElement().waitUntilClick(mLocator.seeAllAchievement);
        getElement().waitUntilClick(mLocator.detailAchievement);
        getElement().switchToTab(1);
        boolean isTabAchievementAppear = getElement().handleElementPresent(mLocator.certificate, 5);
        logAndAssertTrue(isTabAchievementAppear, "success redirect to tab achievements" +
                "</br> depend - KMWA-12167: Halaman Capaian - button 'Lihat Semua' on section achievements tab overview redirect to tab achievements" +
                "</br> depend - KMWA-12168: Halaman Capaian - button 'Lihat Semua' on section achievements tab overview appear with minimal 1 card" +
                "</br> depend - KMWA-12172: Halaman Capaian - open new tab sertifikat when user click button 'Lihat Kredensial'");
    }

    @Override
    public void selectAssessmentOnTabOverview(){
        sortingDateAssessment();
        getElement().waitUntilClick(mLocator.cardAssessment);
        getElement().switchToTab(1);
        String getTitleAssessment = webDriver.getTitle();
        logAndAssertEqual("Talentics Assessment", getTitleAssessment, "success open assessment result page" +
                "</br> depend - KMWA-12175: Halaman Capaian - The order of asessment results cards from the left is the most recent");
    }

    @Override
    public void seeAllAssessments(){
        getElement().waitUntilClick(mLocator.seeAllAssessment);
        getElement().waitUntilClick(mLocator.detailAssessment);
        getElement().switchToTab(1);
        String getTitleAssessment = webDriver.getTitle();
        logAndAssertEqual("Talentics Assessment", getTitleAssessment, "success open assessment result page" +
                "</br> depend - KMWA-12177: Halaman Capaian - button 'Lihat Semua' on section assessment results tab overview redirect to tab assessment results" +
                "</br> depend - KMWA-12179: Halaman Capaian - open new tab assessment results when user click button 'Hasil Assessment' on tab assessment results");
    }

    @Override
    public void selectProgramsOnTabOverview(){
        getElement().waitUntilClick(mLocator.cardPrograms);
        boolean isProgramDetailAppear = getElement().handleElementPresent(mLocator.programDetail, 5);
        logAndAssertTrue(isProgramDetailAppear, "success redirect to program detail" +
                "</br> depend - KMWA-12180: Halaman Capaian - section programs appear on tab overview" +
                "</br> depend - KMWA-12183: Halaman Capaian - programs that appear in the section programs are only programs with 100% completion" +
                "</br> depend - KMWA-12184: Halaman Capaian - open new tab programs when user click card on section programs");
    }

    @Override
    public void seeAllPrograms(){
        getElement().waitUntilClick(mLocator.seeAllPrograms);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.textPrograms));
        getElement().waitUntilClick(mLocator.detailPrograms);
        getElement().switchToTab(1);
        boolean programDetail = getElement().handleElementPresent(mLocator.programDetail, 5);
        logAndAssertTrue(programDetail, "success redirect to program detail" +
                "</br> depend - KMWA-12185: Halaman Capaian - button 'Lihat Semua' on section programs tab overview redirect to tab programs" +
                "</br> depend - KMWA-12186: Halaman Capaian - open new tab programs when user click card on tab programs");
    }

    private void sortingDateAssessment(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", new java.util.Locale("id"));
        int totalCardAssessment = getElement().getSize(mLocator.totalCardAssessment);
        for (int i = 0; i < totalCardAssessment - 1; i++) {
            String getBeforeDateAssessment = getElement().getText(mLocator.getCardAssessment(i));
            String getAfterDateAssessment = getElement().getText(mLocator.getCardAssessment(i+1));
            String getBeforeDate = getBeforeDateAssessment.substring(getBeforeDateAssessment.indexOf("Pada") + 5);
            String getAfterDate = getAfterDateAssessment.substring(getAfterDateAssessment.indexOf("Pada") + 5);

            LocalDate current = LocalDate.parse(getBeforeDate, formatter);
            LocalDate next = LocalDate.parse(getAfterDate, formatter);
            logAndAssertTrue(!current.isBefore(next), "sorting most recent card assessment");
        }
    }
}
