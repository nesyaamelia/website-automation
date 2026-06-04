package automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.prakerja;

import automation.pages.base.BaseSteps;

public interface PrakerjaAdsSteps extends BaseSteps<PrakerjaAdsPage, PrakerjaAdsModel> {

    void selectSection(PrakerjaAdsModel prakerjaAdsModel);

    void addProgram(PrakerjaAdsModel prakerjaAdsModel);

    void removeProgram(PrakerjaAdsModel prakerjaAdsModel);

    void adsProgram();

    void removeAllProgramAndAddProgram(PrakerjaAdsModel prakerjaAdsModel);

    void cancelRemoveProgram(PrakerjaAdsModel prakerjaAdsModel);
}
