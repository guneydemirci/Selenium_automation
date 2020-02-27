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
public class DoubleClick {
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
        driver.get("http://cookbook.seleniumacademy.com/DoubleClickDemo.html");
        
//      Actions actions = new Actions(driver);
        
        WebElement boxElement = driver.findElement(By.id("message"));
        String color = boxElement.getCssValue("background");
        System.out.println(color);
        
        actions.doubleClick(boxElement).perform();
        
        color = boxElement.getCssValue("background");
        System.out.println(color);
    }
    
    @Test
    public void test2 () {
        driver.get("http://demo.guru99.com/test/simple_context_menu.html");
        
        WebElement button = driver.findElement(
                By.xpath("//button[text()='Double-Click Me To See Alert']"));
        
        actions.doubleClick(button).perform();
        
        Common.sleep(1);
        String alertText = driver.switchTo().alert().getText();
        
        Assert.assertEquals(alertText, "You double clicked me.. Thank You..");
        
        Common.sleep(1);
        driver.switchTo().alert().accept();
        
        Common.sleep(1);
    } 
    
    @Test
    public void test3 () {
        driver.get("http://demoqa.com/tooltip-and-double-click/");
        
        actions.doubleClick(driver.findElement(By.id("doubleClickBtn"))).perform();
        
        System.out.println(driver.switchTo().alert().getText());
        
        driver.switchTo().alert().accept();
    }
    /**
     * Go to http://api.jquery.com/dblclick/
     * Get the color code from the Box Element
     * Double Click on it
     * Get the color code from the Box Element again
     * Validate (assert) Color code before click is not equals to Color Code After Click
     */
    @Test
    public void test4 () {
        driver.get("http://api.jquery.com/dblclick/");
        
        driver.switchTo().frame(0); 
        
        WebElement boxElement = driver.findElement(
                By.xpath("//span[text()='Double click the block']/preceding-sibling::div"));
        
        String colorCode1 = boxElement.getCssValue("background");
        System.out.println(colorCode1);
        
        actions.doubleClick(boxElement).perform();
        
        String colorCode2 = boxElement.getCssValue("background");
        System.out.println(colorCode2);
        
        driver.switchTo().defaultContent();
        
//      Assert.assertTrue(!colorCode1.equals(colorCode2));
        Assert.assertFalse(colorCode1.equals(colorCode2));
        System.out.println("Is Color Code Same? " + colorCode1.equals(colorCode2));
    }
    
    @AfterClass
    public void tearDown () {
        driver.quit();
    }
}

