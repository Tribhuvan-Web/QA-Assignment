# QA Assignment - Janitri Login Page Automation

## Project Overview
This project is a Selenium-based automation testing framework for testing the login functionality of the Janitri Dashboard. It implements the Page Object Model (POM) design pattern as per the QA Internship Assignment requirements.

**Application Under Test**: [Janitri Dashboard](https://dev-dash.janitri.in/)

## Assignment Requirements Implementation

### Task 1: Manual Test Cases ✅
- Comprehensive test cases covering positive, negative, UI, and boundary scenarios
- Format: Excel (.xlsx) file

### Task 2: Automation Framework ✅
- Java + Selenium WebDriver + Page Object Model
- TestNG as test runner
- Maven for dependency management
- BaseTest class for browser setup/teardown

## Tech Stack
- **Java 23**: Programming language
- **Selenium WebDriver 4.15.0**: Web automation framework
- **TestNG 7.8.0**: Testing framework
- **Maven**: Build and dependency management
- **Microsoft Edge**: Target browser for testing
- **Logback**: Logging framework

## Test Methods Implementation (As Per Assignment)

### Required Test Methods ✅

1. **`testLoginButtonDisabledWhenFieldAreEmpty()`**
   - Attempts login with blank fields and verifies UI behavior
   - Validates that login button is disabled when both fields are empty
   - Tests blank fields UI behavior

2. **`testPasswordMaskedButton()`**
   - Validates password masking/unmasking toggle functionality
   - Tests eye icon click behavior (if available)
   - Verifies password field is masked by default

3. **`testInvalidLoginShowErrorMsg()`**
   - Enters random credentials and clicks login
   - Captures and prints any error message shown
   - Validates error message display functionality

### Test Scenarios Automated (Without Valid Credentials)

✅ **Attempt login with blank fields and verify UI behavior**
- Implemented in `testLoginButtonDisabledWhenFieldAreEmpty()`
- Verifies login button state when fields are empty

✅ **Enter any random credentials and click login – capture and print any error message shown**
- Implemented in `testInvalidLoginShowErrorMsg()`
- Uses invalid credentials: "invalid@email.com" / "wrongPassword"
- Captures and prints error messages to console

✅ **Validate password masking/unmasking toggle**
- Implemented in `testPasswordMaskedButton()`
- Tests password field masking by default
- Tests eye icon toggle functionality (when available)

✅ **Validate presence of page elements (e.g., title, input fields, eye icon)**
- Validates email input field presence
- Validates password input field presence
- Validates login button presence
- Checks page title
- Checks eye icon presence (optional element)

## Page Object Model Structure

### LoginPage.java Elements
- **Email input**: `By.name("email")`
- **Password input**: `By.name("password")`
- **Login button**: Multiple selectors for compatibility
- **Password visibility toggle**: Multiple selectors for eye icon
- **Error message**: Comprehensive error message selectors

### LoginPage.java Methods
- `enterEmail(String email)`: Enter email in email field
- `enterPassword(String password)`: Enter password with fallback mechanisms
- `clickLogin()`: Click login button
- `togglePasswordVisibility()`: Toggle password visibility (if eye icon available)
- `isLoginButtonEnabled()`: Check login button state with smart logic
- `isMaskedPassword()`: Check password masking state
- `isErrorMessageDisplayed()`: Check for error messages with fallback detection
- `getErrorMessage()`: Capture error message text
- `areFieldsEmpty()`: Verify if fields are empty
- `clearEmailField()` / `clearPasswordField()`: Clear input fields
- Element validation methods for presence checking

## Prerequisites
- Java 23 or higher
- Maven 3.6 or higher
- Microsoft Edge browser
- EdgeDriver executable

## Setup Instructions

### 1. Clone the Repository
```bash
git clone <repository-url>
cd QA-Assignment
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Configure WebDriver
- Download Microsoft EdgeDriver from [Microsoft Edge Developer site](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)
- Update the driver path in `BaseTest.java`:
```java
System.setProperty("webdriver.edge.driver", "E:/msedgedriver.exe");
```

### 4. Notification Permission Handling ✅
The framework automatically handles:
- Browser notification permission dialogs
- Page refresh after permission handling
- Wait mechanisms for page stability
- Clean state before each test execution

### 5. Target Application URL
The tests are configured to run against:
```
https://dev-dash.janitri.in/
```

## Key Features

### Notification Permission Handling
- **Automatic handling**: Browser options disable notifications by default
- **Fallback mechanism**: Handles any remaining permission dialogs
- **Page refresh**: Ensures clean state after permission handling
- **Explicit waits**: WebDriverWait for element stability

### Test Reliability Features
- **Page refresh before each test**: Ensures clean state
- **Explicit waits**: All interactions use WebDriverWait
- **Error handling**: Graceful handling of missing elements
- **Console logging**: Clear test execution feedback

## Running Tests

### Run All Tests (Recommended)
```bash
mvn test
```

### Run with TestNG XML
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run Specific Test Class
```bash
mvn test -Dtest=LoginTest
```

### Run Specific Test Method
```bash
mvn test -Dtest=LoginTest#testLoginButtonDisabledWhenFieldsAreEmpty
```

### Important Notes for Test Execution
- Tests automatically handle notification permissions
- Each test refreshes the page for clean state
- Console output shows test progress and results
- If tests fail due to permission dialogs, the framework will retry

## Test Cases

| Test Case | Description | Expected Result |
|-----------|-------------|-----------------|
| `testLoginButtonDisabledWhenFieldAreEmpty()` | Attempt login with blank fields and verify UI behavior | Login button should be disabled when fields are empty |
| `testPasswordMaskedButton()` | Validate password masking/unmasking toggle functionality | Password should be masked by default, eye icon toggle should work (if available) |
| `testInvalidLoginShowErrorMsg()` | Enter random credentials, click login, capture error message | Error message should be displayed and printed to console |

## Dependencies

### Production Dependencies
- **Selenium Java** (4.15.0): Web automation
- **Logback Classic** (1.4.14): Logging framework

### Test Dependencies
- **TestNG** (7.8.0): Testing framework

## Browser Configuration
The framework is configured to use Microsoft Edge with the following options:
- Disable notifications (`--disable-notification`)

## Test Reports
TestNG generates test reports in the `target/surefire-reports` directory after test execution.

## Best Practices Implemented
1. **Page Object Model**: Separation of page elements and test logic
2. **Base Test Class**: Common setup and teardown methods
3. **Explicit Waits**: Though not implemented yet, recommended for production
4. **Clear Method Names**: Descriptive test and method names
5. **Assertions**: Proper validation using TestNG assertions

## Future Enhancements
- Add explicit waits for better synchronization
- Implement data-driven testing with external data sources
- Add screenshot capture on test failures
- Implement parallel test execution
- Add cross-browser testing capabilities
- Integrate with CI/CD pipelines

## Troubleshooting

### Common Issues
1. **WebDriver Not Found**: Ensure EdgeDriver path is correct in `BaseTest.java`
2. **Element Not Found**: Verify locators match the actual web elements
3. **Test Failures**: Check if the target application is accessible

### Debug Tips
- Use browser developer tools to inspect elements
- Add logging statements for debugging
- Use breakpoints in IDE for step-by-step debugging

## Contributing
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add/update tests as needed
5. Submit a pull request

## Contact
For any questions or issues, please contact the development team.

---

**Note**: This project is part of the Janitri QA Internship Assignment and is intended for educational and testing purposes.