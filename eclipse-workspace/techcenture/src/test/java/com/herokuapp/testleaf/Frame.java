package com.herokuapp.testleaf;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Common;
import static utils.Common.*;
/**
 * February, 12 2020
 * @author TechCenture
 *
 */
public class Frame {
    /*
     * 3 ways to switch to iframe (nested frame)
     * 1. index. Indexes start with 0
     * 2. name or id attributes of iframe elements
     * 3. WebElement of iframe
     */
    private WebDriver driver;
    
    @BeforeClass
    public void setUp () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        driver.get("http://testleaf.herokuapp.com/frame.html");
    }
    
    /**
     * I am inside a frame
     */
    @Test(priority = 1)
    public void test1 () {
//      \ --> back slash ignores next character (escape character)
        By clickMeButton = By.id("Click");
        
        driver.switchTo().frame(0);
        
        driver.findElement(clickMeButton).click();
        
        Common.sleep(1);
        String text = driver.findElement(clickMeButton).getText();
        Assert.assertEquals(text, "Hurray! You Clicked Me.");
        
        WebElement button = driver.findElement(By.xpath("//button[text()='Hurray! You Clicked Me.']"));
        boolean isButtonDisplayed = button.isDisplayed();
        Assert.assertTrue(isButtonDisplayed, "The button was not displayed!!!");
        
        driver.switchTo().defaultContent();
//      Common.sleep(3);
    }
    
    /**
     * I am inside a nested frame
     */
    @Test(priority = 2)
    public void test2 () {
//      http://testleaf.herokuapp.com/pages/frame.html
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "http://testleaf.herokuapp.com/frame.html");
        System.out.println(actualUrl);
        
        WebElement iFrame2 = driver.findElement(By.xpath("(//iframe)[2]"));
        driver.switchTo().frame(iFrame2);
        
        driver.switchTo().frame("frame2");
        
        driver.findElement(By.id("Click1")).click();
        
        Common.sleep(4);
        
        driver.switchTo().defaultContent();
    }
    
    @Test(priority = 3)
    public void test3 () {
//      driver.switchTo().frame(2);
//      
//      int numberOfFrames = driver.findElements(By.tagName("iframe")).size();
//      
//      System.out.println("numberOfFrames --> " + numberOfFrames);
//      
//      driver.switchTo().defaultContent();
//      
//        numberOfFrames = driver.findElements(By.tagName("iframe")).size();
//      
//      System.out.println("numberOfFrames --> " + numberOfFrames);
        
        int totalNumberOfFrames = 0;
        int numberOfFrames = driver.findElements(By.tagName("iframe")).size();
        
        totalNumberOfFrames += numberOfFrames;
        for ( int index = 0; index < numberOfFrames; index++ ) {
            driver.switchTo().frame(index);
            
            //fix the issue using try catch
            
            int numberOfFrames1 = driver.findElements(By.tagName("iframe")).size();
            totalNumberOfFrames += numberOfFrames1;
            
            if ( numberOfFrames1 > 0 ) {
                
                for ( int index1 = 0; index1 < numberOfFrames1; index1++ ) {
                    driver.switchTo().frame(index1);
                    
                    int numberOfFrames2 = driver.findElements(By.tagName("iframe")).size();
                    totalNumberOfFrames += numberOfFrames2;
                }
            }
            
            driver.switchTo().defaultContent();
        }
        
        System.out.println(totalNumberOfFrames);
    }
    
    @AfterClass
    public void tearDown () {
        driver.quit();
    }
    
}
