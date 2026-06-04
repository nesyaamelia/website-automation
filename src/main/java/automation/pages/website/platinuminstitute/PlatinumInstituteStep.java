package automation.pages.website.platinuminstitute;

import automation.pages.base.BaseSteps;

public interface PlatinumInstituteStep extends BaseSteps<PlatinumInstitutePage, PlatinumInstituteModel> {

    void searchResultPageLembagaPlatinum();
    void filterOfflineLocationInstitution();
    void filterPendidikanMinimalSd();
    void filterPendidikanMinimalSma();
    void filterPendidikanMinimalD4();
    void filterBidangStudi();
    void filterBidangStudiFromPopup();
    void searchBidangStudiInvalid();
    void searchBidangStudiValid();
    void filterUsiaMinimal();
    void filterUsiaMaksimal();
    void filterUsiaMinimalMaksimal();
    void closeModal();
    void buttonReset();
    void filterTipeProgramPilihan();
    void filterTipeProgramPrakerja();
    void selectLembagaPopup();
    void showAllButtonBidangIndustri();
    void closeButtonPills();
    void buttonResetUsiaMinimal();
    void buttonResetUsiaMaksimal();
    void closeModalUsiaMinimal();
    void closeModalUsiaMaksimal();
}