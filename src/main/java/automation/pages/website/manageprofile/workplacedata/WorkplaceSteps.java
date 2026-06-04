package automation.pages.website.manageprofile.workplacedata;

import automation.pages.base.BaseSteps;

public interface WorkplaceSteps extends BaseSteps<WorkplacePage, WorkplaceModel> {

    void changeWorkplaceDataProfessional(WorkplaceModel workplaceModel);
    void changeWorkplaceDataTeacher(WorkplaceModel workplaceModel);
    void changeWorkplaceDataCollege(WorkplaceModel workplaceModel);
    void verifySuccessEditWorkPlace();
}