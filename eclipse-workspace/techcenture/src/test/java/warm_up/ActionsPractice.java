package warm_up;

import java.util.List;
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

public class ActionsPractice {
	
		private WebDriver driver;
    
		@BeforeClass
    	public void setUp () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        driver.get("http://testleaf.herokuapp.com/home.html");
    }

			@Test
			public void test1() {
		        Actions action = new Actions(driver);
		        
		        List<WebElement> listOfPages = driver.findElements(By.cssSelector(".wp-categories-list li"));
		        
		        for ( WebElement page : listOfPages ) {
		            Common.sleep(2);
		            action.moveToElement(page).perform();
		        }
		        
		        Common.sleep(5);
		    }

			
		@AfterClass
	    public void tearDown () {
		driver.quit();
	    	    }	
			
}
