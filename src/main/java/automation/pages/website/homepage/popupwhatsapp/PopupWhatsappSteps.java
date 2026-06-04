package automation.pages.website.homepage.popupwhatsapp;

import automation.pages.base.BaseSteps;

public interface PopupWhatsappSteps extends BaseSteps<PopupWhatsappPage, PopupWhatsappModel> {

    void enrollFreeProgram();
    void closeStartLearning();
    void closeCoachMark();
    void inputPhoneNumber(PopupWhatsappModel popupWhatsappModel);
}
