# orangehrm-automation

##Tools and technologies used
- Java
- Eclipse
- Gradle(Build automation tool)
- Selenium(Web automation tool)
- TestNG(Test framework)

## Scenario of this project
- Admin will login to Orange HRM website
- Admin will check employees list based on status
- Admin will create new employees
- New employee will login to the system using username and password
- New employee will update his/her information from user dashboard

## How to run this project
- clone this project
- open eclipse -> import this project to eclipse
- hit the following commands 
```gradle clean test```
- To generate allure reports, hit the following commands
```allure generate allure-results --clean -o allure-report```
```allure serve allure-results```

## Prerequisites
- JDK installed in your system and set JDK path in environment variable
- Download and set gradle path in your system's environment variable
- Install TestNG plugin in eclipse
- Download and set Allure framework path in your system's environment variable
