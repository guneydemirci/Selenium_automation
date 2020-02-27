package warm_up;

//java.lang.*; imported by default
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Common;
/**
* February, 11 2020
* @author TechCenture
*
*/
public class RandomEmailGenerator {
  private WebDriver driver;
  
  @BeforeClass
  public void setUp () {
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
      
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
      
      driver.get("https://gist.github.com/tbrianjones/5992856/");
  }
  
  @Test
  public void getEmails () {
      By emailTextLocator = By.xpath("((//table)[1]//tr)//td[2]");
      
      int numberOfEmailElements = driver.findElements(emailTextLocator).size();
      System.out.println("bobomurad.kamalov@" + driver.findElement(
              By.xpath("(((//table)[1]//tr)//td[2])[" + Common.getRandomNumber(1, numberOfEmailElements) + "]"))
                  .getText());
  }
  
  @Test
  public void storeAllEmails () {
      List<String> listOfEmails = new ArrayList<>();
      List<WebElement> listOfEmailElements = driver.findElements(By.xpath("((//table)[1]//tr)//td[2]"));
      
      for ( WebElement emailElement : listOfEmailElements ) {
          listOfEmails.add(emailElement.getText());
      }
      
      System.out.println(listOfEmails);
      
      String fName = "Ali";
      String lName = "Oz";
      String email = fName + lName + "@" + listOfEmails
              .get( Common.getRandomNumber(0, listOfEmails.size() - 1) );
      System.out.println(email.toLowerCase());
  }
  
  @AfterClass
  public void tearDown () {
      driver.quit();
  }
}