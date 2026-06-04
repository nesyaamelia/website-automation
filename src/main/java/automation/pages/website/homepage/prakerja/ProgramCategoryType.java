package automation.pages.website.homepage.prakerja;

public enum ProgramCategoryType {
    SALES_MARKETING("Sales & Marketing"),
    DATA_TEKNOLOGI("Data & Teknologi"),
    BAHASA("Bahasa"),
    BISNIS("Bisnis"),
    ADMINISTRASI_PERKANTORAN("Administrasi & Perkantoran"),
    PERSIAPAN_KERJA("Persiapan Kerja"),
    KESENIAN_FOTOGRAFI("Kesenian & Fotografi"),
    LAINNYA("Lainnya");

    public String category;

    ProgramCategoryType(String list) {
        this.category = list;
    }
}