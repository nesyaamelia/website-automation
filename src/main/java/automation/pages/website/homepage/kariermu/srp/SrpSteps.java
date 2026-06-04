package automation.pages.website.homepage.kariermu.srp;

import automation.pages.base.BaseSteps;
import automation.pages.website.homepage.kariermu.SortingSearchType;
import org.openqa.selenium.By;

public interface SrpSteps extends BaseSteps<SrpPage, SrpModel> {

    void sortingSearch(SortingSearchType sortingSearchType);
    void filterByInstitution(SrpModel srpModel);
    void filterByTypeActivity(SrpModel srpModel);
    void filterInvalidKeyword(SrpModel srpModel);
    void filterPrice(SrpModel srpModel);
    void filterOfflineLocation();
    void filterIndustry();
    void deleteFilter();
    void resetItemSelected();
    void filterByMinimumEducation(SrpModel srpModel);
    void filterByMinimumAndMaximumAge(SrpModel srpModel);
}