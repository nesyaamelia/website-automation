package automation.pages.website.achievementprofile;

import automation.pages.base.BaseSteps;
import automation.pages.base.EmptyModel;

public interface AchievementProfileSteps extends BaseSteps<AchievementProfilePage, EmptyModel> {

    void selectPortfolio();
    void selectAchievementsOnTabOverview();
    void seeAllAchievements();
    void selectAssessmentOnTabOverview();
    void seeAllAssessments();
    void selectProgramsOnTabOverview();
    void seeAllPrograms();
}
