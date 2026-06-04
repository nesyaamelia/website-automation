package automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.kariermu;

import automation.config.GlobalVariable;
import automation.config.WebsiteConfiguration;
import automation.pages.base.BaseModel;
import automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.PageType;
import automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.SectionType;

public class KariermuAdsModel extends BaseModel {

    public String logDepend = "default not depend";
    public String platform;
    public String page;
    public String section;
    public String programNames;
    public PageType pageType;
    public SectionType sectionType;
    public String programName;

    private final WebsiteConfiguration.Program program = GlobalVariable.getInstance().websiteConfiguration.program;;


    public static KariermuAdsModel newInstance() {
        return new KariermuAdsModel();
    }

    public KariermuAdsModel selectPage(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_UNGGULAN;
        return this;
    }

    public KariermuAdsModel addProgramUnggulan(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_UNGGULAN;
        programName = program.kariermuAdsProgramUnggulan;
        return this;
    }

    public KariermuAdsModel deleteProgram(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_UNGGULAN;
        logDepend = "</br> depend - ADKR-1464: Ensure button Hapus on table Program Unggulan able to click" +
                    "</br> depend - ADKR-1468: Ensure button Hapus on Popup Hapus Program Unggulan able to click" +
                    "</br> depend - ADKR-922: Prakerja Kariermu - button Hapus able to click" +
                    "</br> depend - ADKR-923: Prakerja Kariermu - button Hapus on Popup Hapus Program able to click";
        return this;
    }

    public KariermuAdsModel deleteAllandAddProgramUnggulan(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_UNGGULAN;
        programName = program.kariermuAdsProgramUnggulan;
        logDepend = "</br> depend - ADKR-1489: Showing button Hapus Semua Program" +
                "</br> depend - ADKR-1490: Button Hapus Semua Program able to click" +
                "</br> depend - ADKR-1493: button Hapus on popup hapus semua program able to click" +
                "</br> depend - ADKR-1495: Success delete all program on section Program Unggulan" +
                "</br> depend - ADKR-1497: Success add program unggulan after delete all program" +
                "</br> depend - ADKR-1499: Success simpan perubahan after add program unggulan on list table";
        return this;
    }

    public KariermuAdsModel cancelRemoveProgram(){
        pageType = PageType.HOMEPAGE;
        sectionType = SectionType.PROGRAM_UNGGULAN;
        logDepend = "</br> depend - ADKR-1494: button Batalkan on popup hapus semua program able to click" +
                    "</br> depend - ADKR-1466: button Batalkan on popup hapus program unggulan able to click" +
                    "</br> depend - ADKR-928: Prakerja Kariermu - button Batalkan on Popup Hapus Program able to click" +
                    "</br> depend - ADKR-1571: Prakerja Kariermu - button Batalkan on popup hapus semua program able to click";
        return this;
    }
}
