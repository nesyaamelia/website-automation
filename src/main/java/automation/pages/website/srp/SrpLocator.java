package automation.pages.prakerja.srp;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class SrpLocator extends BaseLocator {

    public static final String loadingProcess = "Loading...";
    public static final String learningVideo = "Video Pembelajaran";
    public static final String offline = "Tatap Muka Offline";
    public static final String webinar = "Webinar";

    public final By sizeLabelFilterActivityType = getElementByXpath("//*[@class=\"desktop-side-filter\"]/section[2]/div[2]/div/div/label");
    public final By programCard = getElementById("program-card");
    public final By ribbonOffline = getElementByClassName("ribbon-offline");
    public final By ribbonWebinar = getElementByClassName("ribbon-webinar");
    public final By ribbonSpl = getElementByClassName("ribbon-self-paced-learning");

    public static SrpLocator newInstance() {
        return new SrpLocator();
    }

    public By filterActivityType(String filter) {
        return getElementByXpath("//span[contains(text(), \"" + filter + "\")]");
    }

    public By labelSpanFilterActivity(int i) {
        return getElementByXpath("//*[@class=\"desktop-side-filter\"]/section[2]/div[2]/div/div/label[" + i + "]/span[1]");
    }
}