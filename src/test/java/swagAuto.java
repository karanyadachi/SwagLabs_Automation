import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(JUnit4.class)
public class swagAuto {

    static WebDriver driver;

    public static By Username = By.xpath("//*[@id=\"user-name\"]");
    public static By Password = By.xpath("//*[@id=\"password\"]");
    public static By Login = By.xpath("//*[@id=\"login-button\"]");

    public static By Addcrt1 = By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]");
    public static By Addcrt2 = By.xpath("//*[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]");
    public static By Cart = By.xpath("//*[@id=\"shopping_cart_container\"]/a");

    public static By Checkout = By.xpath("//*[@id=\"checkout\"]");
    public static By Firstname = By.xpath("//*[@id=\"first-name\"]");
    public static By Lastname = By.xpath("//*[@id=\"last-name\"]");
    public static By Zipcode = By.xpath("//*[@id=\"postal-code\"]");
    public static By Continue = By.xpath("//*[@id=\"continue\"]");

    public static By Finish = By.xpath("//*[@id=\"finish\"]");
    public static By Backtohome = By.xpath("//*[@id=\"back-to-products\"]");
    public static By Menu = By.xpath("//*[@id=\"react-burger-menu-btn\"]");
    public static By Logout = By.xpath("//*[@id=\"logout_sidebar_link\"]");

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "F:\\Swag\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void login() throws InterruptedException {
        driver.findElement(Username).sendKeys("standard_user");
        driver.findElement(Password).sendKeys("secret_sauce");
        Thread.sleep(5000);
        driver.findElement(Login).click();
    }

    public static void addToCart() throws InterruptedException {
        driver.findElement(Addcrt1).click();
        driver.findElement(Addcrt2).click();
        Thread.sleep(5000);
        driver.findElement(Cart).click();

    }

    public static void checkout() throws InterruptedException {
        driver.findElement(Checkout).click();
        driver.findElement(Firstname).sendKeys("Karan");
        driver.findElement(Lastname).sendKeys("R Y");
        driver.findElement(Zipcode).sendKeys("560036");
        Thread.sleep(5000);
        driver.findElement(Continue).click();
        driver.findElement(Finish).click();

    }

    public static void logout() throws InterruptedException {
        driver.findElement(Backtohome).click();
        driver.findElement(Menu).click();
        Thread.sleep(2000);
        driver.findElement(Logout).click();

    }

    @Test
    public void logoTest() {
        WebElement Logo = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]"));
        Assert.assertTrue(Logo.isDisplayed());
    }

    @Test
    public void productPageTest() throws InterruptedException {
        login();
        // Verify product page title
        String expectedProductTitle = "Swag Labs";
        String actualProductTitle = driver.getTitle();
        Assert.assertEquals("Product page title", expectedProductTitle, actualProductTitle);

//        // Verify product name
        String expectedProductName = "Sauce Labs Backpack";
        String actualProductName = driver.findElement(By.xpath("//*[@id='item_4_title_link']/div")).getText();
        Assert.assertEquals("Product price", expectedProductName, actualProductName);

        // Verify product price
        String expectedProductPrice = "$29.99";
        String actualProductPrice = driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();
        Assert.assertEquals("Product price", expectedProductPrice, actualProductPrice);
    }


    @Test
    public void cartTest() throws InterruptedException {
        login();
        addToCart();
        String expectedCartTitle = "Swag Labs";
        String actualCartTitle = driver.getTitle();
        Assert.assertEquals("Cart page title", expectedCartTitle, actualCartTitle);

        //Your cart test
        String expected="Your Cart";
        String actual=driver.findElement(By.xpath("//div[@class='header_secondary_container']")).getText();
        Assert.assertEquals("Not matched",expected,actual);

    }

    @Test
    public void logoutTest() {
        String expectedLogTitle = "Swag Labs";
        String actualLogTitle = driver.getTitle();
        Assert.assertEquals("Logout page title matched", expectedLogTitle, actualLogTitle);
    }

    public static void main(String[] args)  {
        swagAuto s=new swagAuto();
        try {
            s.setUp();

            login();
            addToCart();
            checkout();
            logout();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            s.tearDown();
        }
    }
}

