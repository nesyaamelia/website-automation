package automation.pages.website.manageprofile.pinsetting;

import automation.pages.base.BaseModel;

public class PinModel extends BaseModel {

    public String newPin = System.getenv().getOrDefault("TEST_NEW_PIN", "000000");
    public String oldPin = System.getenv().getOrDefault("TEST_OLD_PIN", "000000");
    public String invalidPin = "999999";
    public boolean isFailed = false;

    public static PinModel newInstance() {
        return new PinModel();
    }

    public PinModel editValidPin() {
        newPin = System.getenv().getOrDefault("TEST_CHANGE_PIN", "111111");
        return this;
    }

    public PinModel editSameNewPinWithOldPin() {
        newPin = System.getenv().getOrDefault("TEST_NEW_PIN", "000000");
        isFailed = true;
        return this;
    }

}