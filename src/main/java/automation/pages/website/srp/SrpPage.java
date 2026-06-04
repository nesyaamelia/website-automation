package automation.pages.prakerja.srp;

import automation.config.Host;
import automation.pages.base.BasePrakerjaPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static automation.pages.prakerja.srp.SrpLocator.*;

public class SrpPage extends BasePrakerjaPage<SrpModel, SrpLocator> implements SrpSteps {

    public static SrpPage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new SrpPage().init(webDriver, webDriverWait);
    }

    @Override
    public SrpPage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void setupController() {
        this.mModel = SrpModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = SrpLocator.newInstance();
    }

    @Override
    public void search(TypeActivityType activityType) {
        int getProgramSize = 0;
        int getTagProgram = 0;
        boolean isShowFilter = false;
        getElement().navigateToUrl(Host.getPrakerja() + "program?search");
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.filterActivityType(offline)));
        int filterActivityType = getElement().getSize(mLocator.sizeLabelFilterActivityType);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= filterActivityType; i++) {
            String getLabelActivityType = getElement().getText(mLocator.labelSpanFilterActivity(i));
            isShowFilter = getLabelActivityType.equalsIgnoreCase("Tatap Muka Offline") || getLabelActivityType.equalsIgnoreCase("Webinar") || getLabelActivityType.equalsIgnoreCase("Video Pembelajaran");
            if (!isShowFilter) {
                break;
            }
            list.add(getLabelActivityType);
        }
        logAndAssertTrue(isShowFilter, "show filter tipe aktivitas: " + list);
        if (activityType != null) {
            switch (activityType) {
                case TATAP_MUKA_OFFLINE:
                    getElement().click(mLocator.filterActivityType(offline));
                    getElement().waitUntilLoadingProcess(loadingProcess );
                    getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.programCard));
                    getProgramSize = getElement().getSize(mLocator.programCard);
                    getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.ribbonOffline));
                    getTagProgram = getElement().getSize(mLocator.ribbonOffline);
                    break;
                case WEBINAR:
                    getElement().click(mLocator.filterActivityType(webinar));
                    getElement().waitUntilLoadingProcess(loadingProcess);
                    getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.programCard));
                    getProgramSize = getElement().getSize(mLocator.programCard);
                    getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.ribbonWebinar));
                    getTagProgram = getElement().getSize(mLocator.ribbonWebinar);
                    break;
                case VIDEO_PEMBELAJARAN:
                    String getParamsUrl = webDriver.getCurrentUrl();
                    if (!getParamsUrl.contains("program?training_type=3")) {
                        getElement().click(mLocator.filterActivityType(learningVideo));
                    }
                    getElement().waitUntilLoadingProcess(loadingProcess);
                    getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.programCard));
                    getProgramSize = getElement().getSize(mLocator.programCard);
                    getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.ribbonSpl));
                    getTagProgram = getElement().getSize(mLocator.ribbonSpl);
                    break;
            }
        }
        logAndAssertEqual(getProgramSize, getTagProgram, "filter program");
    }
}
