package automation.pages.website.homepage.popupwhatsapp.api.request;

import automation.config.GlobalVariable;
import automation.data.User;
import automation.network.BaseRequest;

public class CreateUserRequest extends BaseRequest<CreateUserRequest> {

    public String name;
    public String avatar;
    public String password;
    public String email;
    public String phone;
    public Integer yearLevelId;
    public String facilProgramType;
    public String partnershipType;
    public Boolean isEmailVerified;
    public Boolean status;
    public Integer formType;
    public String username;
    public String source;

    @Override
    public CreateUserRequest getBody() {
        return this;
    }

    public CreateUserRequest() {
        User userModel = new User();
        GlobalVariable globalVariable = GlobalVariable.getInstance();
        this.name = userModel.name;
        this.avatar = "https://cdn.example.com/upload/test-avatar.jpeg";
        this.password = System.getenv().getOrDefault("TEST_USER_PASSWORD", "Password1");
        this.email = userModel.email.toLowerCase();
        this.phone = userModel.cellPhone;
        this.yearLevelId = globalVariable.yearLevel;
        this.facilProgramType = "None";
        this.partnershipType = "Sekolah";
        this.isEmailVerified = true;
        this.status = true;
        this.formType = 1;
        this.source = System.getenv().getOrDefault("TEST_SOURCE_TOKEN", "");
    }
}