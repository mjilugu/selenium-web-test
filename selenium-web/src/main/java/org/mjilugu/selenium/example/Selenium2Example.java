package org.mjilugu.selenium.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium2Example  {
    public static void main(String[] args) {
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        WebDriver driver = new FirefoxDriver();

        // And now use this to visit Google
        driver.get("https://atrcxb1132.athtem.eei.ericsson.se:8181/EniqEventsUI/");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement userName = driver.findElement(By.xpath("//input[@type='text' and not(@tabindex)][1]"));
        WebElement passWord = driver.findElement(By.xpath("//input[@type='password'][1]"));
        WebElement submit = driver.findElement(By.xpath("//div[contains(@class, 'login_submit') and @role='button']//input[1]"));

        // Enter something to search for
        pause(5000);
        userName.sendKeys(Keys.TAB);
        userName.sendKeys("admin");
        userName.sendKeys(Keys.TAB);
        passWord.sendKeys("admin");
        passWord.sendKeys(Keys.TAB);
        submit.sendKeys(Keys.ENTER);

        // Now submit the form. WebDriver will find the form for us from the element
        //element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("ericsson oss");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
        
        //Close the browser
        driver.quit();
    }
    
    private static void pause(int milli){
        try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
		}
    }
}