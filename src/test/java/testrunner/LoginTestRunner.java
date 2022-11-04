package testrunner;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.util.List;

public class LoginTestRunner extends Setup {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test(priority = 1, description = "Admin logged in successfully")
    public void doLogin() {
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin("admin","admin123");
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "viewEmployeeList";
        System.out.println(urlActual);
        Assert.assertTrue(urlActual.contains(urlExpected));
        Allure.description("Admin logged in successfully");
    }

    @Test(priority = 2, description = "Admin profile image exists or not")
    public void ifProfileImageExists() {
        WebElement imageProfile = loginPage.imgProfile;
        Assert.assertTrue(imageProfile.isDisplayed());
        Allure.description("Admin profile image exists or not");
    }

    @Test(priority = 3, description = "Employee with status check")
    public void selectEmploymentStatus() {
        List<WebElement> dropdowns = driver.findElements(By.className("oxd-select-text-input"));
        dropdowns.get(0).click();
        for (int i = 0; i < 4; i++) {
            dropdowns.get(0).sendKeys(Keys.ARROW_DOWN);
        }
        dropdowns.get(0).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("[type=submit]")).click();
        Allure.description("Data found");
    }

    @Test(priority = 4, description = "Check the selected status")
    public void getEmployeeStatus() {
        Utils.scrollDown(driver);
        WebElement table = driver.findElement(By.className("oxd-table-body"));
        List<WebElement> allRows = table.findElements(By.cssSelector("[role=row]"));

        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.cssSelector("[role=cell]"));
            System.out.println(cells.get(5).getText());
            Assert.assertTrue(cells.get(5).getText().contains("Full-Time Probation"));
        }
        Allure.description("Employee list showing properly");
    }

    @Test(priority = 5)
    public void doLogout(){
       dashboardPage = new DashboardPage(driver);
       dashboardPage.btnProfileImage.click();
       dashboardPage.linkLogout.click();
    }
}
