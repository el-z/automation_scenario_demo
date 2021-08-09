package test;

import locators.PageLocators;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static java.lang.Thread.sleep;


public class DemoTest {

    public Setup setup;
    public WebDriver driver;
    public PageLocators pageExe;

    @BeforeClass
    public void setUp() {

        try {
            setup = new Setup();
            driver = setup.getWebDriver();
            pageExe = new PageLocators(driver);
            driver.get(setup.getBaseUrl());
            setup.waitForLoad(driver);
        } catch (NullPointerException e) {
            System.out.println("ERROR: " + e);
            Assert.assertNull(driver);
        }
    }

    @Test(priority=1)
    public void isCorrectPage() throws IOException {
        setup.takeScreenshot();
        Assert.assertEquals(driver.getCurrentUrl(), setup.getBaseUrl());

    }

    @Test (priority=2)
    public void addToBagErrorMessage() throws InterruptedException {
        pageExe.insertTextInSearchBar("Suit");
        pageExe.clickElementFromSearchBar("Suit Sets For Men");

        sleep(3000);
        setup.waitForLoad(driver);

        pageExe.clickBrands();
        pageExe.clickMoreMenu();

        sleep(3000);
        pageExe.clickElementFromBrands(setup.getBrands());

        pageExe.clickOnProductByIndex(4);


        setup.waitForLoad(driver);
        sleep(3000);


        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        if (driver.getCurrentUrl().equals(setup.getBaseUrl()))
                Assert.fail("Did not continue to the next page");

        pageExe.clickAddToBag();

        Assert.assertTrue(pageExe.isErrorMessageDisplayed());

    }

    @Test (priority=3)
    public void couponSuccessMessage() throws InterruptedException {
        pageExe.insertTextInSearchBar("Suit");
        pageExe.clickElementFromSearchBar("Suit Sets For Men");

        sleep(3000);
        setup.waitForLoad(driver);

        pageExe.clickBrands();
        pageExe.clickMoreMenu();

        sleep(3000);
        pageExe.clickElementFromBrands(setup.getBrands());

        pageExe.clickOnProductByIndex(4);

        setup.waitForLoad(driver);
        sleep(3000);


        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        pageExe.chooseSize();
        pageExe.clickAddToBag();

        pageExe.clickCart();
        pageExe.clickCouponByIndex(1);
        pageExe.clickCouponApplyBtn();


        Assert.assertTrue(pageExe.isCouponMessageDisplayed());

    }



    @AfterClass
    public void tearDown() {
        setup.waitForLoad(driver);
        driver.close();
    }

}