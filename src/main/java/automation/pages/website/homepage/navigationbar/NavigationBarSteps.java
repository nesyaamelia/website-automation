package automation.pages.website.homepage.navigationbar;

import automation.pages.base.BaseSteps;
import automation.pages.base.EmptyModel;

public interface NavigationBarSteps extends BaseSteps<NavigationBarPage, EmptyModel> {

    void navigationTo(NavigationType navigationType);
}