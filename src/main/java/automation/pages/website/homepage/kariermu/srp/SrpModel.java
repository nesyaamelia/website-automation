package automation.pages.website.homepage.kariermu.srp;

import automation.config.GlobalVariable;
import automation.config.WebsiteConfiguration;
import automation.pages.base.BaseModel;

public class SrpModel extends BaseModel {

    public final WebsiteConfiguration websiteConfiguration;
    public final String[] listFilter = {"Prakerja Featured", "Journey Automation", "Kariermu Prakerja", "Kota Jakarta Selatan", "Kab. Bekasi", "Kab. Pekalongan"};
    public String searchKeywordProgram;
    public String priceFrom;
    public String priceTo;
    public String filterPrice;
    public String keywordPopupInstitution;
    public int selectFilterInstitution;
    public boolean isSelectProgramRekomendasi;
    public boolean isSelectOtherProgram;
    public boolean searchInvalid;
    public boolean showAll;
    public boolean selectInstitutionFromPopup;
    public boolean closeModal;
    public boolean searchInstitutionModal;
    public boolean isStopSelectProgramRekomendasi = false;
    public boolean resetFilter = false;
    public boolean isSelectFilter = false;

    public static SrpModel newInstance() {
        return new SrpModel();
    }

    SrpModel() {
        websiteConfiguration = GlobalVariable.getInstance().websiteConfiguration;
    }

    public String keywordPopupLocation = "kota";
    public String keywordPopupInvalid = "xyz";
    public String keywordPopupIndustry = "agama";

    public SrpModel multipleSelectFilterInstitution() {
        selectFilterInstitution = 2;
        return this;
    }

    public SrpModel searchInvalidKeyword() {
        searchKeywordProgram = "xyz";
        searchInvalid = true;
        return this;
    }

    public SrpModel selectProgramRekomendasi() {
        searchKeywordProgram = "xyz";
        searchInvalid = true;
        isSelectProgramRekomendasi = true;
        isStopSelectProgramRekomendasi = true;
        return this;
    }

    public SrpModel selectOtherProgram() {
        searchKeywordProgram = "xyz";
        searchInvalid = true;
        isSelectOtherProgram = true;
        return this;
    }

    public SrpModel priceLow() {
        priceFrom = "10000";
        filterPrice = "low price";
        return this;
    }

    public SrpModel priceHigh() {
        priceTo = "100000";
        filterPrice = "high price";
        return this;
    }

    public SrpModel priceLowerGreatherThanHiger() {
        priceFrom = "100000";
        priceTo = "1000";
        filterPrice = "";
        return this;
    }

    public SrpModel isShowAllInstitution() {
        showAll =  true;
        return this;
    }

    public SrpModel selectFilterInstitutionFromPopup() {
        showAll =  true;
        selectInstitutionFromPopup = true;
        return this;
    }

    public SrpModel closeModalPopupFilterInstitution() {
        showAll =  true;
        selectInstitutionFromPopup = true;
        closeModal = true;
        return this;
    }

    public SrpModel searchValidInstitutionFromModal() {
        showAll =  true;
        selectInstitutionFromPopup = true;
        searchInstitutionModal = true;
        keywordPopupInstitution = "sekolah";
        closeModal = true;
        return this;
    }

    public SrpModel searchinValidInstitutionFromModal() {
        showAll =  true;
        selectInstitutionFromPopup = true;
        searchInstitutionModal = true;
        keywordPopupInstitution = "!@#$%^&*()";
        closeModal = true;
        return this;
    }

    public SrpModel filterOffline() {
        searchKeywordProgram = "offline";
        return this;
    }

    public SrpModel filterWebinar() {
        searchKeywordProgram = "webinar";
        return this;
    }

    public SrpModel filterOfflineAndWebinar() {
        searchKeywordProgram = "offline and webinar";
        return this;
    }

    public SrpModel selectMinimumEducation() {
        isSelectFilter = true;
        resetFilter = false;
        closeModal = false;
        return this;
    }

    public SrpModel closeModalMinimumEducation(){
        isSelectFilter = false;
        resetFilter = false;
        closeModal = true;
        return this;
    }

    public SrpModel resetMinimumEducation(){
        isSelectFilter = false;
        resetFilter = true;
        closeModal = false;
        return this;
    }

    public SrpModel selectMinimumAge(){
        isSelectFilter = true;
        resetFilter = false;
        closeModal = false;
        return this;
    }

    public SrpModel resetMinimumAge(){
        isSelectFilter = false;
        resetFilter = true;
        closeModal = false;
        return this;
    }

    public SrpModel closeModalMinimumAge(){
        isSelectFilter = false;
        resetFilter = false;
        closeModal = true;
        return this;
    }
}