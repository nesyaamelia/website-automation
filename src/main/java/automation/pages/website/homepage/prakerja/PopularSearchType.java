package automation.pages.website.homepage.prakerja;

public enum PopularSearchType {
    UMKM("UMKM"),
    BAHASA_INGGRIS("Bahasa Inggris"),
    MICROSOFT("Microsoft"),
    DIGITAL_MARKETING("Digital Marketing"),
    BISNIS("Bisnis"),
    PERSIAPAN_KERJA("Persiapan Kerja");

    public String category;

    PopularSearchType(String list) {
        this.category = list;
    }
}