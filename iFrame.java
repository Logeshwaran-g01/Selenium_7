package Project_Sel;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class iFrame {

    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/iframe";

    @BeforeTest
    public void SetUo(){
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
    }
    @AfterTest
    public void tearDown(){
        driver.close();
    }

    @Test
    public void test1() throws InterruptedException {

        //Canceling the popup notification
        driver.findElement(By.xpath("//button[@class='tox-notification__dismiss tox-button tox-button--naked tox-button--icon']")).click();

        //Switching to iFrame
        WebElement frame =  driver.findElement(By.xpath("//iframe[@id='mce_0_ifr']"));
        driver.switchTo().frame(frame);

        WebElement we = driver.findElement(By.tagName("p"));
        //Installing JavascriptExecutor
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        //Clearing the text using JavascriptExecutor
        jse.executeScript("arguments[0].innerHTML= '';",we);
        //Send value to the input field using JavascriptExecutor
        jse.executeScript("arguments[0].innerHTML= arguments[1];",we,"Hello People!");
        //Getting text from the page
        String str = driver.findElement(By.tagName("p")).getText();
        Assert.assertEquals(str,"Hello People");
        System.out.println(str);

    }


}
