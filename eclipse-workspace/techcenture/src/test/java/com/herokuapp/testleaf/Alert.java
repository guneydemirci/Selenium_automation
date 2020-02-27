package com.herokuapp.testleaf;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import static utils.Common.*;
/**
 * February, 11 2020
 * @author TechCenture
 *
 */
public class Alert {
    
//  public static void main(String[] args) {
//      String text = "   ddd   dad       ";
//      System.out.println(text.replaceAll(" ", ""));
//  }
    
    private WebDriver driver;
    
    @BeforeClass
    public void setUp () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        driver.get("http://testleaf.herokuapp.com/pages/Alert.html");
    }
    
    /**
     * Click the button to display a alert box
     */
    @Test(priority = 1)
    public void login () {
        By buttonLocator = By.xpath("//button[text()='Alert Box']");
        
        driver.findElement(buttonLocator).click();
        
        sleep(3);
        
//      org.openqa.selenium.UnhandledAlertException: unexpected alert open: {Alert text : I am an alert box!}
//      driver.findElement(By.xpath("//button[text()='Confirm Box']")).click();
        
        /*
         * In case of no Alert Window is Present, driver.switchTo().alert() action will fail with the
         * org.openqa.selenium.NoAlertPresentException: no such alert --> Exception
         */
        driver.switchTo().alert()
//          accept() --> click 'OK' button
            .accept();
        
        sleep(3);
    }
    
    @Test(priority = 2)
    public void accountPage () {
        driver.findElement(By.xpath("//button[text()='Confirm Box']")).click();
        
        sleep(2);
        
        driver.switchTo().alert().accept();
        String resultText = driver.findElement(By.id("result")).getText();
        System.out.println(resultText);
        Assert.assertEquals(resultText, "You pressed OK!");
        
        driver.findElement(By.xpath("//button[text()='Confirm Box']")).click();
        
        sleep(2);
        driver.switchTo().alert().dismiss();
        
        resultText = driver.findElement(By.id("result")).getText();
        System.out.println(resultText);
        Assert.assertEquals(resultText, "You pressed Cancel!");
        
    }
    
    @Test(priority = 3)
    public void test3 () {
        driver.findElement(By.xpath("//button[text()='Prompt Box']")).click();
        
        sleep(2);
        driver.switchTo().alert().sendKeys("TechCenture Academy");
        
        sleep(2);
        driver.switchTo().alert().accept();
        
        sleep(2);
        
        driver.findElement(By.xpath("//button[text()='Prompt Box']")).click();
        
//      driver.switchTo().alert().sendKeys("TechCenture Academy");
        
        sleep(2);
        driver.switchTo().alert().dismiss();
    }
    
    /**
     * Click the button to learn line-breaks in an alert.
     */
    @Test
    public void test4 () {
        driver.findElement(By.xpath("//button[text()='Line Breaks?']")).click();
        sleep(2);
        
        String alertBoxText = driver.switchTo().alert().getText();
        System.out.println(alertBoxText);
        
        driver.switchTo().alert().accept();
        
        sleep(3);
        
//      System.out.println("Hello\nHow are you doing today?");
        System.out.println(alertBoxText.replace("\n", " "));
    }
    
    @AfterClass
    public void tearDown () {
        driver.quit();
    }
    
}
