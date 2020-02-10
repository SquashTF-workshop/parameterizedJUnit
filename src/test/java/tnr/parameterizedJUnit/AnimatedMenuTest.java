package tnr.parameterizedJUnit;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.squashtest.ta.galaxia.squash.tf.galaxia.annotations.TFMetadata;
import org.squashtest.ta.galaxia.tf.param.service.TFParamService;

import java.util.concurrent.TimeUnit;

public class AnimatedMenuTest {

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

    @TFMetadata(key = "linked-TC", value = "afcee4b8-d990-4644-9cbc-99b92dee4e12")
    @DisplayName("MenuTestimonials")
    @Test
    public void testMenuTestimonials(){
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='result']")));
        driver.findElement(By.xpath("//a[contains(text(),'"+ paramLocator +"')]")).click();
        String style = driver.findElement(By.xpath("//a[contains(text(),'"+ paramLocator +"')]")).getAttribute("class");
        Assertions.assertTrue(style.contains("is-active"), "Le bouton n'a pas été activé");
        driver.findElement(By.xpath("//a[contains(text(),'About')]")).click();
        style = driver.findElement(By.xpath("//a[contains(text(),'"+ paramLocator +"')]")).getAttribute("class");
        Assertions.assertFalse(style.contains("is-active"), paramErrorMsg);
    }

    @TFMetadata(key = "linked-TC", value = "141714ad-e3cc-463b-bddb-df7231958630")
    @DisplayName("MenuBlog")
    @Test
    public void testMenuBlog(){
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='result']")));
        driver.findElement(By.xpath("//a[contains(text(),'"+ paramLocator +"')]")).click();
        String style = driver.findElement(By.xpath("//a[contains(text(),'"+ paramLocator +"')]")).getAttribute("class");
        Assertions.assertTrue(style.contains("is-active"), "Le bouton n'a pas été activé");
        driver.findElement(By.xpath("//a[contains(text(),'About')]")).click();
        style = driver.findElement(By.xpath("//a[contains(text(),'"+ paramLocator +"')]")).getAttribute("class");
        Assertions.assertTrue(style.contains("is-active"), paramErrorMsg);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
