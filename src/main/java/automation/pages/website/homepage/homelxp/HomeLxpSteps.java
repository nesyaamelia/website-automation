package automation.pages.website.homepage.homelxp;

import automation.pages.base.BaseSteps;
import automation.pages.base.EmptyModel;

public interface HomeLxpSteps extends BaseSteps<HomeLxpPage, EmptyModel> {

    void seeAllLearningInProgress();
    void openActivityScheduleEvent();
    void addToGoogleCalenderScheduleEvent();
    void downloadCalender();
    void seeAllLearningAchievements();
    void selectAreasOfExpertise();
    void selectRemainCertificates();
    void seeAllPopularCourses();
}
