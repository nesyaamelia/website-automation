package automation.pages.website.homepage.prakerja;

import automation.pages.base.BaseSteps;

public interface HomepageStep extends BaseSteps<HomepagePage, HomepageModel> {

    void sectionContainer(SectionType programType);
    void staticBanner();
    void chevronProgramRecommendation();
    void selectProgram(SectionType programType, boolean isCancelSelectOffline);
    void viewAllProgram(SectionType programType);
    void programCategory();
    void selectProgramCategory(ProgramCategoryType programCategoryType);
    void popularSearch(PopularSearchType popularSearchType);
    void pageProgramCategory(HomepageModel homepageModel);
    void bannerSection(BannerType bannerType);
    void validateFooterPrakerja();
    void collaborationPartnerSection(MitraType mitraType);
    void validateDownloadAplikasiSection();
    void tickerAccess();
    void seeMoreInstitution();
    void searchFavoriteInstitution(HomepageModel homepageModel);
    void exploreOtherTraining();
}