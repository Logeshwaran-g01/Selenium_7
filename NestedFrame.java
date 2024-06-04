package Project_Sel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class NestedFrame {

    WebDriver driver;
    String url = "http://the-internet.herokuapp.com/nested_frames";
    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void test1(){
        //Switching to the top frame
        WebElement top = driver.findElement(By.xpath("//frame[@name='frame-top']"));
        driver.switchTo().frame(top);
        //Verify the frames in the top
        int topFrames = driver.findElements(By.tagName("frame")).size();
        if (topFrames==3){
            System.out.println("There are three frames in Top");
        }else {
            System.out.println("There are not three frames in Top");
        }
        //Switching to the LEFT frame and Verify the text "LEFT"
        WebElement left = driver.findElement(By.xpath("//frame[@name='frame-left']"));
        driver.switchTo().frame(left);
        WebElement leftFrame = driver.findElement(By.tagName("body"));
        if (leftFrame.getText().contains("LEFT")){
            System.out.println("Left contains the text 'LEFT' ");
        }else{
            System.out.println("Left doesn't contains the text 'LEFT'");
        }

        //Switching to Parent Frame
        driver.switchTo().parentFrame();

        //Switching to the Middle frame and Verify text "MIDDLE"
        WebElement middle = driver.findElement(By.xpath("//frame[@name='frame-middle']"));
        driver.switchTo().frame(middle);
        WebElement middleFrame = driver.findElement(By.tagName("body"));
        if (middleFrame.getText().contains("MIDDLE")){
            System.out.println("Middle contains the text 'MIDDLE' ");
        }else {
            System.out.println("Middle doesn't contains the text 'MIDDLE' ");
        }

        //Switching to Parent Frame
        driver.switchTo().parentFrame();

        //Switching to the Right frame and Verify text "RIGHT"
        WebElement right = driver.findElement(By.xpath("//frame[@name='frame-right']"));
        driver.switchTo().frame(right);
        WebElement rightFrame = driver.findElement(By.tagName("body"));
        if (rightFrame.getText().contains("RIGHT")){
            System.out.println("Right contains the text 'RIGHT' ");
        }else {
            System.out.println("Right doesn't contains the text 'RIGHT' ");
        }

        //Switching to Parent Frame
        driver.switchTo().parentFrame();

        //Switching back to the default frame
        driver.switchTo().defaultContent();

        //Switching to the Bottom frame and Verify text "BOTTOM"
        WebElement bottom = driver.findElement(By.xpath("//frame[@name='frame-bottom']"));
        driver.switchTo().frame(bottom);
        WebElement bottomFrame = driver.findElement(By.tagName("body"));
        if (bottomFrame.getText().contains("BOTTOM")){
            System.out.println("Bottom contains the text 'BOTTOM' ");
        }
        else {
            System.out.println("Bottom doesn't contains the text 'BOTTOM' ");
        }

        //Switching to Parent Frame
        driver.switchTo().defaultContent();

        //Verifying the title of the frame
        if (driver.getTitle().equals("Frames")){
            System.out.println("The page Title is 'Frames' ");
        }else {
            System.out.println("The page Title is not 'Frames'");
        }
    }
}
