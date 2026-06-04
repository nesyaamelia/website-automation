package automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.kariermu;

import automation.pages.base.BaseSteps;

public interface KariermuAdsSteps extends BaseSteps<KariermuAdsPage, KariermuAdsModel> {

    void selectSection(KariermuAdsModel kariermuAdsModel);

    void addProgram(KariermuAdsModel kariermuAdsModel);

    void removeProgram(KariermuAdsModel kariermuAdsModel);

    void adsProgram();

    void nonAdsProgram();

    void removeAllProgramAndAddProgram(KariermuAdsModel kariermuAdsModel);

    void cancelRemoveProgram(KariermuAdsModel kariermuAdsModel);

    void cancelOnPopupAdsProgram();
}
