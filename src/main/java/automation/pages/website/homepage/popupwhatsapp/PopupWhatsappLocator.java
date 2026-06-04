package automation.pages.website.homepage.popupwhatsapp;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class PopupWhatsappLocator extends BaseLocator {

    public final By inputWhatsappNumber = By.xpath("//*[@id=\"input-whatsapp-number\"]/input");
    public final By buttonCloseModal = By.className("s-modal-close-icon");
    public final By tickerWhatsappNumber = By.className("is-info");
    public final By alertErrorInputField = By.xpath("//*[@class=\"text-error font-body-4\"]");
    public final By buttonSubmitWhatsappNumber = By.id("button-submit");
    public final By modalBody = getElementByXpath("//*[@class=\"modal-body\"]");
    public final By buttonStartLearning = getElementById("state-modal-cancel-button");
    public final By buttonCloseModalCoachmark = getElementById("modal-close-button");
    public final By buttonShepherd = getElementByXpath("//*[@class=\"coachmark-sekolahmu-old__next shepherd-button \"]");
    public final By blankSpace = getElementByXpath("//html");
    public final By logoImage = getElementById("logo-desktop");
    public final By enrollProgramButton = getElementById("enroll-program-btn");

    public static PopupWhatsappLocator newInstance() {
        return new PopupWhatsappLocator();
    }
}