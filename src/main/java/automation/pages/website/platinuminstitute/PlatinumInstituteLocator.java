package automation.pages.website.platinuminstitute;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class PlatinumInstituteLocator extends BaseLocator {

    public final By showMore = getElementByXpath("(//*[@id=\"btn-show-more\"])[2]");
    public final By lokasiOffline = getElementByXpath("//span[text()='Kota Bekasi']");
    public final By pendidikanMinimalSd = getElementByXpath("//div[text()='SD']");
    public final By pendidikanMinimalSma = getElementByXpath("//div[text()='SMA/SMK']");
    public final By pendidikanMinimalD4 = getElementByXpath("//div[text()='D4/S1']");
    public final By bidangStudi = getElementByXpath("(//*[@class=\"desktop-side-filter\"]/section[7])/div[2]/div/div/label[1]/input");
    public final By buttonShowAllBidangStudi = getElementByXpath("(//*[@id=\"show-all-options-button\"])[2]");
    public final By titleBidangStudiModal = getElementByXpath("//*[@class=\"font-subtitle-sb-2 title\"]");
    public final By bidangStudi2 = getElementByXpath("(//*[@class=\"inter-medium_small-normal\"])[6]");
    public final By buttonApplyFilter = getElementById("button-apply-selection");
    public final By cardProgramBidangStudi = getElementByXpath("//*[@class=\"program-card-body\"]/div[1]");
    public final By searchBarPopupBidangStudi = getElementById("s-searchbar");
    public final By buttonSearchPopupBidangStudi = getElementByXpath("(//*[@alt=\"search-icon\"])[2]");
    public final By invalidKeywordText = getElementByClassName("empty-data");
    public final By resultSearchBidangStudi = getElementByXpath("//*[@class=\"inter-medium_small-normal\"]");
    public final By usiaMinimal = getElementById("desktop-select-min-age");
    public final By modalUsiaMinimal = getElementByXpath("//*[@class=\"font-subtitle-sb-2 title\"]");
    public final By selectUsiaMinimal = getElementByXpath("//div[text()='10 Tahun']");
    public final By usiaMaksimal = getElementById("desktop-select-max-age");
    public final By modalUsiaMaksimal = getElementByXpath("//*[@class=\"font-subtitle-sb-2 title\"]");
    public final By selectUsiaMaksimal = getElementByXpath("//div[text()='35 Tahun']");
    public final By closeModal = getElementById("button-close-modal");
    public final By selectBidangStudi = getElementByXpath("(//*[@class=\"inter-medium_small-normal\"])[2]");
    public final By selectBidangStudi2 = getElementByXpath("(//*[@class=\"inter-medium_small-normal\"])[3]");
    public final By buttonReset = getElementById("button-reset-selection");
    public final By programPilihan = getElementByXpath("//span[text()='Program Pilihan']");
    public final By programPrakerja = getElementByXpath("//span[text()='Program Prakerja']");
    public final By selectLembagaPopup= getElementByXpath("//div[@class='listing']//label/div[contains(text(),'Sekolah.mu')]");
    public final By buttonClosePills = getElementById("remove-item");
    public final By textShowAll = getElementByXpath("(//*[@id=\"show-all-options-button\"])[1]");
    public final By programCard = getElementByXpath("(//*[@id=\"program-card\"])[1]");
    public final By buttonApply = getElementById("button-apply-selection");
    public final By offlineLocation = getElementByXpath("//span[text()='Kab. Bekasi']");
    public final By chipsFilter = getElementByXpath("(//button[@id='s-tag'])[2]");

    public static PlatinumInstituteLocator newInstance(){
        return new PlatinumInstituteLocator();
    }
}