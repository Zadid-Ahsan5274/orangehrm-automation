package testrunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.EmployeePage;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class EmployeeTestRunner extends Setup {

    @BeforeTest
    public void doLogin(){
        driver.get("https://opensource-demo.orangehrmlive.com/");
        LoginPage loginPage = new LoginPage(driver);
        String adminUser = "admin";
        String adminPass = "admin123";
        loginPage.doLogin(adminUser,adminPass);
    }

    @Test(priority = 1, description = "Check if user exists", enabled = false)
    public void checkIfUserExists() throws ParseException, IOException {
        EmployeePage employeePage = new EmployeePage(driver);
        employeePage.btnAddEmployee.get(2).click();
        employeePage.toggleButton.click();
        List data = Utils.readJSONArray("./src/test/resources/Users.json");
        JSONObject userObj = (JSONObject) data.get(data.size()-1);
        String existingUserName = (String) userObj.get("userName");
        String validationErrorMessageActual = employeePage.checkIfUserExists(existingUserName);
        String validationErrorMessageExpected = "Username already exists";
        Assert.assertTrue(validationErrorMessageActual.contains(validationErrorMessageExpected));

    }

    @Test(priority = 1, description = "Create new employee")
    public void createEmployee() throws InterruptedException, IOException, ParseException {
        EmployeePage employeePage = new EmployeePage(driver);
        employeePage.btnAddEmployee.get(2).click();
        employeePage.toggleButton.click();
        Utils utils = new Utils();
        utils.generateRandomData();
        String firstName = utils.getFirstname();
        String lastName = utils.getLastname();
        String userName = utils.getUsername();
        // int randomId = Utils.generateRandomNumber(1000,9999);
        // String userName = utils.getFirstname()+randomId;
        String password = "P@word123";
        String confirmPassword = password;
        employeePage.txtUserCreds.get(5).clear();
        employeePage.createEmployee(firstName,lastName,userName,password,confirmPassword);
        Thread.sleep(10000);
        List <WebElement> headerTitle = driver.findElements(By.className("orangehrm-main-title"));
        Assert.assertTrue(headerTitle.get(0).isDisplayed());
        Utils.waitForElement(driver,headerTitle.get(0),50);
        if(headerTitle.get(0).isDisplayed()){
            utils.saveJsonList(userName,password);
        }
    }

}
