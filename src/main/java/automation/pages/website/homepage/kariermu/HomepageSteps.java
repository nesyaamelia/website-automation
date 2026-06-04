package automation.pages.website.homepage.kariermu;

import automation.pages.base.BaseSteps;

public interface HomepageSteps extends BaseSteps<HomepagePage, HomepageModel> {

    void PilihanTimKariermuSection();
    void ProgramList();
    void searchProgram(HomepageModel homepageModel);
    void programRecommendation();
    void entryPointKariermuJobs();
    void validateFooter(String footer);
    void tickerAccess(HomepageModel homepageModel);
    void popupModalWhatsapp(HomepageModel homepageModel);
}