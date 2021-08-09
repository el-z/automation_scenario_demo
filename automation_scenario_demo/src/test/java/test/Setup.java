package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


public class Setup {

    private static WebDriver driver = null;


    public WebDriver getWebDriver() {

        System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        Setup.driver = driver;

        return driver;
    }

    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(5000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            fail("Timeout waiting for Page Load Request to complete.");
        }
    }


    public String getBaseUrl() {

        return "https://www.ajio.com/";
    }

    public void takeScreenshot() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./src/test/java/screenshots/scrnsht_" + timeStamp + ".png"));
    }

    //for demo purposes only
    public List<String> getBrands() {
        ArrayList<String> list = new ArrayList<>();
        list.add("VAN HEUSEN");
        list.add("BLACKBERRYS");
        list.add("RAYMOND");
        list.add("LOUIS PHILIPPE");
        list.add("PARK AVENUE");

        return list;
    }


}
