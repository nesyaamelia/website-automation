package automation.pages.website.paymentmethodprakerja;

import automation.pages.base.BaseSteps;

public interface PaymentPrakerjaStep extends BaseSteps<PaymentPrakerjaPage, PaymentPrakerjaModel> {

    void userPayWithPrakerjaId(PaymentPrakerjaModel paymentPrakerjaModel);
    void inputOtpPrakerja(PaymentPrakerjaModel paymentPrakerjaModel);
    void redeemCode(PaymentPrakerjaModel paymentPrakerjaModel);
    void verifyDetailTransactionPrakerja();
}