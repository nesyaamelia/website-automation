package automation.pages.website.homepage.kariermu.b2b;

import automation.config.Host;
import automation.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static automation.pages.website.homepage.kariermu.b2b.HomepageB2BLocator.*;

public class HomepageB2BPage extends BasePage<HomepageB2BModel, HomepageB2BLocator> implements HomepageB2BSteps {

    public static HomepageB2BPage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new HomepageB2BPage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = HomepageB2BModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = HomepageB2BLocator.newInstance();
    }

    @Override
    public HomepageB2BPage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void handleRedirectButton(SectionType sectionType) {
        getElement().refresh();
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.businessBanner));
        switch (sectionType) {
            case BUSINESS:
                openAndRedirectPage(mLocator.containsClassTitle(1), mLocator.buttonLearnMore,
                        mLocator.hrefLearnMore, urlBusiness, null);
                break;
            case HOME_LOGO:
                getElement().navigateToUrl(Host.getKariermu() + "eksplor");
                getElement().waitUntilClick(mLocator.navbarLogo);
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.businessHomepage));
                boolean isShowB2bPage = getElement().verifyElementPresent(mLocator.businessHomepage);
                logAndAssertTrue(isShowB2bPage, "redirect to b2b page" +
                        "</br>depend - KMWA-12080: [Homepage] - redirect to homepage kariermu B2B when user click logo karier.mu on kariermu/explore");
                break;
            case SALES_TALENT:
                openAndRedirectPage(mLocator.containsClassTitle(2), mLocator.registerTalent(2),
                        mLocator.containsText(titleSalesTalent), urlSalesTalent,
                        "</br>depend - KMWA-12071: [Homepage] - button 'Daftar Sales Talent' on section produk B2C redirect to link kariermu talent blast");
                break;
            case TEACHER_TALENT:
                openAndRedirectPage(mLocator.containsClassTitle(2), mLocator.registerTalent(1),
                        mLocator.pageTalentTeacher, urlTalentTeacher,
                        "</br>depend - KMWA-12070: [Homepage] - button 'Daftar Teacher Talent' on section produk B2C redirect to link kariermu teacher talent");
                break;
            case PROGRAM_PRAKERJA:
                openAndRedirectPage(mLocator.containsClassTitle(3), mLocator.searchTrainingProgram,
                        mLocator.prakerjaLogo, urlPrakerja,
                        "</br>depend - KMWA-12074: [Homepage] - button 'Cari Program Prakerja' on section produk prakerja B2C redirect to web prakerja.karier.mu");
                break;
            case OTHER_PROGRAM:
                getElement().scrollToElement(mLocator.containsText(titleLearningProgram));
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.buttonSearchOtherProgram));
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.kariermuLogo));
                String getUrl = getElement().getUrl();
                boolean isRedirectTo = getUrl.contains("/eksplor");
                logAndAssertTrue(isRedirectTo, "redirect to eksplor page" +
                        "</br>depend - KMWA-12076: [Homepage] - button 'Cari Program Lainnya' on section eksplor program redirect to web kariermu/explore");
                break;
        }
    }

    @Override
    public void fillFormLeads(HomepageB2BModel homepageB2BModel) {
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.businessBanner));
        getElement().scrollToElement(mLocator.containsText(titleFormLeads));
        getElement().waitUntilSetText(mLocator.inputFullName, homepageB2BModel.fullName);
        getElement().waitUntilSetText(mLocator.inputEmail, homepageB2BModel.email);
        getElement().waitUntilSetText(mLocator.inputWhatsapp, homepageB2BModel.whatsappNumber);
        getElement().waitUntilSetText(mLocator.inputInstitution, homepageB2BModel.institution);
        getElement().waitUntilSetText(mLocator.inputRole, homepageB2BModel.role);
        getElement().waitUntilSetText(mLocator.inputInterestedProgram, homepageB2BModel.interestedProgram);
        getElement().waitUntilClick(mLocator.buttonContactUs);
        if (homepageB2BModel.isValid) {
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.snackbarSuccess));
            String getAlert = getElement().getText(mLocator.snackbarSuccess);
            logAndAssertEqual("Terima Kasih, kami akan segera menghubungi Anda.", getAlert, "submit leads");
        } else {
            boolean isShowError = getElement().verifyElementPresent(mLocator.alertError);
            logAndAssertTrue(isShowError, "show alert failed" + homepageB2BModel.logDepend);
        }
    }

    @Override
    public void fillFormSubscription(HomepageB2BModel homepageB2BModel) {
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.businessBanner));
        getElement().scrollToElement(mLocator.containsText(titleFormLeads));
        getElement().setText(mLocator.emailSubscription, homepageB2BModel.email);
        getElement().waitUntilClick(mLocator.buttonSubscription);
        if (homepageB2BModel.isValid) {
            boolean isShowSuccess = getElement().verifyElementPresent(mLocator.subscriptionSuccess);
            logAndAssertTrue(isShowSuccess, "subscription news letter");
        } else if (getElement().verifyElementPresent(mLocator.alertError)) {
            logAndAssertTrue(true, "failed submit subscription news letter");
        }
    }

    private void openAndRedirectPage(By locatorScroll, By clickButton, By visibility, String url, String logDepend) {
        String mainWindow = webDriver.getWindowHandle();
        getElement().scrollToElement(locatorScroll);
        getElement().waitUntilClick(clickButton);
        getElement().switchToTab(1);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(visibility));
        String getUrl = getElement().getUrl();
        boolean isRedirectTo = getUrl.contains(url);
        logAndAssertTrue(isRedirectTo, "redirect to expected page" + logDepend);
        webDriver.close();
        webDriver.switchTo().window(mainWindow);
    }
}