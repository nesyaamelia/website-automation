package automation.pages.website.manageprofile.pinsetting;

import automation.pages.base.BaseSteps;

public interface PinSteps extends BaseSteps<PinPage, PinModel> {

    void createNewPinPoints();
    void updatePinPoints(PinModel pinModel);

    void freezePinUser();
}