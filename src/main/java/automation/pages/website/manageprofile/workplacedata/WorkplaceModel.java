package automation.pages.website.manageprofile.workplacedata;

import automation.pages.base.BaseModel;

public class WorkplaceModel extends BaseModel {

    public String industry = "Teknologi";
    public String nutpk = "ENG3334";
    public String username;
    public String email;
    public String agency;
    public boolean isEmptyValue = false;
    public boolean isInvalidData = false;
    public boolean getNuptk;

    public static WorkplaceModel newInstance() {
        return new WorkplaceModel();
    }

    public WorkplaceModel validDataAgencyProfessional() {
        agency = "Kariermu Prakerja";
        return this;
    }

    public WorkplaceModel validDataAgencyTeacher() {
        agency = "Kariermu Prakerja";
        return this;
    }

    public WorkplaceModel validDataAgencyCollege() {
        agency = "Kariermu Prakerja";
        return this;
    }

    public WorkplaceModel invalidEmptyWorkplaceData() {
        isEmptyValue = true;
        return this;
    }

    public WorkplaceModel invalidDataUser() {
        isInvalidData = true;
        agency = "!@#$%^&*(";
        return this;
    }

    public WorkplaceModel getNuptk() {
        getNuptk = true;
        return this;
    }
}