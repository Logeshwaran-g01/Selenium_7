package Project_Sel;

import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class windowHandel {

    WebDriver dri;
    String url = "https://the-internet.herokuapp.com/windows";

    @BeforeTest
    public void setUp(){
        dri = new ChromeDriver();
        dri.get(url);
        dri.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        dri.manage().window().maximize();
    }

    @AfterTest
    public void tearDown(){
        dri.quit();
    }
    @Test
    public void test1() throws InterruptedException {
        //Clicking the Click here to open new window
        dri.findElement(By.xpath("//div[@id='content']/div/a")).click();
        //Store the handel of the original window
        String originalWindow = dri.getWindowHandle();
        //Switching to the newly opened window
        Set<String> allWindows = dri.getWindowHandles();
        for (String windowHandel : allWindows){
            if(!windowHandel.equals(originalWindow)){
                dri.switchTo().window(windowHandel);
                break;
            }
        }
        Thread.sleep(2000);
        //Getting the text from the page
        String str = dri.findElement(By.xpath("//div[@class='example']/h3")).getText();
        Assert.assertEquals(str, "New Window");
        System.out.println("The Name of the new page is: " + str);
        //Closing the current page
        dri.close();

        //Switching back to the Original window
        dri.switchTo().window(originalWindow);
        //Verify the original window is active
        if (dri.getTitle().equals("The Internet")){
            System.out.println("Original Window is active");
        }else {
            System.out.println("Original window is not active");
        }

    }
}
