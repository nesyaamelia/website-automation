package automation.pages.website.homepage.kariermu.b2b;

import automation.pages.base.BaseSteps;

public interface HomepageB2BSteps extends BaseSteps<HomepageB2BPage, HomepageB2BModel> {

    void handleRedirectButton(SectionType sectionType);
    void fillFormLeads(HomepageB2BModel homepageB2BModel);
    void fillFormSubscription(HomepageB2BModel homepageB2BModel);
}