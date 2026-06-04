package automation.pages.website.manageprofile.remote.database.table;

import sekolahmu.datasource.database.Column;

import java.math.BigInteger;

public class UserAccount {

    @Column(name = "id")
    public BigInteger id;
    @Column(name = "otp")
    public String otp;
    @Column(name = "otp_purpose_id")
    public Long otpPurposeId;
    @Column(name = "username")
    public String username;
    @Column(name = "name")
    public String name;
    @Column(name = "is_email_verified")
    public boolean isEmailVerified;
    @Column(name = "phone")
    public String phone;
    @Column(name = "email")
    public String email;
}
