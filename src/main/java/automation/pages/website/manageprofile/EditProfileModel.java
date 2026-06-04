package automation.pages.website.manageprofile;

import automation.data.User;
import automation.pages.base.BaseModel;
import automation.pages.website.manageprofile.remote.database.ManageProfileDbHelper;
import automation.pages.website.manageprofile.remote.database.table.UserAccount;

import java.util.ArrayList;
import java.util.List;

public class EditProfileModel extends BaseModel {

    public String logDepend = "";
    public String industry = "Teknologi";
    public String district = "jakarta selatan";
    public String nutpk = "ENG3334";
    public String postalCode;
    public String username;
    public String email;
    public String phoneNumber;
    public String fullName;
    public String gender;
    public String lastEducation;
    public String fieldOfStudy;
    public String address;
    public String aboutMe;
    public String institution;
    public String pathImage;
    public String whatsappNumber;
    public String usernameFromDb;
    public String fullNameFromDb;
    public String phoneNumberFromDb;
    public String value;
    public String inputPinUser;
    public boolean isInvalidPhoto = false;
    public boolean isGetWhatsappNumber = true;
    public boolean isEmptyValue = false;
    public boolean isCloseWhatsappNumber;
    public boolean isRefresh;
    public boolean isGoBack;
    public boolean isCheckFieldNik;
    public boolean isEmailVerified;
    public boolean isNegativeData;
    public boolean isEmpty;
    public boolean isInvalidFieldOfStudy;
    public boolean isParseFieldOfStudy;
    public boolean isInvalidPin;
    public UserYearLevelType roleType;
    public ArrayList<String> validateNik;

    public static EditProfileModel newInstance() {
        return new EditProfileModel();
    }

    public EditProfileModel invalidEmptyPrimaryData() {
        isEmptyValue = true;
        username = "";
        email = "";
        phoneNumber = "";
        fullName = "";
        return this;
    }

    public EditProfileModel logValidPopupWhatsapp() {
        logDepend = "</br>depend - KMWA-11367: Profil - Success input phone number on popup verifikasi nomor whatsapp" +
                "</br>depend - KMWA-11369: Profil - Success save phone number when user click simpan on popup verifikasi nomor whatsapp" +
                "</br>depend - KMWA-11370: Profil - tagging ticker with phone number existing appear when user already have phone number" +
                "</br>depend - KMWA-11374: Profil - User input phone number between 10 until 15" +
                "</br>depend - KMWA-11375: Profil - popup verifikasi only appear once on profil";
        return this;
    }

    public EditProfileModel closePopupWhatsapp() {
        isCloseWhatsappNumber = true;
        return this;
    }

    public EditProfileModel phoneNumberLessThan10() {
        isGetWhatsappNumber = false;
        whatsappNumber = "08139";
        return this;
    }

    public EditProfileModel phoneNumberMoreThan15() {
        isGetWhatsappNumber = false;
        whatsappNumber = "0813982798726376378";
        return this;
    }

    public EditProfileModel symbolPhoneNumber() {
        isGetWhatsappNumber = false;
        whatsappNumber = "@@@@@@@@@";
        return this;
    }

    public EditProfileModel goBackAndValidatePopupWhatsapp() {
        isGoBack = true;
        return this;
    }

    public EditProfileModel uploadValidPhoto() {
        pathImage = "/files/content/SampleJPGImage_50kbmb.jpg";
        return this;
    }

    public EditProfileModel uploadInvalidPhoto() {
        isInvalidPhoto = true;
        pathImage = "/files/content/file_example_JPG_2500kB.jpeg";
        return this;
    }

    public EditProfileModel validDataRoleProfessional() {
        User newUser = new User();
        username = newUser.firstName + newUser.lastName;
        fullName = newUser.firstName + " " + newUser.lastName;
        roleType = UserYearLevelType.PROFESSIONAL;
        email = newUser.email;
        phoneNumber = newUser.cellPhone;
        gender = "Laki-laki";
        lastEducation = "S1";
        address = newUser.fullAddress;
        aboutMe = newUser.quotes;
        fieldOfStudy = "Bahasa Indonesia";
        district = "Depok";
        institution = "Automation";
        nutpk = newUser.cellPhone;
        postalCode = "16421";
        logDepend = "</br>depend - ADKR-11717: Profile - button Simpan Perubahan on modal username cannot be clicked when there is no change" +
                "</br>depend - ADKR-11718: Profile - button Simpan Perubahan on modal nama lengkap cannot be clicked when there is no change" +
                "</br>depend - ADKR-11719: Profile - button Simpan Perubahan on modal nomor ponsel cannot be clicked when there is no change" +
                "</br>depend - Profile - Success edit tujuan belajar on preferensi konten";
        return this;
    }

    public EditProfileModel invalidPrimaryDataUser() {
        isNegativeData = true;
        username = "!@#$%^&*()";
        fullName = "!@#$%^&*()";
        roleType = UserYearLevelType.PROFESSIONAL;
        email = "!@#$%^&*()";
        phoneNumber = "!@#$%^&*()";
        return this;
    }

    public EditProfileModel validDataRoleTeacherAndCollege() {
        User newUser = new User();
        username = newUser.firstName + newUser.lastName;
        fullName = newUser.firstName + " " + newUser.lastName;
        email = newUser.email;
        phoneNumber = newUser.cellPhone;
        gender = "Laki-laki";
        lastEducation = "S1";
        address = newUser.fullAddress;
        aboutMe = newUser.quotes;
        fieldOfStudy = "Bahasa Indonesia";
        district = "Depok";
        institution = "Automation";
        nutpk = newUser.cellPhone;
        postalCode = "16421";
        return this;
    }

    public EditProfileModel checkFieldNIKTeacher() {
        isCheckFieldNik = true;
        validateNik = listFieldEditProfileTeacher();
        return this;
    }

    public EditProfileModel checkFieldNIKParent() {
        isCheckFieldNik = true;
        validateNik = listFieldEditProfileParent();
        return this;
    }

    public EditProfileModel checkFieldNIKStudent() {
        isCheckFieldNik = true;
        validateNik = listFieldEditProfileStudent();
        return this;
    }

    public EditProfileModel inputInvalidMaxValue(){
        isNegativeData = true;
        username = "SuperAmazingExtraLongUsernameThatDoesNotFitEasilyButIsStillValidAndVeryCoolWithNoSpacesAtAll123422121";
        fullName = "Alexander Jonathan Michael Williams von Heidelberg Santiago Ramirez da Silva Fernández Montoya García de la Vega del Cam1po y Castillo";
        phoneNumber = "392093";
        logDepend = "</br>depend : Profile - button ubah on field username able to click" +
                "</br>depend : Profile - button X able to click on edit username modal" +
                "</br>depend : Profile - button ubah on field nama lengkap able to click" +
                "</br>depend : Profile - button ubah on field nomor ponsel able to click";
        return this;
    }

    public EditProfileModel selectPrimaryDataUser(String email) {
        List<UserAccount> list = ManageProfileDbHelper.getPrimaryDataUser(email);
        usernameFromDb = list.get(0).username;
        fullNameFromDb = list.get(0).name;
        phoneNumberFromDb = list.get(0).phone;
        isEmailVerified = list.get(0).isEmailVerified;
        return this;
    }

    public EditProfileModel invalidAdditionalDataUser() {
        User newUser = new User();
        isNegativeData = true;
        gender = "Laki-laki";
        lastEducation = "S1";
        address = newUser.fullAddress;
        aboutMe = newUser.quotes;
        fieldOfStudy = "Bahasa Indonesia";
        district = "Depok";
        institution = "Automation";
        nutpk = newUser.cellPhone;
        postalCode = "&@^%#&";
        return this;
    }

    public EditProfileModel invalidFieldOfStudy() {
        isInvalidFieldOfStudy = true;
        fieldOfStudy = "*&^%$###@$";
        return this;
    }

    public EditProfileModel parseFieldOfStudyFromRegister() {
        isParseFieldOfStudy = true;
        return this;
    }

    public EditProfileModel inputInvalidPin() {
        isInvalidPin = true;
        inputPinUser = "000000";
        return this;
    }

    public ArrayList<String> listFieldEditProfileTeacher() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Foto Profil");
        list.add("Username");
        list.add("Nama Lengkap");
        list.add("Email");
        list.add("Nomor Ponsel");
        list.add("Jenis Kelamin");
        list.add("Tanggal Lahir");
        list.add("Pendidikan Terakhir");
        list.add("Alamat Lengkap");
        list.add("Tentang Saya");
        list.add("Bidang Studi/Industri");
        list.add("Kota/Kabupaten");
        list.add("Tujuan Belajar");
        return list;
    }
    public ArrayList<String> listFieldEditProfileParent() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Foto Profil");
        list.add("Username");
        list.add("Nama Lengkap");
        list.add("Email");
        list.add("Nomor Ponsel");
        list.add("Jenis Kelamin");
        list.add("Tanggal Lahir");
        list.add("Pendidikan Terakhir");
        list.add("Bidang Studi/Industri");
        list.add("Alamat Lengkap");
        list.add("Tentang Saya");
        list.add("Bidang Studi/Industri");
        list.add("Kota/Kabupaten");
        list.add("Tujuan Belajar");
        return list;
    }

    public ArrayList<String> listFieldEditProfileStudent() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Foto Profil");
        list.add("Username");
        list.add("Nama Lengkap");
        list.add("Email");
        list.add("Nomor Ponsel");
        list.add("Jenis Kelamin");
        list.add("Tanggal Lahir");
        list.add("Agama");
        list.add("Pendidikan Terakhir");
        list.add("Alamat Lengkap");
        list.add("Tentang Saya");
        list.add("Bidang Studi/Industri");
        list.add("Kota/Kabupaten");
        list.add("Tujuan Belajar");
        return list;
    }
}