package com.herokuapp.testleaf;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Common;
//wildcard import of static members from class Assert from org.testng package
import static org.testng.Assert.*;
/**
 * February, 3 2020
 * 
 * @author TechCenture
 *
 */
public class Checkbox {
    // JUnit and TestNG as Unit testing tools
    private WebDriver driver;
    
    @BeforeTest
    public void setUp () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    
    /**
     * Select the languages that you know?
     */
    @Test
    public void checkboxTest1() {
        List<String> programmingLanguages = new ArrayList<>();
//      Scanner scanner = new Scanner(System.in);
        System.out.println("How many Programming Langugaes do you know?");
//      int numberOfLanguages = scanner.nextInt();
        
        System.out.println("Enter the languages that you know?");
        programmingLanguages.add("Java");
//      for (int index = 0; index < numberOfLanguages; index++) {
//          programmingLanguages.add(scanner.next());
//      }
//      String answer = scanner.nextLine();
        driver.get("http://testleaf.herokuapp.com/pages/checkbox.html");
        By headerLocator = By.tagName("h1");
        By javaCheckboxLocator = By.xpath("(//label[text()='Select the languages that you know?']/..//input)[1]");
        By vbCheckboxLocator = By.xpath("(//label[text()='Select the languages that you know?']/..//input)[2]");
        By sqlCheckboxLocator = By.xpath("(//label[text()='Select the languages that you know?']/..//input)[3]");
        By cCheckboxLocator = By.xpath("(//label[text()='Select the languages that you know?']/..//input)[4]");
        By cPlusCheckboxLocator = By.xpath("(//label[text()='Select the languages that you know?']/..//input)[5]");
        String headerText = driver.findElement(headerLocator).getText();
//      if ( !headerText.equals("Interact with Checkboxes1") )
//          System.out.println("Header is not validated");
        assertEquals(headerText, "Interact with Checkboxes");
        Common.sleep(2);
        for (String answer : programmingLanguages) {
            switch (answer) {
            case "Java":
                driver.findElement(javaCheckboxLocator).click();
                break;
            case "VB":
                driver.findElement(vbCheckboxLocator).click();
                break;
            case "SQL":
                driver.findElement(sqlCheckboxLocator).click();
                break;
            case "C":
                driver.findElement(cCheckboxLocator).click();
                break;
            case "C++":
                driver.findElement(cPlusCheckboxLocator).click();
                break;
            default:
                fail("Answer " + answer + " undefined!");
                break;
            }
        }
        Common.sleep(4);
//      driver.quit();
//      scanner.close();
    }
    /**
     * Confirm Selenium is checked
     */
    @Test
    public void checkboxTest2() {
//      driver.get("http://testleaf.herokuapp.com");
        By seleniumCheckbox =
                By.xpath("//label[text()='Confirm Selenium is checked']/following-sibling::input");
        
        boolean isSelected = driver.findElement(seleniumCheckbox).isSelected();
//      assertEquals(isSelected, true);
        assertTrue(isSelected, "Selenium checkbox was not selected!");
    }
    
    /**
     * DeSelect only checked
     */
    @Test
    public void checkboxTest3 () {
        
        String checkbox = "//label[text()='DeSelect only checked']/following-sibling::input";
        
        List<WebElement> listOfCheckboxes = driver.findElements(By.xpath(checkbox));
        System.out.println(listOfCheckboxes.size());
        
        for ( WebElement checkboxElement : listOfCheckboxes ) {
            boolean isSelected = checkboxElement.isSelected();
            
            if ( isSelected ) {
                checkboxElement.click();
                
                isSelected = checkboxElement.isSelected();
                assertFalse(isSelected);
            }
        }
        Common.sleep(2);
    }
    
    /**
     * Select all below checkboxes
     */
    @Test
    public void checkboxTest4 () {
        By checkboxes = By.xpath("//label[contains(text(), 'Select all below checkboxes')]/following-sibling::input");
        List<WebElement> listOfCheckboxes = driver.findElements(checkboxes);
        
        for ( int index = 0; index < listOfCheckboxes.size(); index++ )
            listOfCheckboxes.get(index).click();
        
        Common.sleep(2);
    }
    
    @AfterTest
    public void tearDown () {
        /*
         * String num1 = "1"; int num2 = 1;
         * 
         * System.out.println(num1); System.out.println(num2);
         * 
         * assertEquals(num1, num2, "Data type is diffrenet");
         */
        
        driver.quit();
    }
    
}
