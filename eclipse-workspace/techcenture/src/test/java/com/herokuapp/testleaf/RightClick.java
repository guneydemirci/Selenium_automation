package com.herokuapp.testleaf;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Common;
import static org.testng.Assert.*;
/**
 * February, 18 2020
 * @author TechCenture
 *
 */
public class RightClick {
    private WebDriver driver;
    private Actions actions;
    
    @BeforeClass
    public void setUp () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        actions = new Actions(driver);
    }
    
    @Test
    public void test1 () {
        driver.get("http://demoqa.com/tooltip-and-double-click/");
        
        WebElement rightClickButton = driver.findElement(By.id("rightClickBtn"));
        
        /*
         * contextClick() --> right click on mouse
         */
//      actions.contextClick(rightClickButton).perform();
//      
//      Common.sleep(1);
//      assertTrue(driver.findElement(By.id("rightclickItem")).isDisplayed());
//      
//      driver.findElement(By.xpath("//div[@id='rightclickItem']//div[text()='Edit this']"))
//          .click();
//      
//      Common.sleep(1);
//      System.out.println(driver.switchTo().alert().getText());
//      
//      driver.switchTo().alert().accept();
        /////////////////////////////////////////////////////////////////////////////////
        String links = "Edit this, Copy Me, Tag, Favourites, Export";
        
        String [] array = links.split(",");
        
        for ( String link : array ) {
            link = link.trim();
            
            actions.contextClick(rightClickButton).perform();
            
            Common.sleep(1);
            assertTrue(driver.findElement(By.id("rightclickItem")).isDisplayed());
            
            driver.findElement(
                    By.xpath("//div[@id='rightclickItem']//div[text()='" + link + "']"))
                        .click();
            
            Common.sleep(1);
            System.out.println(driver.switchTo().alert().getText());
            
            driver.switchTo().alert().accept();
        }
        
        Common.sleep(4);
        
//      org.openqa.selenium.ElementClickInterceptedException: element click intercepted:. Other element would receive the click:
    }
     
    @AfterClass
    public void tearDown () {
        driver.quit();
    }
}

