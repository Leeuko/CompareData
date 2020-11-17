import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;


public class CompareDataTest {

    public WebDriver driver;
    private Logger logger = LogManager.getLogger(CompareDataTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    public  void auth(String login, String pass){
        Objects.Logon(driver).click();
        WaitElement.ToBeClickable(driver,Objects.LoginBox(driver), 800);
        Objects.LoginBox(driver).sendKeys(login);
        Objects.PassBox(driver).sendKeys(pass);
        Objects.SubmitButton(driver).submit();
        logger.info("Authorization passed");
    }

    private void  enterLK() {
        WaitElement.ToBeClickable(driver,Objects.Avatar(driver), 800);
        WebElement icon = Objects.Avatar(driver);
        Actions actions = new Actions(driver);
        actions.moveToElement(icon).build().perform();
        Objects.PrivateRoom(driver).click();
        logger.info("Private room is opened");
    }

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Driver is up");
    }
    @Parameters({"username","password"})
    @Test
    public void CompareData(String login, String pass) {

        driver.get(cfg.url());
        logger.info(String.format("Page is opened %s", cfg.url()));
        auth(login, pass);
        enterLK();

        /* В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        У меня проблема с распознаванием кирилицы, так что заполняю не все поля и локаторы у меня более сложные, хотя можно было по title.*/

        WaitElement.ToBeClickable(driver, Objects.Name(driver), 100);
        Objects.Name(driver).clear();
        Objects.Name(driver).sendKeys("Liudmila");
        Objects.LastNameLatin(driver).clear();
        Objects.LastNameLatin(driver).sendKeys("Cosetova");
        Objects.DateofBirth(driver).clear();
        Objects.DateofBirth(driver).sendKeys("24.11.1985");

        //Страна
        Objects.Country(driver).click();
        Objects.SelectCountry(driver).click();

        //Город
        Objects.City(driver).click();
        Objects.SelectCity(driver).click();

        /*Добавляю telegram*/
        Objects.SelectConnection(driver).click();
        Objects.SelectTelegram(driver).click();
        Objects.AddTelegramNumber(driver).clear();
        Objects.AddTelegramNumber(driver).sendKeys("+3 737 956-51-07");

        /*Добавляю viber*/
        if (Objects.SecondConnection(driver) == null) {
            Objects.AddSecondConnection(driver).click();
        }
        Objects.SelectSecondConnection(driver).click();
        Objects.SelectViber(driver).click();
        Objects.AddViberNumber(driver).clear();
        Objects.AddViberNumber(driver).sendKeys("+3 737 956-51-07");

        /* Нажать сохранить*/
        Objects.SaveButton(driver).click();
        logger.info("Data is set");
        WaitElement.PageLoaded(driver, "https://otus.ru/lk/biography/skills/", 60);

        driver.quit();
        driver = new ChromeDriver();
        driver.get(cfg.url());
        logger.info(String.format("Page is opened %s", cfg.url()));
        auth(login, pass);
        enterLK();

        //Проверить, что в разделе о себе отображаются указанные ранее данные
        Assert.assertEquals("Liudmila", Objects.Name(driver).getAttribute("value"), "First Name is invalid in the field");
        Assert.assertEquals("Cosetova", Objects.LastNameLatin(driver).getAttribute("value"), "Last Name is invalid in the field");
        Assert.assertEquals("24.11.1985", Objects.DateofBirth(driver).getAttribute("value"), "Date of Birth is invalid in the field");
        Assert.assertEquals("Молдова", Objects.Country(driver).getText(), "Country is invalid in the field");
        Assert.assertEquals("Кишинев", Objects.City(driver).getText(), "City is invalid in the field");
        Assert.assertEquals("Тelegram", Objects.SelectConnection(driver).getText(), "Connection is invalid in the field");
        Assert.assertEquals("+3 737 956-51-07", Objects.AddTelegramNumber(driver).getAttribute("value"), "Telegram Number is invalid in the field");
        Assert.assertEquals("Viber", Objects.SelectSecondConnection(driver).getText(), "Connection is invalid in the field");
        Assert.assertEquals("+3 737 956-51-07", Objects.AddViberNumber(driver).getAttribute("value"), "Viber Number is invalid in the field");
    }

    @AfterTest
    public  void setDown(){
        if(driver !=null){
            driver.quit();
        }
    }
}



