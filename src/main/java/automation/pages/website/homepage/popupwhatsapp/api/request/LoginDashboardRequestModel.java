package automation.pages.website.homepage.popupwhatsapp.api.request;

import automation.config.GlobalVariable;
import automation.network.BaseRequest;

public class LoginDashboardRequestModel extends BaseRequest<LoginDashboardRequestModel> {

    public String agent;
    public String deviceId;
    public String email;
    public String longitude;
    public String latitude;
    public String password;
    public String source;

    @Override
    public LoginDashboardRequestModel getBody() {
        return this;
    }

    public LoginDashboardRequestModel() {
        this.agent = "Chrome";
        this.deviceId = System.getenv().getOrDefault("TEST_DEVICE_ID", "");
        this.email = GlobalVariable.getInstance().websiteConfiguration.user.userToken;
        this.longitude = "";
        this.latitude = "";
        this.password = GlobalVariable.getInstance().websiteConfiguration.user.password;
        this.source = GlobalVariable.getInstance().websiteConfiguration.api.source;
    }
}