package automation.pages.website.homepage.kariermu;

import automation.config.GlobalVariable;
import automation.config.Host;
import automation.config.WebsiteConfiguration;
import automation.pages.base.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomepageModel extends BaseModel {

    private static final Logger log = LoggerFactory.getLogger(HomepageModel.class);
    public final WebsiteConfiguration websiteConfiguration;

    public String searchKeywordProgram;
    public String priceFrom;
    public String priceTo;
    public String filterPrice;
    public String whatsappNumber;
    public String logDepend;
    public String goToUrl;
    public boolean isSelectProgramRekomendasi;
    public boolean isSelectOtherProgram;
    public boolean searchInvalid;
    public boolean searchFromUrl;
    public boolean isCloseWhatsappNumber;
    public boolean isGetWhatsappNumber = true;
    public boolean isRefresh;
    public boolean isGoBack;
    public boolean isNotDependWhatsapp;

    public static HomepageModel newInstance() {
        return new HomepageModel();
    }

    HomepageModel() {
        websiteConfiguration = GlobalVariable.getInstance().websiteConfiguration;
    }

    public String searchKeyword = "Program Prakerja";
    public String urlKariermuJobs = "talent/blast";
    public String urlBlogPage = "https://www.karier.mu/blog/";
    public String urlBantuanPage = "https://www.karier.mu/faq/docs/prakerja/";

    public HomepageModel searchInvalidKeyword() {
        searchKeywordProgram = "xyz";
        searchInvalid = true;
        return this;
    }

    public HomepageModel selectProgramRekomendasi() {
        searchKeywordProgram = "xyz";
        searchInvalid = true;
        isSelectProgramRekomendasi = false;
        return this;
    }

    public HomepageModel selectOtherProgram() {
        searchKeywordProgram = "xyz";
        searchInvalid = true;
        isSelectOtherProgram = true;
        return this;
    }

    public HomepageModel priceLow() {
        priceFrom = "10000";
        filterPrice = "low price";
        return this;
    }

    public HomepageModel priceHigh() {
        priceTo = "100000";
        filterPrice = "high price";
        return this;
    }

    public HomepageModel priceLowerGreatherThanHiger() {
        priceFrom = "100000";
        priceTo = "1000";
        filterPrice = "";
        return this;
    }

    public HomepageModel searchUsingSlug() {
        searchFromUrl = true;
        return this;
    }

    public HomepageModel closePopupWhatsapp() {
        isCloseWhatsappNumber = true;
        return this;
    }

    public HomepageModel closePopupWhatsappWithNotDepend() {
        isCloseWhatsappNumber = true;
        isNotDependWhatsapp = true;
        return this;
    }

    public HomepageModel phoneNumberLessThan10() {
        isGetWhatsappNumber = false;
        whatsappNumber = "08139827";
        return this;
    }

    public HomepageModel refreshPopupWhatsapp() {
        isCloseWhatsappNumber = false;
        isRefresh = true;
        return this;
    }

    public HomepageModel phoneNumberMoreThan15() {
        isGetWhatsappNumber = false;
        whatsappNumber = "081398279872637"; // truncated for privacy
        return this;
    }

    public HomepageModel symbolPhoneNumber() {
        isGetWhatsappNumber = false;
        whatsappNumber = "!@#$%^&*()-=";
        return this;
    }

    public HomepageModel goBackAndValidate() {
        isGoBack = true;
        return this;
    }

    public HomepageModel popupWhatsappFromVoucher() {
        logDepend = "</br>depend - KMWA-11213: Voucher - Modal input nomor whatsapp appear when user success payment with redeem voucher" +
                "</br>depend - KMWA-11214: Voucher - Success input phone number on popup verifikasi nomor whatsapp" +
                "</br>depend - KMWA-11216: Voucher - Success save phone number when user click simpan on popup verifikasi nomor whatsapp" +
                "</br>depend - KMWA-11217: Voucher - tagging ticker with phone number existing appear when user already have phone number" +
                "</br>depend - KMWA-11221: Voucher - User input phone number between 10 until 15" +
                "</br>depend - KMWA-11222: Voucher - popup verifikasi only appear once on success payment";
        return this;
    }

    public HomepageModel popupWhatsappFromPaymentPrakerja() {
        logDepend = "</br>depend - KMWA-11203: Prakerja - Modal input nomor whatsapp appear when user success payment with kartu prakerja" +
                "</br>depend - KMWA-11204: Prakerja - Success input phone number on popup verifikasi nomor whatsapp" +
                "</br>depend - KMWA-11206: Prakerja - Success save phone number when user click simpan on popup verifikasi nomor whatsapp" +
                "</br>depend - KMWA-11207: Prakerja - tagging ticker with phone number existing appear when user already have phone number" +
                "</br>depend - KMWA-11211: Prakerja - User input phone number between 10 until 15" +
                "</br>depend - KMWA-11212: Prakerja - popup verifikasi only appear once on success payment";
        return this;
    }

    public HomepageModel popupWhatsappFromVaBCA() {
        logDepend = "</br>depend - KMWA-11233: VA - Modal input nomor whatsapp appear when user success payment with VA" +
                "</br>depend - KMWA-11234: VA - Success input phone number on popup verifikasi nomor whatsapp" +
                "</br>depend - KMWA-11236: VA - Success save phone number when user click simpan on popup verifikasi nomor whatsapp" +
                "</br>depend - KMWA-11237: VA - tagging ticker with phone number existing appear when user already have phone number" +
                "</br>depend - KMWA-11241: VA - User input phone number between 10 until 15" +
                "</br>depend - KMWA-11242: VA - popup verifikasi only appear once on success payment";
        return this;
    }

    public HomepageModel popupWhatsappFromIndomaret() {
        logDepend = "</br>depend - KMWA-11243: RETAIL - Modal input nomor whatsapp appear when user success payment with retail" +
                "</br>depend - KMWA-11244: RETAIL - Success input phone number on popup verifikasi nomor whatsapp" +
                "</br>depend - KMWA-11246: RETAIL - Success save phone number when user click simpan on popup verifikasi nomor whatsapp" +
                "</br>depend - KMWA-11247: RETAIL - tagging ticker with phone number existing appear when user already have phone number" +
                "</br>depend - KMWA-11251: RETAIL - User input phone number between 10 until 15" +
                "</br>depend - KMWA-11252: RETAIL - popup verifikasi only appear once on success payment";
        return this;
    }

    /* popup whatsapp gopay error di sandbox */
    public HomepageModel popupWhatsappFromGopay() {
        logDepend = "</br>depend - KMWA-11253: E-WALLET - Modal input nomor whatsapp appear when user success payment with e-wallet" +
                "</br>depend - KMWA-11254: E-WALLET - Success input phone number on popup verifikasi nomor whatsapp" +
                "</br>depend - KMWA-11256: E-WALLET - Success save phone number when user click simpan on popup verifikasi nomor whatsapp" +
                "</br>depend - KMWA-11257: E-WALLET - tagging ticker with phone number existing appear when user already have phone number" +
                "</br>depend - KMWA-11261: E-WALLET - User input phone number between 10 until 15" +
                "</br>depend - KMWA-11262: E-WALLET - popup verifikasi only appear once on success payment";
        return this;
    }

    public HomepageModel popupWhatsappFromCreditCard() {
        logDepend = "</br>depend - KMWA-11266: KARTU KREDIT - Modal input nomor whatsapp appear when user success payment with kartu kredit" +
                "</br>depend - KMWA-11267: KARTU KREDIT - Success input phone number on popup verifikasi nomor whatsapp" +
                "</br>depend - KMWA-11269: KARTU KREDIT - Success save phone number when user click simpan on popup verifikasi nomor whatsapp" +
                "</br>depend - KMWA-11270: KARTU KREDIT - tagging ticker with phone number existing appear when user already have phone number" +
                "</br>depend - KMWA-11274: KARTU KREDIT - User input phone number between 10 until 15";
        return this;
    }

    public HomepageModel tickerEksplore(){
        goToUrl = Host.getKariermu() + "eksplor";
        return this;
    }
}