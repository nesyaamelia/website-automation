package automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.prakerja;

import automation.config.GlobalVariable;
import automation.config.WebsiteConfiguration;
import automation.pages.base.BaseModel;
import automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.PageType;
import automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.SectionType;

public class PrakerjaAdsModel extends BaseModel {

    public String logDepend = "default not depend";
    public String platform;
    public String page;
    public String section;
    public PageType pageType;
    public SectionType sectionType;
    public String programName;

    private final WebsiteConfiguration.Program program = GlobalVariable.getInstance().websiteConfiguration.program;


    public static PrakerjaAdsModel newInstance() {
        return new PrakerjaAdsModel();
    }

    public PrakerjaAdsModel selectPage(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_RECOMMENDATION;
        return this;
    }

    public PrakerjaAdsModel selectPageOffline(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_OFFLINE;
        return this;
    }

    public PrakerjaAdsModel selectPageWebinar(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_WEBINAR;
        return this;
    }

    public PrakerjaAdsModel selectPageSPL(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_SPL;
        return this;
    }

    public PrakerjaAdsModel selectPageLembagaFavorit(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.FAVORITE_INSTITUTION;
        return this;
    }

    public PrakerjaAdsModel selectPageSalesMarketing(){
        pageType = PageType.CATEGORY_PAGE_SALES_MARKETING;
        return this;
    }

    public PrakerjaAdsModel selectPageDataTeknologi(){
        pageType = PageType.CATEGORY_PAGE_DATA_TECHNOLOGY;
        return this;
    }

    public PrakerjaAdsModel selectPageBahasa(){
        pageType = PageType.CATEGORY_PAGE_LANGUAGE;
        return this;
    }

    public PrakerjaAdsModel selectPageBisnis(){
        pageType = PageType.CATEGORY_PAGE_BUSINESS;
        return this;
    }

    public PrakerjaAdsModel selectPageAdministrasiPerkantoran(){
        pageType = PageType.CATEGORY_PAGE_ADMINISTRATION_OFFICE;
        return this;
    }

    public PrakerjaAdsModel selectPagePersiapanKerja(){
        pageType = PageType.CATEGORY_PAGE_WORK_PREPARATION;
        return this;
    }

    public PrakerjaAdsModel selectPageKesenianFotografi(){
        pageType = PageType.CATEGORY_PAGE_ART_PHOTOGRAPHY;
        return this;
    }

    public PrakerjaAdsModel selectPageLainnya(){
        pageType = PageType.CATEGORY_PAGE_OTHER;
        return this;
    }

    public PrakerjaAdsModel addProgramRecommendation(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_RECOMMENDATION;
        programName = program.prakerjaAdsProgramRekomendasi;
        return this;
    }

    public PrakerjaAdsModel addProgramOffline(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_OFFLINE;
        programName = program.prakerjaAdsProgramOffline;
        return this;
    }

    public PrakerjaAdsModel addProgramWebinar(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_WEBINAR;
        programName = program.prakerjaAdsProgramWebinar;
        return this;
    }

    public PrakerjaAdsModel addProgramSPL(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_SPL;
        programName = program.prakerjaAdsProgramSPL;
        return this;
    }

    public PrakerjaAdsModel addLembagaFavorit(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.FAVORITE_INSTITUTION;
        programName = program.prakerjaAdsLembagaFavorit;
        return this;
    }

    public PrakerjaAdsModel addProgramSalesMarketing(){
        pageType = PageType.CATEGORY_PAGE_SALES_MARKETING;
        programName = program.prakerjaAdsProgramSalesMarketing;
        return this;
    }

    public PrakerjaAdsModel addProgramDataTeknologi(){
        pageType = PageType.CATEGORY_PAGE_DATA_TECHNOLOGY;
        programName = program.prakerjaAdsProgramDataTeknologi;
        return this;
    }

    public PrakerjaAdsModel addProgramBahasa(){
        pageType = PageType.CATEGORY_PAGE_LANGUAGE;
        programName = program.prakerjaAdsProgramBahasa;
        return this;
    }

    public PrakerjaAdsModel addProgramBisnis(){
        pageType = PageType.CATEGORY_PAGE_BUSINESS;
        programName = program.prakerjaAdsProgramBisnis;
        return this;
    }

    public PrakerjaAdsModel addProgramAdministrasiPerkantoran(){
        pageType = PageType.CATEGORY_PAGE_ADMINISTRATION_OFFICE;
        programName = program.prakerjaAdsProgramAdministrasiPerkantoran;
        return this;
    }

    public PrakerjaAdsModel addProgramPersiapanKerja(){
        pageType = PageType.CATEGORY_PAGE_WORK_PREPARATION;
        programName = program.prakerjaAdsProgramPersiapanKerja;
        return this;
    }

    public PrakerjaAdsModel addProgramKesenianFotografi(){
        pageType = PageType.CATEGORY_PAGE_ART_PHOTOGRAPHY;
        programName = program.prakerjaAdsProgramKesenianFotografi;
        return this;
    }

    public PrakerjaAdsModel addProgramLainnya(){
        pageType = PageType.CATEGORY_PAGE_OTHER;
        programName = program.prakerjaAdsProgramLainnya;
        return this;
    }

    public PrakerjaAdsModel deleteProgram(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_RECOMMENDATION;
        logDepend = "</br> depend - ADKR-1464: Ensure button Hapus on table Program Unggulan able to click" +
                    "</br> depend - ADKR-1468: Ensure button Hapus on Popup Hapus Program Unggulan able to click" +
                    "</br> depend - ADKR-922: Prakerja Kariermu - button Hapus able to click" +
                    "</br> depend - ADKR-923: Prakerja Kariermu - button Hapus on Popup Hapus Program able to click";
        return this;
    }

    public PrakerjaAdsModel deleteAllProgram(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_RECOMMENDATION;
        programName = program.prakerjaAdsProgramRekomendasi;
        logDepend = "</br> depend - ADKR-1489: Showing button Hapus Semua Program" +
                "</br> depend - ADKR-1490: Button Hapus Semua Program able to click" +
                "</br> depend - ADKR-1493: button Hapus on popup hapus semua program able to click" +
                "</br> depend - ADKR-1495: Success delete all program on section Program Unggulan" +
                "</br> depend - ADKR-1497: Success add program unggulan after delete all program" +
                "</br> depend - ADKR-1499: Success simpan perubahan after add program unggulan on list table";
        return this;
    }

    public PrakerjaAdsModel cancelRemoveProgram(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_RECOMMENDATION;
        logDepend = "</br> depend - ADKR-1494: button Batalkan on popup hapus semua program able to click" +
                    "</br> depend - ADKR-1466: button Batalkan on popup hapus program unggulan able to click" +
                    "</br> depend - ADKR-928: Prakerja Kariermu - button Batalkan on Popup Hapus Program able to click" +
                    "</br> depend - ADKR-1571: Prakerja Kariermu - button Batalkan on popup hapus semua program able to click";
        return this;
    }
}
