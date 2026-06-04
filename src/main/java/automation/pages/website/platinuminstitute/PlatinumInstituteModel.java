package automation.pages.website.platinuminstitute;

import automation.pages.base.BaseModel;

public class PlatinumInstituteModel extends BaseModel {

    public String keywordPopupInvalid = "xyz";
    public String keywordPopupValid = "matematika";

    public static PlatinumInstituteModel newInstance(){
        return new PlatinumInstituteModel();
    }
}