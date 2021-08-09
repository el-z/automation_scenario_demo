package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.Assert.fail;

public class PageLocators {

    protected WebDriver driver;
    protected Actions action;

    @FindBy(name = "searchVal")
    WebElement searchBar;

    @FindBy(xpath = "//*[@id='react-autowhatever-1']/ul/li")
    List<WebElement> searchBarList;

    @FindBy(xpath = "//span[contains(text(),'brands')]")
    WebElement brandsMenu;

    @FindBy(xpath = "//*[@id='verticalsizegroupformat']")
    WebElement moreMenu;

    @FindBy(xpath = "//*[@class='facet-filter-modal__body']")
    List<WebElement> brandsList;

    @FindBy(xpath = "//button[contains(text(),'Apply')]")
    WebElement applyBtn;

    @FindBy(xpath = "//*/select/option")
    List<WebElement> sortOption;

    @FindBy(xpath = "//*[@id='products']/div[4]/div[1]/div/div")
    List<WebElement> products;

    @FindBy(xpath = "//span[contains(text(),'ADD TO BAG')]")
    WebElement addToBagBtn;

    @FindBy(xpath = "//span[contains(text(),'Please select a size')]")
    WebElement errorMessage;

    @FindBy(xpath = "//div[contains(text(),'40')]")
    WebElement chooseSize;

    @FindBy(xpath = "//header/div[3]/div[2]/div[2]")
    WebElement cart;

    @FindBy(xpath = "//*[@id='dCartWrapper']//*/ul")
    List<WebElement> coupons;

    @FindBy(xpath = "//button[contains(text(),'Apply')]")
    WebElement couponApplyBtn;

    @FindBy(xpath = "//span[contains(text(),'Your coupon has been redeemed successfully')]")
    WebElement couponMessage;

    @FindBy(xpath = "//button[contains(text(),'Proceed to shipping')]")
    WebElement proceedToShippingBtn;

    @FindBy(xpath = "//*[@name='username']")
    WebElement emailTextBox;

    @FindBy(xpath = "//*[@id='login-modal']//*/input[@value='Continue']")
    WebElement continueBtn;

    public PageLocators(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void insertTextInSearchBar(String text) {
        searchBar.sendKeys(text);
    }

    public void clickElementFromSearchBar(String text) {

        if (text == null)
            fail("Sent Null");

        for (WebElement e : searchBarList)
            if (e.getText().equals(text)) {
                e.click();
                break;
            }
    }

    public void clickBrands() {

        brandsMenu.click();
    }

    public void clickMoreMenu() {
        moreMenu.click();
    }

    public void clickElementFromBrands(List<String> brands) {

        for (String brand : brands)
            this.driver.findElement(By.xpath("//*[@class='facet-list-title-name' and contains(text(), '" + brand + "')]")).click();

        applyBtn.click();

    }

    public void clickOnProductByIndex(int index) {
        products.get(index).click();

    }

    public void clickAddToBag() {
        addToBagBtn.click();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    public void chooseSize() {
        chooseSize.click();
    }

    public void clickCart() {
        cart.click();
    }

    public void clickCouponByIndex(int index) {

        coupons.get(index).click();
    }

    public void clickCouponApplyBtn() {
        couponApplyBtn.click();
    }

    public boolean isCouponMessageDisplayed() {
        return couponMessage.isDisplayed();
    }

    public void clickProceedToShipping() {
        proceedToShippingBtn.click();
    }

    public void insertEmailTextBox(String text) {
        emailTextBox.sendKeys(text);
    }

    public void clickContinue() {
        continueBtn.click();
    }


}