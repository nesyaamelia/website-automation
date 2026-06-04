package automation.pages.website.homepage.kariermu;

import automation.config.Host;
import automation.pages.base.BasePage;
import automation.pages.website.manageprofile.EditProfileLocator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

public class HomepagePage extends BasePage<HomepageModel, HomepageLocator> implements HomepageSteps {

    public final EditProfileLocator editProfileLocator = EditProfileLocator.newInstance();

    public static HomepagePage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new HomepagePage().init(webDriver, webDriverWait);
    }

    @Override
    public HomepagePage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void setupController() {
        this.mModel = HomepageModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = HomepageLocator.newInstance();
    }

    @Override
    public void PilihanTimKariermuSection() {
        getElement().scrollToElement(mLocator.cardPilihan);
        if (getElement().verifyElementPresent(mLocator.cardImage)) {
            logPassed("Image of card is exist");
        } else {
            logInfo("There is no image card");
        }
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.cardTitle));
        String getTitleCard = getElement().getText(mLocator.cardTitle);
        if (getTitleCard.equalsIgnoreCase("Seri Belajar Menyenangkan")) {
            logPassed("There is title of card, title is " + getTitleCard);
        } else {
            logInfo("Selected program section is incorect");
        }
        getElement().click(mLocator.cardTitle);
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.programPilihanTitle));
        String getTitleOptions = getElement().getText(mLocator.programPilihanTitle);
        if (getTitleOptions.equalsIgnoreCase("Seri Belajar Menyenangkan")) {
            if (getElement().verifyElementPresent(mLocator.programPilihanDate)) {
                String getDateProgram = getElement().getText(mLocator.programPilihanDate);
                logPassed("Successfully selected program. Title : " + getTitleOptions + ", Date : " + getDateProgram);
            }
        } else {
            logInfo("Incorect redirect path");
        }
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.programPilihanDescription));
        if (getElement().verifyElementPresent(mLocator.programPilihanDescription)) {
            logPassed("There is description program pilihan");
        } else {
            logInfo("There is no description");
        }
    }

    @Override
    public void ProgramList() {
        getElement().scrollToElement(mLocator.cardPilihan);
        getElement().click(mLocator.cardTitle);
        stepsHelper.delay(globalVariable.shortDelay);
        getElement().scrollToElement(mLocator.programTitle);
        String getTitleProgram = getElement().getText(mLocator.programTitle);
        if (getElement().verifyElementPresent(mLocator.programTitle) && getElement().verifyElementPresent(mLocator.programImage) && getElement().verifyElementPresent(mLocator.programDescription)) {
            logPassed("Program consist of Title, Image and Description. Title : " + getTitleProgram);
        } else {
            logInfo("Program not found");
        }
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonDetailProgram));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonDetailProgram));
        getElement().click(mLocator.buttonDetailProgram);
        stepsHelper.delay(globalVariable.shortDelay);
        if (getElement().verifyElementPresent(mLocator.titleOnProgramDetail)) {
            logPassed("Successfully redirect to Program Detail");
        } else {
            logInfo("Cannot redirect to Program Detail");
        }
    }

    @Override
    public void searchProgram(HomepageModel homepageModel) {
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.searchBar));
        if (homepageModel.searchFromUrl) {
            getElement().navigateToUrl(Host.getKariermu() + "program?search=" + mModel.searchKeyword);
        } else {
            getElement().click(mLocator.searchBar);
            getElement().setText(mLocator.searchBar, mModel.searchKeyword);
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.buttonSearch));
        }
        getElement().allowPushNotif();
        getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.carouselBannerHomepage));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.programCardTitle));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.programCardTitle));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.searchCount));
        String getTitle = getElement().getText(mLocator.searchCount);
        boolean isTitleMatch = Pattern.matches("Menampilkan \\d+ - \\d+ dari total \\d+ pencarian untuk \"" + mModel.searchKeyword + "\"", getTitle);
        logAndAssertTrue(isTitleMatch, "show title" +
                "</br>depend - KMWA-7442: Ensure Wording Counting Hasil Pencarian Appear When User Input Keyword On Search Bar");
    }

    @Override
    public void programRecommendation() {
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.imageUser));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.imageUser));
        getElement().scrollToElement(mLocator.sectionPilihanRekomendasi);
        boolean isShowListProgram = getElement().verifyElementPresent(mLocator.programRecommendation);
        logAndAssertTrue(isShowListProgram, "show list program recommendation");
    }

    @Override
    public void entryPointKariermuJobs() {
        if (getElement().verifyElementPresent(mLocator.bannerKariermuJobs)) {
            logPassed("Banner Kariermu Jobs successfully show");
        } else {
            logFailed("Banner Kariermu Jobs no show correctly", null);
        }
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonKariermuJobs));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonKariermuJobs));
        getElement().click(mLocator.buttonKariermuJobs);
        stepsHelper.delay(globalVariable.shortDelay);
        String mainWindow = webDriver.getWindowHandle();
        getElement().switchWindow();
        String currentUrl = webDriver.getCurrentUrl();
        boolean isUrlBlast = currentUrl.contains(mModel.urlKariermuJobs);
        logAndAssertTrue(isUrlBlast, "redirect to url blast");
        webDriver.close();
        webDriver.switchTo().window(mainWindow);
    }

    @Override
    public void validateFooter(String footer) {
        String mainWindow = webDriver.getWindowHandle();
        switch (footer) {
            case "Mitra Kolaborasi":
                getElement().scrollToElement(mLocator.buttonMitraKolaborasi);
                getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonMitraKolaborasi));
                getElement().click(mLocator.buttonMitraKolaborasi);
                stepsHelper.delay(globalVariable.shortDelay);
                if (getElement().verifyElementPresent(mLocator.titleMitraKolaborasiPage)) {
                    logPassed("Successfully show title page");
                } else {
                    logFailed("Cannot show title page", null);
                }
                break;
            case "Blog":
                getElement().scrollToElement(mLocator.buttonBlog);
                getElement().click(mLocator.buttonBlog);
                getElement().switchWindow();
                String getBlogUrl = webDriver.getCurrentUrl();
                if (getBlogUrl.equalsIgnoreCase(mModel.urlBlogPage)) {
                    logPassed("Successfully redirect to Blog Kariermu");
                    webDriver.close();
                    webDriver.switchTo().window(mainWindow);
                } else {
                    logFailed("Cannot redirect to Blog Kariermu", null);
                }
                break;
            case "Bantuan":
                getElement().scrollToElement(mLocator.buttonBantuan);
                getElement().click(mLocator.buttonBantuan);
                getElement().switchWindow();
                String getHelpUrl = webDriver.getCurrentUrl();
                if (getHelpUrl.equalsIgnoreCase(mModel.urlBantuanPage)) {
                    logPassed("Successfully redirect to Help page");
                    webDriver.close();
                    webDriver.switchTo().window(mainWindow);
                } else {
                    logFailed("Cannot redirect to Help page", null);
                }
                break;
        }
    }

    @Override
    public void tickerAccess(HomepageModel homepageModel) {
        getElement().navigateToUrl(homepageModel.goToUrl);
        getElement().waitUntilClick(mLocator.textDetailTicker);
        boolean isRedirectTicker = getElement().verifyElementNotPresent(mLocator.textDetailTicker);
        logAndAssertTrue(isRedirectTicker, "show ticker");
    }

    @Override
    public void popupModalWhatsapp(HomepageModel homepageModel) {
        try {
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.imagePopupWhatsapp));
            if (homepageModel.isCloseWhatsappNumber) {
                getElement().click(editProfileLocator.buttonCloseModal);
                getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(editProfileLocator.buttonCloseModal));
                boolean inputWhatsappNumber = getElement().verifyElementNotPresent(mLocator.inputWhatsappNumber);
                logAndAssertTrue(inputWhatsappNumber, "is close popup whatsapp number");
            } else {
                if (homepageModel.isRefresh) {
                    getElement().refresh();
                    getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.imagePopupWhatsapp));
                    getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.imagePopupWhatsapp));
                    boolean inputWhatsappNumber = getElement().verifyElementPresent(mLocator.imagePopupWhatsapp);
                    logAndAssertTrue(inputWhatsappNumber, "show popup whatsapp after refresh page");
                    return;
                }
                String infoNumber = getElement().getText(mLocator.getPhoneNumberWhatsapp).replaceAll("\\D", "");
                if (!homepageModel.isGetWhatsappNumber) {
                    getElement().setText(mLocator.inputWhatsappNumber, homepageModel.whatsappNumber);
                    if (Pattern.matches("\\d+", homepageModel.whatsappNumber) || Pattern.matches("\\W+", homepageModel.whatsappNumber)) {
                        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.errorBorder));
                        boolean isError = getElement().verifyElementPresent(mLocator.errorBorder);
                        logAndAssertTrue(isError, "handle length whatsapp number");
                    } else {
                        boolean inputWhatsappNumber = getElement().verifyElementNotPresent(editProfileLocator.alertErrorInputField);
                        logAndAssertTrue(inputWhatsappNumber, "pattern not equals");
                    }
                    return;
                } else {
                    getElement().setText(mLocator.inputWhatsappNumber, infoNumber);
                }
                getElement().waitUntilClick(mLocator.buttonSubmit);
                getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.inputWhatsappNumber));
                boolean successSavePhoneNumber = getElement().verifyElementNotPresent(mLocator.inputWhatsappNumber);
                logAndAssertTrue(successSavePhoneNumber, "is success save input whatsapp number");
            }
        } catch (TimeoutException t) {
            if (homepageModel.isNotDependWhatsapp) {
                logAndAssertTrue(true, "not verify whatsapp number because is not depend");
            } else {
                logFailed("popup whatsapp not appears", null);
                boolean popupWhatsapp = getElement().verifyElementNotPresent(mLocator.inputWhatsappNumber);
                logAndAssertTrue(popupWhatsapp, "not show popup whatsapp");
            }
        }
    }
}