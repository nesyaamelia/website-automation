package automation.pages.website.homepage.prakerja;

import automation.config.Host;
import automation.pages.base.BasePrakerjaPage;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static automation.pages.website.homepage.prakerja.HomepageLocator.selectProgramFilter;

public class HomepagePage extends BasePrakerjaPage<HomepageModel, HomepageLocator> implements HomepageStep {

    public static HomepagePage newInstance(WebDriver webDriver, WebDriverWait webDriverWait) {
        return new HomepagePage().init(webDriver, webDriverWait);
    }

    @Override
    public void setupController() {
        this.mModel = HomepageModel.newInstance();
    }

    @Override
    public void setupPathElement() {
        this.mLocator = HomepageLocator.newInstance();
    }

    @Override
    public HomepagePage init(WebDriver webDriver, WebDriverWait webDriverWait) {
        initialInstance(webDriver, webDriverWait);
        return this;
    }

    @Override
    public void sectionContainer(SectionType programType) {
        switch (programType) {
            case KATEGORI_PROGRAM:
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.sectionProgramCategoryList));
                boolean isShowCategoryList = getElement().verifyElementPresent(mLocator.sectionProgramCategoryList);
                logAndAssertTrue(isShowCategoryList, "show category list" +
                        "</br>depend - KMWA-3781: Homepage Prakerja There Is A Kategori Program Section On The Homepage");
                break;
            case PROGRAM_ONLINE:
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.cardProgramOnline));
                boolean isShowProgramOnline = getElement().verifyElementPresent(mLocator.cardProgramOnline);
                logAndAssertTrue(isShowProgramOnline, "show program online");
                getElement().scrollToElement(mLocator.cardProgramOnline);
                int getProgramOnline = getElement().getSize(mLocator.ribbonWebinar);
                boolean isSizeProgram = getProgramOnline >= 5;
                logAndAssertTrue(isSizeProgram, "show list program online more than 4" +
                        "</br>depend - KMWA-6432: Ensure On The First Slide 5 Online Program Cards Appear");
                break;
            case PROGRAM_OFFLINE:
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.sectionProgramOffline));
                getElement().perfomeToElement(mLocator.sectionProgramOffline);
                boolean isProgramOffline = getElement().verifyElementPresent(mLocator.sectionProgramOffline);
                logAndAssertTrue(isProgramOffline, "show program offline");
                break;
        }
    }

    @Override
    public void selectProgram(SectionType programType, boolean isCancelSelectOffline) {
        String getProgramName = "";
        switch (programType) {
            case PROGRAM_RECOMMENDATION:
                getProgramName = getElement().getText(mLocator.getTextProgramNameRecommendation);
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.getTextProgramNameRecommendation));
                getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.getTextProgramNameRecommendation));
                getElement().click(mLocator.getTextProgramNameRecommendation);
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.textProgramNameProgramDetail));
                String programNameProgramDetail = getElement().getText(mLocator.textProgramNameProgramDetail);
                logAndAssertEqual(getProgramName.toLowerCase(), programNameProgramDetail.toLowerCase(), "verify program selected" +
                        "</br>depend - KMWA-6440: Ensure Able Click Card Program Rekomendasi");
                break;
            case PROGRAM_OFFLINE:
                getElement().perfomeToElement(mLocator.sectionProgramOffline);
                int sizeProgramOffline = getElement().getSize(mLocator.sizeProgramOffline);
                for (int i = 1; i <= sizeProgramOffline; i++) {
                    getProgramName = getElement().getText(mLocator.nameProgramOffline(i));
                    if (getProgramName.equalsIgnoreCase("Journey Beli Program Prakerja")) {
                        break;
                    }
                }
                logAndAssertEqual("Journey Beli Program Prakerja", getProgramName, "show program selected");
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.textContains(selectProgramFilter)));
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.textContains(selectProgramFilter)));
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.textContains(selectProgramFilter)));
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.programNamePopup));
                String getProgramNamePopup = getElement().getText(mLocator.programNamePopup);
                logAndAssertEqual(getProgramNamePopup.toLowerCase(), getProgramNamePopup.toLowerCase(), "show program name" +
                        "</br>depend - KMWA-6446: Ensure Able Click Card Program Offline");
                if (isCancelSelectOffline) {
                    getElement().click(mLocator.iconLocationProgramOffline);
                    getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.modalLocationProgramOffline));
                    boolean isCancelLocationOffline = getElement().verifyElementNotPresent(mLocator.modalLocationProgramOffline);
                    logAndAssertTrue(isCancelLocationOffline, "cancel select option location offline");
                } else if (getElement().verifyElementPresent(mLocator.getLocationProgramOffline)) {
                    String getLocationOffline = getElement().getText(mLocator.getLocationProgramOffline);
                    boolean isMatchFormat = getLocationOffline.matches("([A-Za-z\\s\\W\\d])+");
                    logAndAssertTrue(isMatchFormat, "match format program location offline");
                    getElement().click(mLocator.buttonArrowLocationOffline);
                    getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.groupLocationOffline));
                    getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.groupLocationOffline));
                    String totalLocationAvailable = getElement().getText(mLocator.totalLocationAvailable).replaceAll("\\D+", "");
                    String getMemberLocationOffline = getElement().getText(mLocator.listNameProgramOffline);
                    int getSizeListTotalLocationAvailable = getElement().getSize(mLocator.getSizeListLocationProgramOffline);
                    logAndAssertEqual(Integer.parseInt(totalLocationAvailable), getSizeListTotalLocationAvailable, "show total list location program");
                    getElement().click(mLocator.groupLocationOffline);
                    getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.groupLocationOffline));
                    getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.textProgramNameProgramDetail));
                    getProgramName = getElement().getText(mLocator.textProgramNameProgramDetail);
                    logAndAssertEqual(getMemberLocationOffline.toLowerCase(), getProgramName.toLowerCase(), "selected program offline");
                }
                break;
            case INSTITUTION:
                getElement().perfomeToElement(mLocator.buttonSeeAllMitra);
                String institution = getElement().getText(mLocator.getTextInstitution);
                getElement().click(mLocator.getTextInstitution);
                /* dipakai untuk delay response new page */
                stepsHelper.delay(globalVariable.midDelay);
                String getInstitutionDetail = getElement().getText(mLocator.textInstitutionNameInstitutionDetail);
                logAndAssertEqual(institution.toLowerCase(), getInstitutionDetail.toLowerCase(), "click card institution" +
                        "</br>depend - KMWA-6452: Ensure Able Click Card Lembaga");
                break;
            case PROGRAM_ONLINE:
                getElement().scrollToElement(mLocator.cardProgramOnline);
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.indexRibbonWebinar(1)));
                getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.indexRibbonWebinar(1)));
                boolean isNotShowPopupLocation = getElement().verifyElementNotPresent(mLocator.listLocationProgramOffline);
                logAndAssertTrue(isNotShowPopupLocation, "not open popup location");
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.buttonShareProgram));
                boolean isShowButtonShare = getElement().verifyElementPresent(mLocator.buttonShareProgram);
                logAndAssertTrue(isShowButtonShare, "show button share");
                break;
            case NEAREST_SCHEDULE:
                getElement().waitUntilClick(mLocator.buttonHomepage);
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.staticBanner));
                getElement().scrollToElement(mLocator.imageStaticBanner);
                getElement().click(mLocator.programNearestSchedule);
                boolean isSectionNearestScheduleAppear = getElement().handleElementPresent(mLocator.sectionNearestSchedule, 5);
                logAndAssertTrue(isSectionNearestScheduleAppear, "section nearest schedule appear" + mModel.nearestSchedule());
        }
    }

    @Override
    public void viewAllProgram(SectionType programType) {
        switch (programType) {
            case PROGRAM_RECOMMENDATION:
                getElement().click(mLocator.buttonAllProgramRecommendation);
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.programCard));
                boolean isShowCardProgram = getElement().verifyElementPresent(mLocator.programCard);
                logAndAssertTrue(isShowCardProgram, "show card program" +
                        "</br>depend - KMWA-6438: Ensure Able Click Button Lihat Semua On Program Rekomendasi Section");
                break;
            case PROGRAM_OFFLINE:
                getElement().perfomeToElement(mLocator.buttonAllPrpgramOffline);
                getElement().click(mLocator.buttonAllPrpgramOffline);
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.programOfflineList));
                boolean isShowList = getElement().verifyElementPresent(mLocator.programOfflineList);
                logAndAssertTrue(isShowList, "show list program" +
                        "</br>depend - KMWA-6444: Ensure Able Click Button Lihat Semua On Program Offline Section");
                break;
            case INSTITUTION:
                getElement().clickHandlerJs(mLocator.buttonSeeAllMitra);
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.titleInstitutionPage));
                boolean isPageInstitutionAppear = getElement().verifyElementPresent(mLocator.titleInstitutionPage);
                logAndAssertTrue(isPageInstitutionAppear, "Lembaga page is present" +
                        "</br>depend - KMWA-6450: ensureAbleClickButtonLihatSemuaOnLembagaSection" +
                        "</br>depend - KMWA-4492: [Lembaga Page] Entry Point - Make sure there is a Lembaga Favorit section on the desktop web platform" +
                        "</br>depend - KMWA-4491: [Lembaga Page] Entry Point - Make sure the CTA button in the Lembaga Favorit section on the Prakerja Landing page with the wording \"Lihat Semua\" on the desktop web platform");
                break;
            case PROGRAM_ONLINE:
                getElement().perfomeToElement(mLocator.seeAllProgramOnline);
                getElement().click(mLocator.seeAllProgramOnline);
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.cardResultProgram));
                boolean isShowCardProgramOnline = getElement().verifyElementPresent(mLocator.cardResultProgram);
                logAndAssertTrue(isShowCardProgramOnline, "show card program" +
                        "</br>depend - KMWA-6431: Ensure Able Click Button Lihat Semua For Program Online");
                break;
            case PROGRAM_PEMBELAJARAN_MANDIRI:
                getElement().perfomeToElement(mLocator.buttonRouteToSrp);
                getElement().click(mLocator.buttonRouteToSrp);
                break;
            case NEAREST_SCHEDULE:
                getElement().waitUntilClick(mLocator.buttonHomepage);
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.staticBanner));
                getElement().scrollToElement(mLocator.sectionNearestSchedule);
                int totalNearestSchedule = getElement().getSize(mLocator.totalNearestScheduleCard);
                logAndAssertTrue(totalNearestSchedule <= 15, "Maximum card nearest schedule");
                if (totalNearestSchedule > 1) {
                    for (int i = 1; i < totalNearestSchedule; i++) {
                        double firstRating = Double.parseDouble(getElement().getText(mLocator.getRating(i)).replaceAll("[^\\d.]", ""));
                        double secondRating = Double.parseDouble(getElement().getText(mLocator.getRating(i + 1)).replaceAll("[^\\d.]", ""));
                        logAndAssertTrue(firstRating >= secondRating, "sort highest to lowest");
                    }
                }
                getElement().click(mLocator.tabOffline);
                getElement().click(mLocator.programNearestSchedule);
                boolean isProgramAppear = getElement().verifyElementPresent(mLocator.programNearestSchedule);
                logAndAssertTrue(isProgramAppear, "program on tab offline appear" +
                        "</br>depend - KMWA-10028: Jadwal Terdekat - Success showing program with jadwal terdekat Tatap Muka Offline in section Jadwal Terdekat when user click button Tatap Muka Offline" +
                        "</br>depend - KMWA-9987: Jadwal Terdekat - Redirect to pop up Pilih Jadwal but Jadwal terdekat is disabled if quota is unavailable when first user that click program card in section Program dengan Jadwal Terdekat" +
                        "</br>depend - KMWA-10016: Jadwal Terdekat - Showing Avatar on card program in section Program dengan Jadwal terdekat");
        }
    }

    @Override
    public void chevronProgramRecommendation() {
        getElement().scrollToElement(mLocator.sectionProgramRecommendation);
        if (getElement().isEnabled(mLocator.buttonChevronRight)) {
            getElement().click(mLocator.buttonChevronRight);
            logPassed("Button chevron right is clickable");
            stepsHelper.delay(globalVariable.shortDelay);
            if (getElement().isEnabled(mLocator.buttonChevronLeft)) {
                getElement().click(mLocator.buttonChevronLeft);
                logPassed("Button chevron left is clickable");
            } else {
                logFailed("Button chevron left not clickable", null);
            }
        } else {
            logFailed("Button chevron right not clickable", null);
        }
    }

    @Override
    public void staticBanner() {
        stepsHelper.delay(globalVariable.shortDelay);
        if (getElement().verifyElementPresent(mLocator.imageStaticBanner)) {
            getElement().scrollToElement(mLocator.imageStaticBanner);
            String getSrcBanner = getElement().getAttributeFrom(mLocator.imageStaticBanner, "src");
            logPassed("Banner present with src: " + getSrcBanner);
        } else {
            logFailed("Banner not present", null);
        }
    }

    @Override
    public void programCategory() {
        stepsHelper.delay(globalVariable.shortDelay);
        if (getElement().verifyElementPresent(mLocator.sectionProgramCategoryList)) {
            getElement().waitUntilClick(mLocator.programCategoryTitle);
            stepsHelper.delay(globalVariable.shortDelay);
            if (getElement().verifyElementPresent(mLocator.programCard)) {
                logPassed("Success navigate to page detail category");
            } else {
                logInfo("Card not found");
            }
        } else {
            logFailed("List category not found", null);
        }
    }

    @Override
    public void selectProgramCategory(ProgramCategoryType programCategoryType) {
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.selectProgramCategoryList(1)));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.selectProgramCategoryList(1)));
        int getSizeCategoryList = getElement().getSize(mLocator.programCategoryList);
        logInfo("Category list: " + getSizeCategoryList);
        for (int i = 1; i <= getSizeCategoryList; i++) {
            String programCategoryTitle = getElement().getText(mLocator.programCategoryTitle(i));
            if (programCategoryTitle.equalsIgnoreCase(programCategoryType.category)) {
                getElement().click(mLocator.selectProgramCategoryList(i));
                logPassed("Category selected: " + programCategoryTitle +
                        "</br>depend KMWA - 3782: homepagePrakerjaEnsureCategoryOnSectionKategoriProgramAbleToClickAndRedirectToCategoryDetailPageWithPillsOn");
                if (getElement().verifyElementPresent(mLocator.activeSelectedPills)) {
                    String getPillsOn = getElement().getText(mLocator.activeSelectedPills);
                    if (getPillsOn.equalsIgnoreCase(programCategoryType.category)) {
                        logPassed("Pills selected equals: " + getPillsOn);
                        break;
                    } else {
                        logFailed("Pills selected not equals", null);
                    }
                } else {
                    logFailed("Page category selected not present", null);
                }
            }
        }
    }

    @Override
    public void popularSearch(PopularSearchType popularSearchType) {
        getElement().waitUntilClick(mLocator.inputTextSearchBar);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.textPopularSearch));
        int listSearchPopular = getElement().getSize(mLocator.textPopularSearch);
        for (int i = 1; i <= listSearchPopular; i++) {
            String listSearch = getElement().getText(mLocator.listKeySearch(i));
            if (listSearch.equalsIgnoreCase(popularSearchType.category)) {
                getElement().click(mLocator.listKeySearch(i));
                logPassed("Category selected: " + listSearch);
                stepsHelper.delay(globalVariable.shortDelay);
                if (getElement().verifyElementPresent(mLocator.titleSearchKeyword)) {
                    String keyword = getElement().getText(mLocator.titleSearchKeyword);
                    logAndAssertEqual(keyword, popularSearchType.category, "search keyword" +
                            "</br>KMWA - 9995: Pencarian Terpopuler- Redirect to search result page and filtered by Umkm when user click menu Umk in the searchbar" +
                            "</br>KMWA - 9996: Pencarian Terpopuler - Redirect to search result page and filtered by 'Bahasa Inggris' when user can click menu 'Bahasa Inggris' in the searchbar");
                } else if (getElement().verifyElementPresent(mLocator.emptyStateSearch)) {
                    String emptyState = getElement().getText(mLocator.emptyStateSearch);
                    logInfo("Message: " + emptyState);
                }
                break;
            }
        }
    }

    @Override
    public void pageProgramCategory(HomepageModel homepageModel) {
        getElement().navigateToUrl(Host.getPrakerja() + mModel.validUrl);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.tagCategoryProgram));
        String getUrl = webDriver.getCurrentUrl();
        int totalTagCategory = getElement().getSize(mLocator.tagCategoryProgram);
        logAndAssertEqual(8, totalTagCategory, "show total category");
        if (homepageModel.isResetUrlCategory) {
            int index = getUrl.indexOf("=");
            String currentUrl = getUrl.substring(0, index);
            getElement().navigateToUrl(currentUrl + homepageModel.invalidUrl);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.emptyStateMessage));
            boolean isShowEmptyState = getElement().verifyElementPresent(mLocator.emptyStateMessage);
            logAndAssertTrue(isShowEmptyState, "show empty state");
        } else {
            getElement().waitUntilSetText(mLocator.inputKeywordSearchCategory, mModel.searchKeyword);
            getElement().waitUntilSetText(mLocator.inputKeywordSearchCategory, Keys.chord(Keys.ENTER));
            getElement().waitUntilLoadingProcess("Loading...");
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.programCard));
            int programCard = getElement().getSize(mLocator.programCard);
            boolean isProgramNameEquals = false;
            for (int i = 1; i <= programCard; i++) {
                String programName;
                try {
                    programName = getElement().getText(mLocator.programName(i)).toLowerCase();
                } catch (StaleElementReferenceException s) {
                    programName = getElement().getText(mLocator.programName(i)).toLowerCase();
                }
                isProgramNameEquals = programName.toLowerCase().contains("program");
                if (!isProgramNameEquals) {
                    break;
                }
            }
            logAndAssertTrue(isProgramNameEquals, "show program with contains keyword");
            if (homepageModel.isRefreshPage) {
                webDriver.navigate().refresh();
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.programCard));
                boolean isShowCardProgram = getElement().verifyElementPresent(mLocator.programCard);
                logAndAssertTrue(isShowCardProgram, "show card program");
            }
        }
    }

    @Override
    public void bannerSection(BannerType bannerType) {
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.logoKariermuNavbar));
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.logoKariermuNavbar));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.logoKariermuNavbar));
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.programList));
        getElement().scrollToElement(mLocator.sectionPencarianPopuler);
        switch (bannerType) {
            case TUKAR_VOUCHER:
                if (getElement().verifyElementPresent(mLocator.voucherBanner)) {
                    String titleBanner = getElement().getText(mLocator.titleRedeemVoucher);
                    logPassed("Title: " + titleBanner);
                } else {
                    logFailed("Banner not found", null);
                }
                getElement().click(mLocator.buttonRedeemFromBanner);
                stepsHelper.delay(globalVariable.midDelay);
                if (getElement().verifyElementPresent(mLocator.sectionLogin)) {
                    logPassed("Login section is present");
                } else if (getElement().verifyElementPresent(mLocator.redeemVoucherTitle)) {
                    logPassed("Success navigate to tukar voucher page");
                } else {
                    logFailed("Page not responding", null);
                }
                break;
            case PRAKERJA:
                logInfo("Section is present");
                break;
            case STATIC_BANNER:
                if (getElement().verifyElementPresent(mLocator.staticBanner)) {
                    String srcImage = getElement().getAttributeFrom(mLocator.imageStaticBanner, "src");
                    logPassed("Static banner is present: " + srcImage);
                } else {
                    logFailed("Static banner not present", null);
                }
                break;
        }
    }

    @Override
    public void validateFooterPrakerja() {
        getElement().scrollToElement(mLocator.logoKariermu);
        if (getElement().verifyElementPresent(mLocator.logoKariermu)) {
            logPassed("Successfully show Karier.mu logo");
        } else {
            logFailed("Cannot show Karier.mu logo", null);
        }
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonCareer));
        getElement().click(mLocator.buttonCareer);
        String mainWindow = webDriver.getWindowHandle();
        getElement().switchWindow();
        String getKarierUrl = webDriver.getCurrentUrl();
        if (getKarierUrl.equalsIgnoreCase(System.getenv().getOrDefault("TEST_KARIER_URL", "https://jobs.example.com/partner"))) {
            logPassed("Successfully redirect to career page" +
                    "</br>depend - 4627: makeSureThereIsACareerLinkInTheFooterOnThePrakerjaPage" +
                    "</br>depend - 4625: makeSureTheCareerFooterLinkOnThePrakerjaPageIsUsingTheLatestOne" +
                    "</br>depend - 4629: makeSureTheCareerLinkInTheFooterOnThePrakerjaPageCanBeClickedAndMovePagesAccordingToTheLink");
            webDriver.close();
            webDriver.switchTo().window(mainWindow);
        } else {
            logFailed("Cannot redirect to career page", null);
        }
    }

    @Override
    public void collaborationPartnerSection(MitraType mitraType) {
        String getTitle;
        boolean isShowTitle;
        getElement().scrollToElement(mLocator.buttonMitraKolaborasi);
        getElement().waitUntilClick(mLocator.buttonMitraKolaborasi);
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.titleMitraKolaborasiPage));
        boolean isShowMitraKolaborasi = getElement().verifyElementPresent(mLocator.titleMitraKolaborasiPage);
        logAndAssertTrue(isShowMitraKolaborasi, "show title page" +
                "</br>depend - KMWA-7651: Ensure Headline Berkolaborasi Bersama KarierMu Dan Bantu Mereka Temukan Potensi Appears" +
                "</br>depend - KMWA-7652: Ensure Every Wording On Mitra KarierMu Page Appears Properly According To Design Figma");
        stepsHelper.delay(globalVariable.shortDelay);
        switch (mitraType) {
            case MITRA_LEMBAGA_PELATIHAN:
                getElement().waitUntilClick(mLocator.mitraColaborationClass("Gabung sebagai Mitra Lembaga Pelatihan"));
                getElement().switchWindow();
                stepsHelper.delay(globalVariable.midDelay);
                getTitle = webDriver.getTitle();
                isShowTitle = getTitle.contains("Form Kemitraan Penyedia Pelatihan");
                logAndAssertTrue(isShowTitle, "open form mitra");
                break;
            case MITRA_PEMBERI_KERJA:
                getElement().scrollToElement(mLocator.mitraColaborationClass("Gabung sebagai Mitra Pemberi Kerja"));
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.mitraColaborationClass("Gabung sebagai Mitra Pemberi Kerja")));
                getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.mitraColaborationClass("Gabung sebagai Mitra Pemberi Kerja")));
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", getElement().findElement(mLocator.mitraColaborationClass("Gabung sebagai Mitra Pemberi Kerja")));
                stepsHelper.delay(globalVariable.midDelay);
                getElement().switchWindow();
                getTitle = webDriver.getTitle();
                isShowTitle = getTitle.contains("Form Partisipasi Mitra Pemberi Kerja");
                logAndAssertTrue(isShowTitle, "show form partisipasi mitra pemberi kerja");
                break;
            case MITRA_PENGAJAR_PROFFESIONAL:
                getElement().scrollToElement(mLocator.mitraColaborationClass("Gabung sebagai Mitra Pengajar Profesional"));
                getElement().waitUntilClick(mLocator.mitraColaborationClass("Gabung sebagai Mitra Pengajar Profesional"));
                stepsHelper.delay(globalVariable.midDelay);
                getElement().switchWindow();
                getTitle = webDriver.getTitle();
                boolean getTitleTeacherProfessional = getTitle.contains("Kemitraan Individu Karier.mu");
                logAndAssertTrue(getTitleTeacherProfessional, "show form kerjasama");
                break;
            case KATA_MITRA:
                stepsHelper.delay(globalVariable.shortDelay);
                getElement().scrollToElement(mLocator.titleApaKataMitra);
                boolean isShowTitleMitra = getElement().verifyElementPresent(mLocator.titleApaKataMitra);
                logAndAssertTrue(isShowTitleMitra, "show title apa kata mitra");
                getElement().click(mLocator.chevronApaKataMitra);
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.nameCardTitle));
                int getSizeMitra = getElement().getSize(mLocator.nameCardTitle);
                for (int i = 1; i <= getSizeMitra; i++) {
                    String mitraName = getElement().getText(mLocator.mitraName(i));
                    if (mitraName.equalsIgnoreCase("Bagus Sandi Prayogo")) {
                        logPassed("Success move to next slide" +
                                "</br>depend - KMWA-7657: Ensure When User Click Left Or Right Chevron On Section Apa Kata Para Mitra It Will Change Slide");
                        break;
                    }
                }
                getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.mailInfo));
                boolean isShowMailInformation = getElement().verifyElementPresent(mLocator.mailInfo);
                logAndAssertTrue(isShowMailInformation, "show mail information" +
                        "</br>depend - KMWA-7659: Ensure Email Is Clickable And Redirect To Email Page");
                break;
        }
    }

    @Override
    public void validateDownloadAplikasiSection() {
        getElement().scrollToElement(mLocator.titleDownloadAplikasi);
        String getTitleDownloadAplikasi = getElement().getText(mLocator.titleDownloadAplikasi);
        if (getTitleDownloadAplikasi.equalsIgnoreCase("Download aplikasi Karier.mu sekarang!")) {
            logPassed("Successfully show Download Aplikasi section");
        } else {
            logFailed("There is no Download Aplikasi section", null);
        }
        getElement().waitUntil(ExpectedConditions.elementToBeClickable(mLocator.buttonDownloadAplikasi));
        getElement().click(mLocator.buttonDownloadAplikasi);
        String mainWindow = webDriver.getWindowHandle();
        getElement().switchWindow();
        String getUrlPlayStore = webDriver.getCurrentUrl();

        if (getUrlPlayStore.equalsIgnoreCase(mModel.linkPlayStore)) {
            logPassed("Successfully redirect to Play Store");
            webDriver.close();
        } else {
            logFailed("Cannot redirect to Play Store", null);
        }
        webDriver.switchTo().window(mainWindow);
    }

    @Override
    public void tickerAccess() {
        getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.tickerInformation));
        boolean isShowTicker = getElement().verifyElementPresent(mLocator.tickerInformation);
        logAndAssertTrue(isShowTicker, "show ticker on homepage");
        getElement().click(mLocator.buttonDetailTicker);
        getElement().waitUntil(ExpectedConditions.invisibilityOfElementLocated(mLocator.buttonDetailTicker));
        boolean isRedirectToPage = getElement().verifyElementNotPresent(mLocator.buttonDetailTicker);
        logAndAssertTrue(isRedirectToPage, "redirect to another page");
    }

    @Override
    public void seeMoreInstitution() {
        while (getElement().verifyElementPresent(mLocator.buttonSeeMoreMitraPage)) {
            int totalInstitution = getElement().getSize(mLocator.totalInstitution);
            getElement().scrollToElement(mLocator.buttonSeeMoreMitraPage);
            getElement().clickHandlerJs(mLocator.buttonSeeMoreMitraPage);
            getElement().waitUntil(ExpectedConditions.visibilityOfElementLocated(mLocator.getInstitutionCard(totalInstitution + 1)));
            int newTotalInstitution = getElement().getSize(mLocator.totalInstitution);
            boolean isPassed = newTotalInstitution > totalInstitution;
            logAndAssertTrue(isPassed, "button see more disappear when all institution appear" +
                    "</br>depend - KMWA-4500: [Lembaga Page] Structure - Make sure there is no \"Tampilkan Lainnya\" button on the latest Institution Page" +
                    "</br>depend - KMWA-4504: [Lembaga Page] Structure - Make sure there is a list of lembaga section on the latest Halaman Lembaga");
        }
    }

    @Override
    public void searchFavoriteInstitution(HomepageModel homepageModel){
        getElement().waitUntilClick(mLocator.inputSearchInstitution);
        getElement().setText(mLocator.inputSearchInstitution, homepageModel.keywordInstitution);
        getElement().click(mLocator.buttonSearch);
        boolean isInstitutionAppear = getElement().handleElementPresent(mLocator.totalInstitution, 5);
        logAndAssertTrue(isInstitutionAppear, "institution appear");
    }

    @Override
    public void exploreOtherTraining(){
        getElement().waitUntilClick(mLocator.buttonExploreOtherTraining);
        getElement().switchToTab(1);
        String actualSearch = getElement().getAttributeFrom(mLocator.searchBarSrp, "value");
        logAndAssertEqual("kariermuprakerja", actualSearch, "value on searchbar");
    }
}