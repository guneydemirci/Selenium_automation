package com.herokuapp.testleaf;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Common;
/**
 * February, 18 2020
 * @author TechCenture
 *
 */
public class HoverOver {
    private WebDriver driver;
    
    @BeforeClass
    public void setUp () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        
    }
    
    @Test
    public void test1 () {
        driver.get("http://www.leafground.com/pages/drop.html");
        Common.sleep(2);
        
        Actions actions = new Actions(driver);
        
        WebElement sourceElement = driver.findElement(By.id("draggable"));
        WebElement targetElement = driver.findElement(By.id("droppable"));
        
        actions.dragAndDrop(sourceElement, targetElement).perform();
        
        Assert.assertEquals(targetElement.getText(), "Dropped!");
        
//      Common.sleep(5);
    }
    
    @Test
    public void test2 () {
        Actions actions = new Actions(driver);
        
        driver.get("http://demo.guru99.com/test/drag_drop.html");
        
        WebElement from1 = driver.findElement(By.id("credit2"));
        WebElement to1 = driver.findElement(By.id("bank"));
        
        WebElement from2 = driver.findElement(By.id("fourth"));
        WebElement to2 = driver.findElement(By.id("amt7"));
        
        WebElement from3 = driver.findElement(By.id("credit1"));
        WebElement to3 = driver.findElement(By.id("loan"));
        
        WebElement from4 = driver.findElement(By.xpath("(//li[@id='fourth'])[2]"));
        WebElement to4 = driver.findElement(By.id("amt8"));
        
        actions.dragAndDrop(from1, to1).perform();
        Common.sleep(1);
        
        actions.dragAndDrop(from2, to2).perform();
        Common.sleep(1);
        
        actions.dragAndDrop(from3, to3).perform();
        Common.sleep(1);
        
        actions.dragAndDrop(from4, to4).perform();
        Common.sleep(1);
        
        WebElement perfectButton = driver.findElement(By.xpath("//div[@id='equal']/a[text()='Perfect!']"));
        boolean isDisplayed = perfectButton.isDisplayed();
        
        Assert.assertTrue(isDisplayed, "The 'Perfect!' button is not displayed!");
    } 
     
    @AfterClass
    public void tearDown () {
        driver.quit();
    }
}

