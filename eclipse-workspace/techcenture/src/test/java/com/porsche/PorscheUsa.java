package com.porsche;

import static utils.Common.sleep;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Common;

public class PorscheUsa {
	
private WebDriver driver;
    
    @BeforeClass
    public void setUp () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.porsche.com/usa/modelstart/");
    }

    @Test(priority = 1)
    public void login () {
    	
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	
    	driver.findElement (By.cssSelector("[alt='Porsche - Panamera']")).click();
    	Common.sleep(3);
    	jse.executeScript("window.scrollBy(0,1000)");
    	
    	driver.findElement (By.xpath("//*[@id=\\\"m-14-filter-cat-1-content\\\"]/div/div[2]/div[3]/div/div/div[1]/div/div[2]/label[5]\n" + 
    			"\n" + 
    			"")).click();
    	Common.sleep(3);

        
        sleep(3);
}
    
    @AfterClass
    public void tearDown () {
        driver.quit();
    }
    
}

