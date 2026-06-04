package automation.pages.website.homepage.kariermu.b2b;

import automation.pages.base.BaseModel;

public class HomepageB2BModel extends BaseModel {

    public String fullName;
    public String email;
    public String whatsappNumber;
    public String institution;
    public String role;
    public String interestedProgram;
    public String logDepend = "";
    public boolean isValid;
    public SectionType sectionType;

    public static HomepageB2BModel newInstance() {
        return new HomepageB2BModel();
    }

    public HomepageB2BModel validData() {
        fullName = "automation test";
        email = System.getenv().getOrDefault("TEST_USER_EMAIL", "test.user@example.com");
        whatsappNumber = System.getenv().getOrDefault("TEST_WHATSAPP_NUMBER", "08100000000");
        institution = "PT. Test Company Indonesia";
        role = "QA Engineer";
        interestedProgram = "Technology";
        isValid = true;
        return this;
    }

    public HomepageB2BModel emptyData() {
        fullName = "";
        email = "";
        whatsappNumber = "";
        institution = "";
        role = "";
        interestedProgram = "";
        return this;
    }

    public HomepageB2BModel invalidData() {
        fullName = "@@";
        email = "indra@";
        whatsappNumber = "08";
        institution = "PT";
        role = "QA";
        interestedProgram = "Te";
        logDepend = "</br>depend - KMWA-12094: [Homepage] - input invalid field 'Email' on form leads kariermu" +
                "</br>depend - KMWA-12095: [Homepage] - input invalid field 'Nomor Whatsapp' on form leads kariermu" +
                "</br>depend - KMWA-12096: [Homepage] - input invalid field 'Nama Perusahaan' on form leads kariermu" +
                "</br>depend - KMWA-12097: [Homepage] - input invalid field 'Jabatan' on form leads kariermu" +
                "</br>depend - KMWA-12098: [Homepage] - input invalid field 'Anda tertarik dengan program apa?' on form leads kariermu";
        return this;
    }
}