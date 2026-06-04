package automation.pages.website.homepage.navigationbar;

import automation.config.Host;
import automation.pages.base.BasePage;
import automation.pages.base.EmptyModel;
import automation.pages.website.manageprofile.EditProfileLocator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static automation.pages.website.homepage.navigationbar.NavigationBarLocator.kariermuLogo;
import static automation.pages.website.homepage.navigationbar.NavigationBarLocator.prakerjaLogo;

public class NavigationBarPage extends BasePage<EmptyModel, NavigationBarLocator> implements NavigationBarSteps {

    private final EditProfileLocator editProfileLocator = EditProfileLocator.newInstance();

    public static NavigationBarPage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new NavigationBarPage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = EmptyModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = NavigationBarLocator.newInstance();
    }

    @Override
    public NavigationBarPage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void navigationTo(NavigationType navigationType) {
        try {
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.inputWhatsappNumberHomepage));
        } catch (TimeoutException t) {
            if (getElement().verifyElementPresent(mLocator.inputWhatsappNumberHomepage)) {
                getElement().waitUntilClick(editProfileLocator.buttonCloseModal);
            }
        }
        switch (navigationType) {
            case LOGO:
                navigateToHomePageFromLogo();
                break;
            case KELAS_PRAKERJA:
                navigateToPrakerjaClass();
                break;
            case BANTUAN:
                navigateToHelpPage();
                break;
            case TANYAMU:
                navigateToTanyamuPage();
                break;
            case PROGRAMMU:
                navigateToProgrammuPage();
                break;
            case DAFTAR_PILIHAN:
                navigationToListOptions();
                break;
            case NOTIFIKASI:
                navigationToNotification();
                break;
            case PRODUK_KAMI:
                navigationToOurProduct();
                break;
            case HOMEPAGE:
                String getCurrentUrl = webDriver.getCurrentUrl();
                if (getCurrentUrl.contains("prakerja")) {
                    getElement().navigateToUrl(Host.getPrakerja());
                } else {
                    try {
                        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.logoKariermu));
                        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.logoKariermu));
                        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.logoKariermu));
                    } catch (Exception e) {
                        getElement().waitUntilClick(mLocator.logoKariermuAtActivityPage);
                    }
                }
                break;
            case HOMEPAGE_PRAKERJA:
                getElement().navigateToUrl(Host.getPrakerja());
                break;
            case FOR_BUSINESS:
                navigationToForBusiness();
                break;
            case TEACHER_TALENT:
                navigationToTeacherTalent();
                break;
            case SALES_TALENT:
                navigationToSalesTalent();
                break;
            case PRAKERJA:
                navigationToPrakerja();
                break;
            case PROFILE:
                navigationToAchievementProfile();
                break;
            case HOME:
                navigationToHomeLxp();
                break;
        }
    }

    private void navigateToHomePageFromLogo() {
        String mainWindow = webDriver.getWindowHandle();
        String getCurrentUrl = webDriver.getCurrentUrl();
        if (getCurrentUrl.contains("prakerja")) {
            getElement().navigateToUrl(Host.getPrakerja() + "program/journey-buy-program");
        } else {
            getElement().navigateToUrl(Host.getKariermu() + "program/journey-buy-program");
        }
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.textShareProrgam));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.textShareProrgam));
        if (getCurrentUrl.equalsIgnoreCase(Host.getKariermu()) || !getCurrentUrl.contains("prakerja")) {
            getElement().executeJavascript(mLocator.executeLogo(kariermuLogo));
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonBanner));
            getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonBanner));
            String getUrl = webDriver.getCurrentUrl();
            logAndAssertEqual(Host.getKariermu(), getUrl, "navigate to homepage");
        } else if (getCurrentUrl.contains(Host.getPrakerja())) {
            getElement().executeJavascript(mLocator.executeLogo(prakerjaLogo));
            getElement().switchWindow();
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.placeholderInputEmail));
            getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.placeholderInputEmail));
            String getTitlePage = webDriver.getTitle();
            boolean isTitlePage = getTitlePage.contains("Gali potensi dirimu dengan Kartu Prakerja");
            logAndAssertTrue(isTitlePage, "redirect to page");
            webDriver.close();
            webDriver.switchTo().window(mainWindow);
        }
    }

    private void navigateToPrakerjaClass() {
        String mainWindow = webDriver.getWindowHandle();
        getElement().click(mLocator.kelasPrakerja);
        getElement().switchWindow();
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.textShareProrgam));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.textShareProrgam));
        String getProgramName = getElement().getText(mLocator.getProgramName);
        if (getProgramName.contains("Kelas Persiapan Kerja")) {
            logPassed("Success open class: " + getProgramName);
            webDriver.close();
            webDriver.switchTo().window(mainWindow);
        } else {
            logFailed("Failed open class prakerja", null);
        }
    }

    private void navigateToHelpPage() {
        String getCurrentUrl = webDriver.getCurrentUrl();
        if (getCurrentUrl.equalsIgnoreCase(Host.getKariermu())) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.textBantuan));
            stepsHelper.delay(globalVariable.shortDelay);
            String getTitlePage = webDriver.getTitle();
            logAndAssertEqual("Beranda - Panduan Karier.mu", getTitlePage, "navigate to help page");
        } else if (getCurrentUrl.equalsIgnoreCase(Host.getPrakerja())) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.textBantuan));
            stepsHelper.delay(globalVariable.shortDelay);
            String getTitlePage = webDriver.getTitle();
            logAndAssertEqual("Karier.mu & Karier.mu Prakerja - Panduan Karier.mu", getTitlePage, "navigate to help page");
        }
    }

    private void navigateToTanyamuPage() {
        getElement().waitUntilClick(mLocator.iconTanyamu);
        String getTitlePage = webDriver.getTitle();
        logAndAssertEqual("Diskusi Tanya Jawab Program | Karier.mu", getTitlePage, "navigate to page tanyamu");
    }

    private void navigateToProgrammuPage() {
        try {
            getElement().waitUntilClick(mLocator.textProgrammu);
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.textProgrammu));
        }
        stepsHelper.delay(globalVariable.midDelay);
        String getCurrentUrl = webDriver.getCurrentUrl();
        if (getCurrentUrl.contains(".dev")) {
            getElement().navigateToUrl("https://dev-area:Sekolahmu-Dev-ENV@app.dev.karier.mu/programmu");
        }
        String getTitlePage = webDriver.getTitle();
        boolean isContainsTitle = getTitlePage.contains("Karier.mu");
        logAndAssertTrue(isContainsTitle, "verify title page programmu");
        if (getElement().verifyElementPresent(mLocator.errorGeneral)) {
            getElement().click(mLocator.buttonBackErrorGeneral);
        }
    }

    private void navigationToListOptions() {
        try {
            getElement().waitUntilClick(mLocator.shoppingChartIcon);
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.shoppingChartIcon));
        }
        stepsHelper.delay(globalVariable.shortDelay);
        String getTitlePage = webDriver.getTitle();
        logAndAssertEqual("Daftar Pilihan", getTitlePage, "navigate to daftar pilihan page");
    }

    private void navigationToNotification() {
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.iconNotification));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.iconNotification));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.iconNotification));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.textHeadingPage));
        String headingPage = getElement().getText(mLocator.textHeadingPage);
        logAndAssertEqual("Notifikasi", headingPage, "redirect to page notification");
    }

    private void navigationToOurProduct() {
        String mainWindow = webDriver.getWindowHandle();
        getElement().waitUntilClick(mLocator.textProductKami);
        getElement().waitUntilClick(mLocator.textJobKariermu);
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().switchWindow();
        String getTitlePage = webDriver.getTitle();
        logAndAssertEqual("Cari Lowongan Kerja di Indonesia | Karier.mu Jobs", getTitlePage, "redirect to page our product");
        webDriver.close();
        webDriver.switchTo().window(mainWindow);
    }

    private void navigationToForBusiness(){
        getElement().waitUntilClick(mLocator.textForBusiness);
        getElement().switchToTab(1);
        String getTitlePageBusiness = webDriver.getTitle();
        logAndAssertEqual("Learning Platform - Kariermu For Business", getTitlePageBusiness, "navigate to page kariermu for business");
    }

    private void navigationToTeacherTalent(){
        getElement().waitUntilClick(mLocator.textForTalent);
        getElement().waitUntilClick(mLocator.textTeacherTalent);
        getElement().switchToTab(1);
        String getTitlePageTeacher = webDriver.getTitle();
        logAndAssertEqual("Teacher-Talent – Lab Kariermu", getTitlePageTeacher, "navigate to page teacher talent");
    }

    private void navigationToSalesTalent(){
        getElement().waitUntilClick(mLocator.textForTalent);
        getElement().waitUntilClick(mLocator.textSalesTalent);
        getElement().switchToTab(1);
        String getTitlePageSales = webDriver.getTitle();
        logAndAssertEqual("BLAST – Lab Kariermu", getTitlePageSales, "navigate to page sales talent");
    }

    private void navigationToPrakerja(){
        getElement().waitUntilClick(mLocator.textPrakerja);
        String getTitlePagePrakerja = webDriver.getTitle();
        logAndAssertEqual("Prakerja", getTitlePagePrakerja, "navigate to page prakerja kariermu");
    }

    private void navigationToAchievementProfile(){
        getElement().waitUntilClick(mLocator.textProfile);
        String getTitlePageProfile = webDriver.getTitle();
        logAndAssertEqual("Karier.mu", getTitlePageProfile, "navigate to page achievement profile");
    }

    private void navigationToHomeLxp(){
        getElement().waitUntilClick(mLocator.textHome);
        String getTitlePageHome = webDriver.getTitle();
        logAndAssertEqual("Karier.mu", getTitlePageHome, "navigate to page home");
    }
}