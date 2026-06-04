package automation.pages.website.homepage.popupwhatsapp.api.response;

import sekolahmu.datasource.api.base.SekolahmuResponse;

public class CreateUserResponse extends SekolahmuResponse {

    public Data data;

    public static class Data {
        public Integer id;
        public String name;
        public String email;
        public String username;
    }
}