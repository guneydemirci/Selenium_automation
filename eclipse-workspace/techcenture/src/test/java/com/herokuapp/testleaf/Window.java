package com.herokuapp.testleaf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Common;
import static org.testng.Assert.*;
/**
 * February, 19 2020
 * @author TechCenture
 *
 */
public class Window {
    private WebDriver driver;
    
    @BeforeClass
    public void setUp () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        driver.get("http://testleaf.herokuapp.com/pages/Window.html");
    }
    
    /**
     * Click button to open home page in New Window
     */
    @Test
    public void test1 () {
        //click on button
        driver.findElement(By.id("home")).click();
        
//      List vs Set
//      List<String> list = new ArrayList<String>();
//      list.add("hello");
//      list.add("hello");
//      System.out.println(list.toString());
//      
//      Set<String> set = new HashSet<>();
//      set.add("hello");
//      set.add("hello");
//      System.out.println(set.toString());
        
        Set<String> windows = driver.getWindowHandles();
        System.out.println(windows);
        
        assertTrue(windows.size() == 2);
        
        for ( String window : windows ) {
            driver.switchTo().window(window);
            
            Common.sleep(1);
            String url = driver.getCurrentUrl();
            
            if ( url.contains("home.html") ) {
                String title = driver.getTitle();
                String header = driver.findElement(By.tagName("h1")).getText();
                System.out.println(url);
                System.out.println(title);
                System.out.println(header);
                
                assertTrue(url.contains("home.html"));
                assertTrue(title.contains("TestLeaf - Selenium Playground"));
                assertTrue(header.contains("Locators and Selenium Interactions"));
                
//              System.out.println(driver.getWindowHandle());
//              System.out.println(window);
//              System.out.println(driver.getWindowHandle().equals(window));
//              driver.switchTo().window(driver.getWindowHandle());
                
                driver.close();
                Common.sleep(1);
                
//              org.openqa.selenium.NoSuchWindowException: no such window: target window already closed
//              System.out.println(driver.findElement(By.tagName("h1")).getText());
                
                /*
                 * Switch back to default Window
                 */
                driver.switchTo().window(windows.iterator().next());
                
                String header1 = driver.findElement(By.tagName("h1")).getText();
                System.out.println(header1);
                assertEquals(header1, "Work with Windows");
            }
        }
    }
    
    /**
     * Find the number of opened windows
     */
    @Test
    public void test2 () {
    	driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();
        Set<String> setOfWindows = driver.getWindowHandles();
        assertTrue(setOfWindows.size() == 3);
        for (String window : setOfWindows) {
            driver.switchTo().window(window);
            Common.sleep(1);
            driver.manage().window().maximize();
            
            String url = driver.getCurrentUrl();
            String title = driver.getTitle();
            String header = driver.findElement(By.tagName("h1")).getText();
            System.out.println(url);
            System.out.println(title);
            System.out.println(header);
            
            if ( !url.contains("/Window.html") ) {
                driver.close();
            }
        }
        
        driver.switchTo().window(setOfWindows.iterator().next());
        
        System.out.println(driver.findElement(By.tagName("h1")).getText());
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

