package automation.pages.website.paymentmethodprakerja;

import automation.pages.base.BaseLocator;
import org.openqa.selenium.By;

public class PaymentPrakerjaLocator extends BaseLocator {

    public final By getTextProgramName = getElementByXpath("//*[@class=\"main-card-right\"]/h4");
    public final By cardGroup = getElementByXpath("//*[@class=\"card-group-information font-paragraph-5\"]");
    public final By paymentInformationPrakerja = getElementByXpath("//*[@class=\"prakerja-payment-information font-label-md-rg-lh16 payment-information-wrapper information-desktop\"]");
    public final By getSizePaymentInstruction = getElementByXpath("//*[@class=\"prakerja-payment-information font-label-md-rg-lh16 payment-information-wrapper information-desktop\"]/*");
    public final By inputTextRedeemCode = getElementById("s-input-text-field");
    public final By inputTextPrakerjaId = getElementById("s-input-text-field");
    public final By buttonContinuePayment = getElementById("button-checkout-prakerja");
    public final By textFaqPrakerja = getElementById("faq-prakerja");
    public final By iconSearch = getElementByXpath("//*[@type=\"submit\"]/i");
    public final By buttonDetailCard = getElementById("move-purchase-detail-btn");
    public final By groupName = getElementByXpath("(//*[@class=\"mt-2 highlight highlight--royal-navy highlight__block font-label-sm-rg\"])[1]");
    public final By creatorCardVoucher = getElementByXpath("(//*[@class=\"inter-medium_small-normal primary-font-color\"])[3]");
    public final By closeButtonPopupRedeem = getElementById("close-button");
    public final By buttonSubmitRedeem = getElementById("modal-input-redeem-code-submit");
    public final By iconClearRedeemCode = getElementById("s-input-clear-button");
    public final By alertErrorRedeemCode = getElementByXpath("//*[contains(@class, \"error-input-redeem-code\")]");
    public final By snackbarSuccess = getElementByXpath("//*[@class=\"snackbar-atom-wrapper snackbar-atom-wrapper--success snackbar-atom-wrapper--5s\"]");
    public final By buttonInvoice = getElementById("btn-receipt-detail");
    public final By failedBookingQuota = getElementByXpath("//*[contains(text(), \"Gagal melakukan Booking Quota\")]");
    public final By iconCloseModal = getElementById("modal-close-button");
    public final By buttonCloseModalBox = getElementById("modal-box-close-button");
    public final By buttonBackToProgram = getElementByXpath("//button[contains(text(), \"Kembali Ke Program\")]");
    public final By errorText = getElementByXpath("//label[contains(@class, \"is-error\")]");
    public final By buttonGoToFaceRecognition = getElementById("redirect-url-btn");
    public final By buttonRedirectAfterFaceRecognition = getElementById("homepage-button");
    public final By alertSuccessEnroll = getElementByXpath("//*[contains(text(), \"Selamat! Program berhasil didapatkan.\")]");
    public final By buttonSelectSchedule = getElementById("pilih-jadwal-program-web-btn");

    public static PaymentPrakerjaLocator newInstance() {
        return new PaymentPrakerjaLocator();
    }

    public By getTextPaymentInstruction(int i) {
        return getElementByXpath("//*[@class=\"prakerja-payment-information font-label-md-rg-lh16 payment-information-wrapper information-desktop\"]/*[" + i + "]");
    }

    public By inputOtp(int i) {
        return getElementById("pin-input-" + (i - 1));
    }
}