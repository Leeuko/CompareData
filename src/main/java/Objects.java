import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Objects {
    public static WebElement element = null;

    //--Authorization----------------------------------------------------------------------------

    public static  WebElement Logon (WebDriver driver){
        element = driver.findElement(By.cssSelector("button.header2__auth"));
        return element;
    }
    public static  WebElement LoginBox (WebDriver driver){
        element = driver.findElement(By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)"));
        return element;
    }

    public static WebElement PassBox (WebDriver driver){
        element = driver.findElement(By.cssSelector(".js-psw-input"));
        return element;
    }

    public static WebElement SubmitButton (WebDriver driver){
        element = driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)"));
        return element;
    }
    //------Private Room----------------------------------------------------------------------------

    public static WebElement Avatar (WebDriver driver) {
        element = driver.findElement(By.cssSelector(".ic-blog-default-avatar"));
        return element;
    }

    public static WebElement PrivateRoom (WebDriver driver){
            element = driver.findElement(By.cssSelector(".header2-menu__item_dropdown_no-border > div:nth-child(2) > a:nth-child(1)"));
            return element;
    }
    //------Data----------------------------------------------------------------------------

    public static WebElement Name (WebDriver driver){
        element = driver.findElement(By.id("id_fname_latin"));
        return  element;
    }

    public static WebElement LastNameLatin (WebDriver driver){
        element = driver.findElement(By.id("id_lname_latin"));
        return  element;
    }

    public static WebElement DateofBirth (WebDriver driver){
        element = driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)"));
        return  element;
    }

    public static WebElement Country (WebDriver driver){
        element = driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)"));
        return  element;
    }

    public static WebElement City (WebDriver driver){
        element = driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)"));
        return  element;
    }
    public static WebElement SelectCountry (WebDriver driver){
        element = driver.findElement(By.xpath("//button[@data-value='5']"));
        return  element;
    }

    public static WebElement SelectCity (WebDriver driver){
        element = driver.findElement(By.xpath("//button[@data-value='469']"));
        return  element;
    }

    public static WebElement SelectConnection (WebDriver driver){
        element = driver.findElement(By.cssSelector(".input_straight-bottom-right"));
        return  element;
    }
    public static WebElement SelectTelegram (WebDriver driver){
        element = driver.findElement(By.xpath("//button[@data-value='telegram']"));
        return  element;
    }

    public static WebElement AddTelegramNumber (WebDriver driver){
        element = driver.findElement(By.id("id_contact-0-value"));
        return  element;
    }

    public static WebElement SecondConnection (WebDriver driver){
        element = driver.findElement(By.xpath("//input[@name='contact-1-service']"));
        return  element;
    }

    public static WebElement AddSecondConnection (WebDriver driver){
        element = driver.findElement(By.xpath("//button[contains(@class,'js-lk-cv-custom-select-add')]"));
        return  element;
    }

    public static WebElement SelectSecondConnection (WebDriver driver){
        element = driver.findElement(By.xpath("//input[@name='contact-1-service']")).findElement(By.xpath("./following-sibling::div"));
        return  element;
    }

    public static WebElement SelectViber (WebDriver driver){
        element = driver.findElement(By.xpath("//div[@data-num='1']/div/div/div/div/div/div/button[@data-value='viber']"));
        return  element;
    }

    public static WebElement AddViberNumber (WebDriver driver){
        element = driver.findElement(By.id("id_contact-1-value"));
        return  element;
    }

    public static WebElement SaveButton (WebDriver driver){
        element = driver.findElement(By.xpath("//div[@class='lk-cv-action-buttons']/button[@name='continue']"));
        return  element;
    }
}
