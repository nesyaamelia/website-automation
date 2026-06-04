package automation.pages.website.homepage.popupwhatsapp.api;

import automation.config.DatabasesConfiguration;
import automation.config.WebsiteConfiguration;
import automation.pages.dashboard.manageassessment.activity.mass.remote.database.MassAssessmentDbHelper;
import automation.pages.website.homepage.popupwhatsapp.PopupWhatsappModel;
import automation.pages.website.homepage.popupwhatsapp.api.request.CreateUserRequest;
import automation.pages.website.homepage.popupwhatsapp.api.request.EnrollUserRequest;
import automation.pages.website.homepage.popupwhatsapp.api.request.LoginDashboardRequestModel;
import automation.pages.website.homepage.popupwhatsapp.api.request.VerifyOtpAdminRequest;
import automation.pages.website.homepage.popupwhatsapp.api.response.CreateUserResponse;
import sekolahmu.datasource.api.SekolahmuApi;
import sekolahmu.datasource.api.base.SekolahmuResponse;

import java.util.concurrent.atomic.AtomicReference;

public class PopupWhatsappApi implements PopupWhatsappSteps {

    private final String LOGIN = "/v2/auth/login";
    private final String VERIFY_OTP = "/v2/auth/verify-otp";
    private final String MANAGE_USER = "/user/manage/?platform=kariermu";
    private final String PROGRAM_ACTIVITY = "/program_activity/v2/enroll/?platform=kariermu";

    @Override
    public CreateUserResponse createUser() {
        CreateUserResponse createUserResponse = createNewUser();
        String message = createUserResponse.message;
        if (message.equalsIgnoreCase("Terjadi kesalahan pada permintaan anda!") || createUserResponse.message.equalsIgnoreCase("Username telah terdaftar")) {
            return createNewUser();
        } else {
            return createUserResponse;
        }
    }

    @Override
    public int enrollProgramUser(PopupWhatsappModel popupWhatsappModel) {
        AtomicReference<Integer> status = new AtomicReference<>();
        EnrollUserRequest enrollUserRequest = new EnrollUserRequest(popupWhatsappModel);
        SekolahmuApi.post(SekolahmuResponse.class, loginAdmin(), PROGRAM_ACTIVITY, enrollUserRequest.toJsonString(), sekolahmuResponse -> {
            status.set(sekolahmuResponse.status);
        });
        return status.get();
    }

    private String loginAdmin() {
        AtomicReference<String> token = new AtomicReference<>();
        LoginDashboardRequestModel loginDashboardRequestModel = new LoginDashboardRequestModel();
        SekolahmuApi.post(SekolahmuResponse.class, DatabasesConfiguration.getInstance().keys.generalToken, LOGIN, loginDashboardRequestModel.toJsonString(), sekolahmuResponse -> {
            if (sekolahmuResponse.message.contains("Silakan verifikasi akunmu terlebih dahulu")) {
                String otp = MassAssessmentDbHelper.getCodeFromEmailOtp(WebsiteConfiguration.getInstance().user.userToken);
                int otpPurposeId = MassAssessmentDbHelper.otpPurposeId(WebsiteConfiguration.getInstance().user.userToken);
                otpAdmin(otp, otpPurposeId);
            } else {
                token.set(sekolahmuResponse.token.token);
            }
        });
        return token.get();
    }

    private void otpAdmin(String otp, int otpPurposeId) {
        AtomicReference<String> token = new AtomicReference<>();
        VerifyOtpAdminRequest otpAdminRequestModel = new VerifyOtpAdminRequest(otp, otpPurposeId);
        SekolahmuApi.post(SekolahmuResponse.class, DatabasesConfiguration.getInstance().keys.generalToken, VERIFY_OTP, otpAdminRequestModel.toJsonString(), sekolahmuResponse -> {
            token.set(sekolahmuResponse.token.token);
        });
        token.get();
    }

    private CreateUserResponse createNewUser() {
        AtomicReference<Object> createUserResponse = new AtomicReference<>();
        CreateUserRequest createUser = new CreateUserRequest();
        try {
            SekolahmuApi.post(CreateUserResponse.class, loginAdmin(), MANAGE_USER, createUser.toJsonString(), createUserResponse::set);
        } catch (IllegalArgumentException i) {
            SekolahmuApi.post(CreateUserResponse.class, loginAdmin(), MANAGE_USER, createUser.toJsonString(), createUserResponse::set);
        }
        return (CreateUserResponse) createUserResponse.get();
    }
}