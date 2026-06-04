package automation.pages.website.paymentmethodprakerja;

import automation.data.User;
import automation.pages.base.BaseModel;
import automation.pages.website.programmu.remote.database.ProgrammuDbHelper;

public class PaymentPrakerjaModel extends BaseModel {

    public String prakerjaID = System.getenv().getOrDefault("TEST_PRAKERJA_ID", "1234567890123456");
    public String otpPrakerja;
    public String redeemCodeSuccess;
    public String errorMessage;
    public int statusSubmissionUK = 2;
    public int statusSubmissionTPM = 2;
    public boolean helpPrakerja;
    public boolean isRedeem;
    public boolean closeRedeem;
    public boolean isValid;
    public boolean isDeleteInputCode;
    public boolean isInvalidId;

    public static PaymentPrakerjaModel newInstance() {
        return new PaymentPrakerjaModel();
    }

    public PaymentPrakerjaModel validPrakerjaNumber() {
        prakerjaID = System.getenv().getOrDefault("TEST_PRAKERJA_ID", "1234567890123456");
        return this;
    }

    public PaymentPrakerjaModel invalidPrakerjaNumber() {
        prakerjaID = "1234567890123456";
        return this;
    }

    public PaymentPrakerjaModel prakerjaNumberLessThan16() {
        prakerjaID = "12345678910";
        isInvalidId = true;
        return this;
    }

    public PaymentPrakerjaModel prakerjaNumberMoreThan16() {
        prakerjaID = "12345678901234567890";
        isInvalidId = true;
        return this;
    }

    public PaymentPrakerjaModel specialCaracterPrakerjaNumber() {
        prakerjaID = "!@#$%^&*()";
        return this;
    }

    public PaymentPrakerjaModel helpKariermuPrakerja() {
        helpPrakerja = true;
        return this;
    }

    public PaymentPrakerjaModel validOtpPrakerja() {
        otpPrakerja = "123456";
        return this;
    }

    public PaymentPrakerjaModel invalidOtpPrakerja() {
        otpPrakerja = "112233";
        return this;
    }

    public PaymentPrakerjaModel validRedeemCode() {
        isRedeem = true;
        isValid = true;
        redeemCodeSuccess = getRedeemCode();
        return this;
    }

    public PaymentPrakerjaModel closeRedeem() {
        closeRedeem = true;
        return this;
    }

    public PaymentPrakerjaModel codeIsEmpty() {
        isRedeem = true;
        isValid = false;
        redeemCodeSuccess = " ";
        errorMessage = "Redeem Code sudah digunakan";
        return this;
    }

    public PaymentPrakerjaModel codeNotExist() {
        isRedeem = true;
        isValid = false;
        redeemCodeSuccess = "ERRTRX40020";
        errorMessage = "Transaksi tidak ditemukan";
        return this;
    }

    public PaymentPrakerjaModel courseTypeDoesntMatch() {
        isRedeem = true;
        isValid = false;
        redeemCodeSuccess = "ERRTRX40001";
        errorMessage = "Gagal verifikasi, tipe pelatihan tidak sesuai.";
        return this;
    }

    public PaymentPrakerjaModel courseNotStartedOrAlreadyFinished() {
        isRedeem = true;
        isValid = false;
        redeemCodeSuccess = "ERRTRX40027";
        errorMessage = "Jadwal pelatihan belum dimulai atau sudah selesai, silahkan cek jadwal pelatihan pada dashboard Prakerja";
        return this;
    }

    public PaymentPrakerjaModel codeExistButLPDoesntOwnTheCourseId() {
        isRedeem = true;
        isValid = false;
        redeemCodeSuccess = "ERRTRX40028";
        errorMessage = "Gagal verifikasi, kode redeem bukan milik lembaga pelatihan.";
        return this;
    }

    public PaymentPrakerjaModel codeVoid() {
        isRedeem = true;
        isValid = false;
        redeemCodeSuccess = "ERRTRX40022";
        errorMessage = "Gagal verifikasi, kode sudah dibatalkan";
        return this;
    }

    public PaymentPrakerjaModel transactionVoid() {
        isRedeem = true;
        isValid = false;
        redeemCodeSuccess = "ERRTRX40101";
        errorMessage = "Gagal verifikasi, transaksi sudah dibatalkan";
        return this;
    }

    public PaymentPrakerjaModel codeAlreadyUsed() {
        isRedeem = true;
        isValid = false;
        isDeleteInputCode = false;
        redeemCodeSuccess = "PRAT-HEGQQO";
        errorMessage = "Redeem Code sudah digunakan";
        return this;
    }

    public PaymentPrakerjaModel deleteInputRedeemCode() {
        isRedeem = true;
        isValid = false;
        redeemCodeSuccess = "ERRTRX40021";
        isDeleteInputCode = true;
        return this;
    }

    private String getRedeemCode() {
        User dummy = new User();
        String code = "DUMMY-" + dummy.firstName + dummy.nik;
        for (int i = 1; i <= 4; i++) {
            if (i == 4) {
                ProgrammuDbHelper.generateFRDummyCode(999, code, (999 + code), 1, 1, 1);
                ProgrammuDbHelper.generateSubmissionDummyCode(999, code, 1, statusSubmissionUK);
            } else {
                ProgrammuDbHelper.generateFRDummyCode(i, code, i + code, 1, 1, 1);
                ProgrammuDbHelper.generateSubmissionDummyCode(i, code, 1, statusSubmissionTPM);
            }
        }
        return code;
    }
}