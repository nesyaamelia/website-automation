package automation.pages.website.homepage.popupwhatsapp;

import automation.config.GlobalVariable;
import automation.config.WebsiteConfiguration;
import automation.pages.base.BaseModel;
import automation.pages.website.homepage.popupwhatsapp.api.PopupWhatsappApi;
import automation.pages.website.homepage.popupwhatsapp.api.response.CreateUserResponse;

public class PopupWhatsappModel extends BaseModel {

    public WebsiteConfiguration.Program program = GlobalVariable.getInstance().websiteConfiguration.program;
    public final String urlProgram = program.programFree;
    public String whatsappNumber;
    public String email;
    public int userId;
    public int programId = 9116;
    public boolean isGetWhatsappNumber = true;
    public boolean isCloseWhatsappNumber = false;

    public static PopupWhatsappModel newInstance() {
        return new PopupWhatsappModel();
    }

    public PopupWhatsappModel() {
        PopupWhatsappApi popupWhatsappApi = new PopupWhatsappApi();
        CreateUserResponse createUserResponse = popupWhatsappApi.createUser();
        email = createUserResponse.data.email;
        userId = createUserResponse.data.id;
    }

    public PopupWhatsappModel closePopupWhatsapp() {
        isCloseWhatsappNumber = true;
        return this;
    }

    public PopupWhatsappModel phoneNumberLessThan10() {
        isGetWhatsappNumber = false;
        whatsappNumber = "08100000";
        return this;
    }

    public PopupWhatsappModel phoneNumberMoreThan15() {
        isGetWhatsappNumber = false;
        whatsappNumber = "081398279872637"; // truncated for privacy
        return this;
    }

    public PopupWhatsappModel symbolPhoneNumber() {
        isGetWhatsappNumber = false;
        whatsappNumber = "!@#$%^&*()-=";
        return this;
    }
}