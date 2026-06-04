package automation.pages.website.homepage.prakerja;

import automation.pages.base.BaseModel;

public class HomepageModel extends BaseModel {

    public boolean isResetUrlCategory;
    public boolean isRefreshPage;
    public String invalidUrl;
    public String keywordInstitution;
    public String validUrl = "kategori-program";
    public String searchKeyword = "program";
    public String linkPlayStore = "https://play.google.com/store/apps/details?id=mu.karier.android&hl=in";
    public String logDepend;

    public static HomepageModel newInstance() {
        return new HomepageModel();
    }

    public HomepageModel resetUrl() {
        isResetUrlCategory = true;
        invalidUrl = "=Saleus & Markting";
        return this;
    }

    public HomepageModel refreshPage() {
        isRefreshPage = true;
        return this;
    }

    public String nearestSchedule(){
        return "</br>depend - KMWA-9969: Jadwal Terdekat - Showing section Program dengan Jadwal terdekat in the Homepage Prakerja Karier.mu when there is programs with nearest schedule" +
                "</br>depend - KMWA-9974: Jadwal Terdekat - Program shown in section jadwal Terdekat should have PMO Code" +
                "</br>depend - KMWA-9975: Jadwal Terdekat - Program shown in section jadwal Terdekat should have minimal 1 study group and the schedule is nearest schedule" +
                "</br>depend - KMWA-9977: Jadwal Terdekat - Each card program in section Program dengan Jadwal terdekat is able to click" +
                "</br>depend - KMWA-9986: Jadwal Terdekat - Redirect to pop up Konfirmasi Kehadiran with the right schedule if quota is available when user click program card in section Program dengan Jadwal Terdekat" +
                "</br>depend - KMWA-10025: Jadwal Terdekat - Showing section Program dengan Jadwal terdekat with tab Webinar and Tatap Muka Offline in the Homepage Prakerja Karier.mu when there is programs with nearest schedule" +
                "</br>depend - KMWA-10029: Jadwal Terdekat - Success showing program with jadwal terdekat Webinar in section Jadwal Terdekat when user click button Webinar";
    }

    public HomepageModel searchIntitution(){
        keywordInstitution = "Journey Automation";
        return this;
    }
}