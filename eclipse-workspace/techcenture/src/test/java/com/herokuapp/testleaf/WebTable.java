package com.herokuapp.testleaf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Common;
import static org.testng.Assert.*;
/**
 * February, 10 2020
 * @author TechCenture
 *
 */
public class WebTable {
    
    private String url = "http://testleaf.herokuapp.com/pages/table.html";
    private WebDriver driver;
    
    @BeforeClass
    public void setUp () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        driver.get(url);
    }
    
    @Test//annotation
    public void tableTest1 () {
        String expectedHeaderText = "Toy with Tables";
        String actualHeadetText = driver.findElement(By.tagName("h1")).getText();
        
        assertEquals(actualHeadetText, expectedHeaderText, "Web table header text not validated!!!");
        
        //table > tr (table row) > th (table header)
        //table > tr (table row) > td (table data)
        
        int numberOfColumns = driver.findElements(By.xpath("//table//tr//th")).size();
        assertEquals(numberOfColumns, 3, "number of columns not validated!!!");
        
        int numberOfRows = driver.findElements(By.cssSelector("table tr")).size();
        System.out.println(numberOfRows);
        
        assertEquals(numberOfRows, 4, "number of rows not validated!!!");
        
//      Common.sleep(2);
    }
    
    /**
     * Get the progress value of 'Learn to interact with Elements'
     */
    @Test
    public void tableTest2 () {
        List<WebElement> listOfRows = driver.findElements(By.cssSelector("table tr"));
        
        for ( int index = 0; index < listOfRows.size(); index++ ) {
            //(//table//tr)[" + index + "]//td[1]
            
            int rowIndex = index + 1;
            
            if ( rowIndex == 1 )
                continue;
            
            String text = driver.findElement(By.xpath("(//table//tr)[" + rowIndex + "]//td[1]")).getText();
            
//          try {
//              text = driver.findElement(By.xpath("(//table//tr)[" + rowIndex + "]//td[1]")).getText();
//          } catch ( NoSuchElementException e ) {
//              System.out.println(e.getMessage());
//          }
            
            System.out.println(text);
            
            if ( text.equals("Learn to interact with Elements") ) {
                String progressValue = driver.findElement(By.xpath("(//table//tr)[" + rowIndex + "]//td[2]")).getText();
                System.out.println("Progress Value of " + text + " --> " + progressValue);
            }
        }
    }
    
    /**
     * Check the vital task for the least completed progress.
     */
    @Test
    public void tableTest3 () {
        List<WebElement> listOfRows = driver.findElements(By.cssSelector("table tr"));
        List<Integer> numbers = new ArrayList<>();
        int minNumber = 0;
        int maxNumber = 0;
        
        for ( int index = 0; index < listOfRows.size(); index++ ) {
            int rowIndex = index + 1;
            if ( rowIndex == 1 )
                continue;
            
            String learningDetails = driver.findElement(By.xpath("(//table//tr)[" + rowIndex + "]//td[1]")).getText();
            String progressValue = driver.findElement(By.xpath("(//table//tr)[" + rowIndex + "]//td[2]")).getText();
            
            System.out.println("Learning Details --> " + learningDetails + ", Progress Value --> " + progressValue);
            
            int progressValueNumber = Integer.parseInt(progressValue.replace("%", ""));
            numbers.add(progressValueNumber);
        }
        
        minNumber = getMinNumber(numbers);
        maxNumber = getMaxNumber(numbers);
//      System.out.println(numbers.toString());
//      System.out.println("min number is " + minNumber);
        
        WebElement checkbox = driver.findElement(By.xpath("//table//tr//td[.='" + minNumber + "%']/following-sibling::td/input"));
        
        checkbox.click();
        boolean isSelected = checkbox.isSelected();
        assertTrue(isSelected, "is not selected!!!"); 
        
        Common.sleep(2);
        
        checkbox = driver.findElement(By.xpath("//table//tr//td[.='" + maxNumber + "%']/following-sibling::td/input"));
        //table//tr//td[contains(.,'100%')]/following-sibling::td/input
        checkbox.click();
        isSelected = checkbox.isSelected();
        assertTrue(isSelected, "is not selected!!!"); 
        
        Common.sleep(5);
    }
    
    private int getMinNumber ( List<Integer> numbers ) {
        int minNumber = 0;
        
        for ( int index = 0; index < numbers.size() - 1; index++ ) {
//          if ( numbers.get(index) < numbers.get(index + 1) )
//              minNumber = numbers.get(index);
//          else minNumber = numbers.get(index + 1);
            
            minNumber = numbers.get(index) < numbers.get(index + 1) ?
                    numbers.get(index) : numbers.get(index + 1);
        }
        return minNumber;
    }
    
    private int getMaxNumber ( List<Integer> numbers ) {
        int maxNumber = 0;
        
        for ( int index = 0; index < numbers.size() - 1; index++ ) {
            int number = numbers.get(index) > numbers.get(index + 1) ?
                    numbers.get(index) : numbers.get(index + 1);
                    
            maxNumber = maxNumber > number ? maxNumber : number;
        }
                    
        
        return maxNumber;
    }
    @AfterClass
    public void tearDown () {
        driver.quit();
        driver = null;
    }
    
}

