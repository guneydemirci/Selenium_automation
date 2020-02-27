package warm_up;

import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import common.Common;
/**
 * January, 14 2020
 * @author TechCenture
 */
public class GoogleSearch {
    public static void main(String[] args) throws InterruptedException {
        String userDirectory = System.getProperty("user.dir");
        System.out.println(userDirectory);
        
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Browser type: ");
        String browserType = scanner.nextLine();
        
        WebDriver driver;
        
        switch ( browserType.toLowerCase() ) {
        case "chrome":
            System.setProperty("webdriver.chrome.driver", 
                    userDirectory + "/src/drivers/chromedriver.exe");
            //open Chrome Browser
            driver = new ChromeDriver();
            break;
        case "firefox":
            System.setProperty("webdriver.gecko.driver", 
                    userDirectory + "/src/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
            break;
        default: System.err.println("Browser type \"" + browserType + "\" undefined"); 
            return;
        }
        
        //navigate to URl
        driver.get("https://google.com");
        
        
        /*
         * Selenium has 8 different types of locators
         * id, name, tagName, className, linkText, partialLinkText
         * cssSelector, xpath
         */
        
        String searchText = "java courses for beginners";
        
        //finding web element and assigning it to WebElement object -> searchBox
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchText);
        
        Common.sleep(1);
        
        WebElement googleSearchButton = 
                driver.findElement(By.name("btnK"));
        googleSearchButton.click();
        
        Common.sleep(1);
        
        String title = driver.getTitle();
        
//      if ( title.equals(searchBox + " - Google Search") )
        if ( title.contains(searchText) ) {
            System.out.println("Test Pass");
        } else {
            System.out.println("Test Fail");
        }
        
        driver.quit();
        scanner.close();
    }
}
