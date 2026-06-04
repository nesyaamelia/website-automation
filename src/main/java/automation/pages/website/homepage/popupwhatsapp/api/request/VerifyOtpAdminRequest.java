package automation.pages.website.homepage.popupwhatsapp.api.request;

import automation.network.BaseRequest;

public class VerifyOtpAdminRequest extends BaseRequest<VerifyOtpAdminRequest> {

    public String email;
    public String otp;
    public int otpPurposeId;
    public String action;
    public String deviceId;
    public String source;

    @Override
    public VerifyOtpAdminRequest getBody() {
        return this;
    }

    public VerifyOtpAdminRequest(String otp, int otpPurposeId) {
        this.email = System.getenv().getOrDefault("QA_ADMIN_EMAIL", "qa.admin@example.com");
        this.otp = otp;
        this.otpPurposeId = otpPurposeId;
        this.action = "admin";
        this.deviceId = System.getenv().getOrDefault("TEST_DEVICE_ID", "");
        this.source = System.getenv().getOrDefault("TEST_SOURCE_TOKEN", "");
    }
}
