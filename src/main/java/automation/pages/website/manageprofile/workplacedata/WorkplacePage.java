package automation.pages.website.manageprofile.workplacedata;

import automation.pages.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static automation.pages.website.manageprofile.workplacedata.WorkplaceLocator.*;

public class WorkplacePage extends BasePage<WorkplaceModel, WorkplaceLocator> implements WorkplaceSteps {

    public static WorkplacePage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new WorkplacePage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = WorkplaceModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = WorkplaceLocator.newInstance();
    }

    @Override
    public WorkplacePage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void changeWorkplaceDataProfessional(WorkplaceModel workplaceModel) {
        goToEditWorkplace();
        if (!workplaceModel.isEmptyValue) {
            getElement().waitUntilClick(mLocator.inputInstitution);
            getElement().waitUntilSetText(mLocator.inputInstitution, workplaceModel.agency);
            if (workplaceModel.isInvalidData) {
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.dataNotFound));
                boolean isShowEmptyData = getElement().verifyElementPresent(mLocator.dataNotFound);
                logAndAssertTrue(isShowEmptyData, "show empty data user" +
                        "</br>depend - KMWA-11749: Profile - Empty state appear when user search tempat kerja but data not found");
                return;
            }
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.containsTextWithIndex(workplaceModel.agency, 1)));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.containsTextWithIndex(workplaceModel.agency, 1)));
        }
        getElement().waitUntilClick(mLocator.buttonSubmit);
    }

    @Override
    public void changeWorkplaceDataTeacher(WorkplaceModel workplaceModel) {
        goToEditWorkplace();
        if (!workplaceModel.isEmptyValue) {
            getElement().waitUntilClick(mLocator.inputInstitution);
            getElement().waitUntilSetText(mLocator.inputInstitution, workplaceModel.agency);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.containsTextWithIndex(workplaceModel.agency, 1)));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.containsTextWithIndex(workplaceModel.agency, 1)));
            getElement().waitUntilSetText(mLocator.inputNuptk, mModel.nutpk);
        }
        getElement().waitUntilClick(mLocator.buttonSubmit);
    }

    @Override
    public void changeWorkplaceDataCollege(WorkplaceModel workplaceModel) {
        goToEditWorkplace();
        if (workplaceModel.getNuptk) {
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.inputNuptk));
            String getValueNuptk = getElement().getAttributeFrom(mLocator.inputNuptk, "value");
            logAndAssertEqual("16111993", getValueNuptk, "show default nuptk");
            return;
        }
        if (!workplaceModel.isEmptyValue) {
            getElement().waitUntilClick(mLocator.inputInstitution);
            getElement().waitUntilSetText(mLocator.inputInstitution, workplaceModel.agency);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.containsTextWithIndex(workplaceModel.agency, 1)));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.containsTextWithIndex(workplaceModel.agency, 1)));
            getElement().click(mLocator.inputSubjectIndustry);
            getElement().waitUntilSetText(mLocator.inputSubjectIndustry, mModel.industry);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.containsText(textSubjectIndustry)));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.containsText(textSubjectIndustry)));
            getElement().click(mLocator.inputWrapper);
            getElement().waitUntilClick(mLocator.dropdownNutpk);
            getElement().setText(mLocator.inputNutpk, mModel.nutpk);
        }
        getElement().waitUntilClick(mLocator.buttonSubmit);
    }

    @Override
    public void verifySuccessEditWorkPlace() {
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.snackbarSuccess));
        boolean isShowSnackbarSuccess = getElement().verifyElementPresent(mLocator.snackbarSuccess);
        logAndAssertTrue(isShowSnackbarSuccess, "edit workplace user");
    }

    private void goToEditWorkplace() {
        if (getElement().verifyElementPresent(mLocator.buttonCloseModal)) {
            getElement().click(mLocator.buttonCloseModal);
            getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonCloseModal));
        } else {
            logInfo("Popup whatsapp not appears");
        }
        getElement().waitUntilClick(mLocator.buttonInstitution);
        getElement().waitUntilClick(mLocator.buttonEditInstitution);
    }
}