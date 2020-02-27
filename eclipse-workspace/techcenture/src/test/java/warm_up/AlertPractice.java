package warm_up;

import static utils.Common.sleep;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Common;

public class AlertPractice {
	
	 private WebDriver driver;
	    
	    @BeforeClass
	    public void setUp () {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	        
	        driver.get("http://demo.guru99.com/test/delete_customer.php");
	        
	    }
	     
	    	 @Test
	    	    public void test1 () {
	    	        String customerId = String.valueOf(Common.getRandomNumber(1000, 9999));
	    	        driver.findElement(By.name("cusid")).sendKeys(customerId);
	    	        
	    	        driver.findElement(By.name("submit")).click();
	    	        
	    	        String alertText = driver.switchTo().alert().getText();
	    	        Assert.assertEquals(alertText, "Do you really want to delete this Customer?");
	    	        
	    	        driver.switchTo().alert().accept();
	    	        
	    	        Common.sleep(1);
	    	        
	    	        alertText = driver.switchTo().alert().getText();
	    	        Assert.assertEquals(alertText, "Customer Successfully Delete!");
	    	        driver.switchTo().alert().accept();
	    	    }
	    	    
	    	    @Test
	    	    public void test2 () {
	    	        String customerId = String.valueOf(Common.getRandomNumber(1000, 9999));
	    	        driver.findElement(By.name("cusid")).sendKeys(customerId);
	    	        
	    	        driver.findElement(By.name("submit")).click();
	    	        
	    	        String alertText = driver.switchTo().alert().getText();
	    	        Assert.assertEquals(alertText, "Do you really want to delete this Customer?");
	    	        
	    	        driver.switchTo().alert().dismiss();
	    	        
	    	        String actualTextBoxValue = driver.findElement(By.name("cusid")).getAttribute("value");
	    	        Assert.assertEquals(actualTextBoxValue, customerId);
	    	        
	    	        driver.findElement(By.name("res")).click();
	    	        actualTextBoxValue = driver.findElement(By.name("cusid")).getAttribute("value");
	    	        
//	    	      Assert.assertEquals(actualTextBoxValue, "");
	    	        Assert.assertTrue(actualTextBoxValue.isEmpty());
//	    	      Assert.assertTrue(actualTextBoxValue.length() == 0);
	    	    }
	    	    
	    	    @AfterClass
	    	    public void tearDown () {
	    	        driver.quit();
	    	    }
	    	}
    

