package tnr.parameterizedJUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.squashtest.ta.galaxia.squash.tf.galaxia.annotations.TFMetadata;
import org.squashtest.ta.galaxia.tf.param.service.TFParamService;

import java.util.concurrent.TimeUnit;

public class MutationObserverTest {

    WebDriver driver;
    WebDriverWait wait;
    String paramBrowser;
    String paramUrl;
    String paramLocator;
    String paramErrorMsg;
    String paramTimeout;

    @BeforeEach
    public void setUp() throws Exception {
        try{
            paramBrowser = TFParamService.getInstance().getParam("DS_BROWSER","Firefox");
            paramUrl = TFParamService.getInstance().getParam("TS_CUF_URL");
            paramLocator = TFParamService.getInstance().getParam("TC_CUF_LOCATOR");
            paramErrorMsg = TFParamService.getInstance().getParam("IT_CUF_ERRMSG","This is an error message");
            paramTimeout = TFParamService.getInstance().getParam("CPG_CUF_TO","30");
        }catch (Exception e) {
            throw new Exception("Error with parameters");
        }
        if(paramBrowser.equals("Firefox")){
            driver = new FirefoxDriver();
        }
        else if(paramBrowser.equals("Chrome")){
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(paramTimeout), TimeUnit.SECONDS);
        driver.get(paramUrl);
    }

    @TFMetadata(key = "linked-TC", value = "346615e0-0b5b-4ade-a5f6-671ce972138c")
    @Test
    public void testMutation(){
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='result']")));
        driver.findElement(By.xpath("//button[@id='red-button']")).click();
        String style = driver.findElement(By.xpath("//p[@id='"+ paramLocator +"']")).getAttribute("style");
        Assertions.assertTrue(style.contains("color: red"), "Le texte n'a pas été changé en rouge");
        driver.findElement(By.xpath("//button[@id='blue-button']")).click();
        style = driver.findElement(By.xpath("//p[@id='"+ paramLocator +"']")).getAttribute("style");
        Assertions.assertTrue(style.contains("color: blue"), paramErrorMsg);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
