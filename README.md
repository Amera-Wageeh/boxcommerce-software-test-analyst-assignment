# BoxCommerce – Software Test Analyst Automation Framework

This repository contains an **automation framework** built to test the BoxCommerce platform sign-up and onboarding flow.  
It is structured for **scalability** and will serve as a base to expand into additional regression and functional coverage.

---

## 1) Framework, Tools, and Libraries Used (and Why)

- **Java 17** – modern, strongly-typed language, widely adopted for test automation.  
- **Maven** – dependency management & build tool.  
- **Selenium 4** – browser automation library, industry-standard for UI testing.  
- **TestNG** – testing framework supporting annotations, test suites, parallel runs, and reporting.  
- **WebDriverManager** – handles browser driver setup automatically (no manual driver downloads).  
- **Allure TestNG** – generates rich test execution reports with steps, screenshots, and metadata.  
- **Custom Utilities (`TestDataGenerator`)** – generates random runtime test data (emails, UAE-compliant phone numbers) to avoid collisions and hardcoded values.

---

## 2) Project Structure

```
boxcommerce-automation/
├── pom.xml
├── testng.xml
├── README.md
├── .github/workflows/ci.yml
├── src/
│   ├── main/java/com/boxcommerce/pages/
│   │   ├── BasePage.java
│   │   └── SignUpPage.java
│   ├── main/java/com/boxcommerce/utils/
│   │   └── TestDataGenerator.java   # Random email & UAE phone generator
│   └── test/java/com/boxcommerce/tests/
│       └── SignUpTest.java
└── docs/
    ├── example-report.png
    └── Manual_Test_Cases.md
```

---

## 3) Running Tests Locally

```bash
# Prereqs: Java 17, Maven
mvn -v

# Run the TestNG suite
mvn clean test

# Run in headless mode
HEADLESS=true mvn clean test
```

---

## 4) Test Data Strategy

Dynamic test data is generated at runtime:  
- **Email** → unique email using timestamp (`user1725678123456@example.com`)  
- **Phone** → UAE-compliant 9-digit mobile number (prefix 50, 52, 54, 55, 56, 58)  

```java
String email = TestDataGenerator.generateEmail();
String phone = TestDataGenerator.generatePhoneNumberUAE();
```

---

## 5) Viewing Results

- **Surefire reports**: located at `target/surefire-reports`  
- **Allure reports** (recommended):  

```bash
mvn allure:report
mvn allure:serve
```

This generates a **rich HTML dashboard** with pass/fail, test steps, and logs.


---

## 6) Domain & EFT

My **Usersite domain** (enabled for EFT) is:  
`http://ameratesting.uat.boxcommerce.dev/`  

This domain is used in checkout scenarios to simulate full product purchase flows, including EFT payments.

---

## 7) Next Steps

- Expand Page Objects for additional onboarding and dashboard flows.  
- Add assertions for domain setup, store configuration, and product/catalog management.  
- Add regression test coverage for Usersite checkout with EFT.  

---

© 2025 Candidate Demo
