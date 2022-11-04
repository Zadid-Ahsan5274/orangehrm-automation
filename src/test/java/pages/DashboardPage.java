package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    @FindBy(className = "oxd-userdropdown-img")
    public WebElement btnProfileImage;

    @FindBy(partialLinkText = "Logout")
    public WebElement linkLogout;

    @FindBy(className = "oxd-userdropdown-tab")
    public WebElement btnProfileIcon;

    public DashboardPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

}
