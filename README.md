# Test Automation Framework

## Description
This is a test automation framework for the **Hardcore** task from the WebDriver course. It supports multiple browsers, logging, and flexible configurations for different environments.

## Features
- **WebDriverManager** for managing browser drivers.
- **Page Object Model (PageFactory)** for page abstraction.
- **Business Objects** for core entities.
- **Property files** for different test environments (at least 2).
- **XML Test Suites** for Smoke tests.
- **Failure Screenshots** with log info about saved files.
- **Flexible Configuration** for browser and environment.
- **Logging (Log4j)** with different levels (INFO, ERROR).
- **Log Output** in both console and daily log files.
- **Test Results Visualization**.

## Run Tests

Run all tests:
```sh
mvn clean test
```
Run with a specific browser:

```sh
mvn -Dbrowser=firefox clean test
```
Run for a specific environment:
```sh
mvn -Denv=qa clean test
```
