package automation.pages.website.homepage.popupwhatsapp.api;

import automation.pages.website.homepage.popupwhatsapp.PopupWhatsappModel;
import automation.pages.website.homepage.popupwhatsapp.api.response.CreateUserResponse;

public interface PopupWhatsappSteps {

    CreateUserResponse createUser();
    int enrollProgramUser(PopupWhatsappModel popupWhatsappModel);
}