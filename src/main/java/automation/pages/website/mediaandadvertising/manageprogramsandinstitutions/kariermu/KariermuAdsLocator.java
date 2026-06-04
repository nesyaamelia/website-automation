package automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions.kariermu;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class KariermuAdsLocator extends BaseLocator {

    public final By selectPlatform = getElementByXpath("//a[@href='/admin/kelola-program-lembaga/kariermu']");
    public final By selectPage = getElementByXpath("(//*[@class='multiselect__tags'])[1]");
    public final By inputPage = getElementById("multiselect-ads-page-list");
    public final By selectSection = getElementByXpath("(//*[@class='multiselect__tags'])[2]");
    public final By buttonRemoveAll = getElementById("btn-remove-all");
    public final By selectSearchProgram = getElementByXpath("(//div[@class='multiselect__tags'])[3]");
    public final By addProgram = getElementById("add-button");
    public final By popupRemoveAll = getElementById("swal2-title");
    public final By buttonDeleteOnPopup = getElementByXpath("//button[@class='swal2-confirm swal-activity-confirm-button swal2-styled']");
    public final By buttonCancelOnPopup = getElementByXpath("//button[@class='swal2-cancel swal-activity-cancel-button swal2-styled']");
    public final By buttonSave = getElementById("submit-button");
    public final By buttonDeleteOnList = getElementById("delete-item-0");
    public final By popupAds = getElementByXpath("//*[contains(text(), \"Iklankan Program?\")]");
    public final By buttonAdsOnPopup = getElementByXpath("//button[@class='swal2-confirm swal-activity-confirm-button swal2-styled' and text() = 'Iklankan']");
    public final By buttonCancelAdsOnPopup = getElementByXpath("//button[@class='swal2-cancel swal-activity-cancel-button swal2-styled' and text() = 'Batalkan']");
    public final By buttonNonAds = getElementById("ads-deactivator-1");
    public final By popupNonAds = getElementByXpath("//*[contains(text(), \"Turunkan Iklan Program?\")]");
    public final By buttonNonAdsOnPopup = getElementByXpath("//button[@class='swal2-confirm swal-activity-confirm-button swal2-styled' and text() = 'Turunkan']");
    public final By tickerInfo = getElementByXpath("//p[@class='main-font-color m-0 font-paragraph-5']");
    public final By popupSaveChanges = getElementByXpath("//*[contains(text(), \"Simpan Perubahan?\")]");
    public final By buttonSaveOnPopup = getElementByXpath("//button[@class='swal2-confirm swal-activity-confirm-button swal2-styled']");
    public final By alertSnackbar = getElementByXpath("//div[@class='snackbar-atom-text-wrapper pr-40']");
    public final By popupRemoveProgram = getElementByXpath("//*[contains(text(), 'Hapus Program?')] | //*[contains(text(), 'Hapus Lembaga?')]");
    public final By tagAds = getElementByXpath("//span[@class='font-label-4 ad-pill']");
    public final By emptyState = getElementByXpath("//p[@class='empty-title font-body-1-new']");
    public final By dropdownInput = getElementByXpath("//div[contains(@class,'multiselect--active')]//input");
    public final By totalRow = getElementByXpath("//tbody/tr");
    public final By waitTable = getElementByCssSelector(".table-responsive");

    public static KariermuAdsLocator newInstance() {
        return new KariermuAdsLocator();
    }

    public By listProgramName(int i) {
        return getElementByXpath("//table/tbody/tr[" + i + "]/td[contains(@class,'td--name')]//span[1]");
    }

    public By inputSection(String kariermuAdsModel) {
        return getElementByXpath("//*[contains(text(), \"" + kariermuAdsModel + "\")]");
    }

    public By listProgramDelete(int i) {
        return getElementByXpath("//table/tbody/tr[" + i + "]/td[contains(@class,'td--action')]//button[contains(@id,'delete-item')]");
    }

    public By buttonAds(int i) {
        return getElementByXpath("//*[@id=\"ads-activator-" + i + "\"]");
    }

    public By listProgramOnTable(int i) {
        return getElementByXpath("//*[@id=\"route-to-edit-program-" + i + "\"]");
    }
}
