package automation.pages.dashboard.mediaandadvertising.manageprogramsandinstitutions;

public enum PageType {

    HOMEPAGE("Beranda"),
    CATEGORY_PAGE_SALES_MARKETING("Halaman Kategori - Sales & Marketing"),
    CATEGORY_PAGE_DATA_TECHNOLOGY("Halaman Kategori - Data & Teknologi"),
    CATEGORY_PAGE_LANGUAGE("Halaman Kategori - Bahasa"),
    CATEGORY_PAGE_BUSINESS("Halaman Kategori - Bisnis"),
    CATEGORY_PAGE_ADMINISTRATION_OFFICE("Halaman Kategori - Administrasi & Perkantoran"),
    CATEGORY_PAGE_WORK_PREPARATION("Halaman Kategori - Persiapan Kerja"),
    CATEGORY_PAGE_ART_PHOTOGRAPHY("Halaman Kategori - Kesenian & Fotografi"),
    CATEGORY_PAGE_OTHER("Halaman Kategori - Lainnya");

    public final String page;

    PageType(String listPage){
        this.page = listPage;
    }

}
