package com.herokuapp.internet;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Common;
/**
 * 
 * @author TechCenture
 *
 */
public class CheckBox {
    @Test
    public void test1 () {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        
        By checkbox1 = By.xpath("(//form[@id='checkboxes']//input)[1]");
        By checkbox2 = By.xpath("(//form[@id='checkboxes']//input)[2]");
        
        driver.findElement(checkbox1).click();
        
        Common.sleep(1);
        Assert.assertTrue(driver.findElement(checkbox1).isSelected());
        
        driver.findElement(checkbox2).click();
        
        Common.sleep(1);
        Assert.assertFalse(driver.findElement(checkbox2).isSelected());
        
        driver.quit();
    }
    
}
