# Website & Dashboard — Automation Testing Suite

![Java](https://img.shields.io/badge/Java-11-orange?logo=java)
![Selenium](https://img.shields.io/badge/Selenium-1.44.0-2EAD33?logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-7.9.0-FF6C37)
![Allure](https://img.shields.io/badge/Allure-2.27.0-blue)

End-to-end automation test suite covering **Website** and **Dashboard** modules — UI functional testing, API integration testing, and database validation across 7 feature modules.

---

## Table of Contents
- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Modules Covered](#modules-covered)
- [Getting Started](#getting-started)
- [Running Tests](#running-tests)

---

## Overview

This project automates regression testing for multiple platform modules:

- **UI Testing** — browser automation using Selenium Java with Page Object Model
- **API Testing** — REST API validation integrated within test lifecycle
- **Database Validation** — direct DB queries to verify data integrity (MySQL)

Test cases cover CRUD flows, form validation, search & filter, payment integration, and authentication scenarios (OTP, PIN, password change).

---

## Tech Stack

| Layer | Tool | Version |
|---|---|---|
| Browser Automation | Microsoft Selenium | 4.18.1 |
| Test Framework | TestNG | 7.9.0 |
| Reporting | Allure Report | 2.27.0 |
| Build Tool | Maven | 3.x |
| Language | Java | 11 |
| DB Validation | MySQL (via JDBC) | — |
| Logging | SLF4J + Logback | — |

---

## Architecture

```
┌─────────────────────────────────┐
│         Test Class              │  ← @Test methods
├─────────────────────────────────┤
│      Step Interface             │  ← Action contracts
├─────────────────────────────────┤
│         Page Class              │  ← Selenium implementation
├─────────────────────────────────┤
│    Locator  │  Model  │  DB     │  ← Selectors │ Data │ DB helpers
└─────────────────────────────────┘
```

---

## Project Structure

```
src/main/java/automation/pages/website/
├── achievementprofile/         # Achievement Profile UI
├── homepage/
│   ├── kariermu/               # Kariermu homepage + SRP + B2B leads form
│   ├── prakerja/               # Prakerja homepage
│   ├── homelxp/                # HomeLXP
│   ├── navigationbar/          # Navigation bar
│   └── popupwhatsapp/          # WhatsApp popup + API (create/enroll user, OTP)
├── manageprofile/
│   ├── passwordsetting/        # Password change flows
│   ├── pinsetting/             # PIN setup/change/freeze
│   ├── workplacedata/          # Workplace data edit
│   └── remote/database/        # DB helpers for profile verification
├── mediaandadvertising/
│   └── manageprogramsandinstitutions/
│       ├── kariermu/           # Kariermu ads management
│       ├── prakerja/           # Prakerja ads management
│       └── database/           # DB helpers for program validation
├── paymentmethodprakerja/      # Prakerja payment method (Prakerja ID, OTP)
├── platinuminstitute/          # Platinum Institute page
└── srp/                        # Search Results Page
```

---

## Modules Covered

| Module | UI Tests | API | DB Validation | Key Scenarios |
|---|:---:|:---:|:---:|---|
| Achievement Profile | ✅ | — | — | View achievement badges & certificates |
| Homepage (Kariermu) | ✅ | — | — | Search, filter, B2B leads form, SRP |
| Homepage (Prakerja) | ✅ | — | — | Banner, ads, program categories |
| PopupWhatsapp | ✅ | ✅ | — | Create user, enroll, verify OTP |
| Manage Profile | ✅ | — | ✅ | Edit profile, change password, PIN |
| Media & Advertising | ✅ | — | ✅ | Ads management Kariermu & Prakerja |
| Payment Prakerja | ✅ | — | — | Prakerja ID input, OTP verification |
| Platinum Institute | ✅ | — | — | Institution page flows |
| SRP | ✅ | — | — | Search results, filters, sorting |

---

## Getting Started

### Prerequisites
- Java 11+
- Maven 3.8+
- MySQL (for DB validation tests)

### Installation

```bash
git clone https://github.com/your-username/website-automation.git
cd website-automation

# Install Selenium browsers
mvn exec:java -e -D exec.mainClass=com.microsoft.selenium.CLI -D exec.args="install chromium"

# Configure environment
cp .env.example .env
# Edit .env with your values
```

### Environment Variables

| Variable | Description |
|---|---|
| `BASE_URL` | Application base URL |
| `TEST_USER_EMAIL` | Test user email |
| `TEST_USER_PASSWORD` | Test user password |
| `TEST_OLD_PASSWORD` | Current password for change-password tests |
| `TEST_NEW_PIN` | New PIN for PIN tests |
| `TEST_OLD_PIN` | Current PIN for PIN change tests |
| `TEST_PRAKERJA_ID` | Valid Prakerja card ID (16 digits) |
| `TEST_WHATSAPP_NUMBER` | Valid WA number for form tests |
| `QA_ADMIN_EMAIL` | Admin email for OTP verification |
| `TEST_DEVICE_ID` | Device token for API auth |
| `DB_HOST` | MySQL host |
| `DB_PORT` | MySQL port |
| `DB_USER` | MySQL username |
| `DB_PASSWORD` | MySQL password |

---

## Running Tests

```bash
# Full suite
mvn test -DsuiteFile=testng.xml

# Specific module
mvn test -DsuiteFile=testng.xml -Dgroups="Manage Profile"

# Headed mode
HEADLESS=false mvn test -DsuiteFile=testng.xml

# Generate Allure report
mvn allure:serve
```
