package automation.pages.website.manageprofile;

import automation.network.kelolauser.response.CreateUserResponse;
import automation.pages.base.BaseSteps;

public interface EditProfileSteps extends BaseSteps<EditProfilePage, EditProfileModel> {

    void fillPrimaryDataUser(EditProfileModel editProfileModel, String email);
    void fillAdditionalDataUser(EditProfileModel editProfileModel);
    void fillPreferencesDataUser(EditProfileModel editProfileModel);
    void fillWhatsappNumberOnPopup(EditProfileModel editProfileModel);
    void validateFieldNIK(EditProfileModel editProfileModel);
    void changePhotoProfileUser(EditProfileModel editProfileModel);
    void verifyValidUploadImageUser();
    void verifyInvalidUploadImageUser();
    void cancelEditPrimaryDataUser(EditProfileModel editProfileModel);
    void createPinUser(CreateUserResponse createUserResponse);
    void updatePinUser(EditProfileModel editProfileModel);
}