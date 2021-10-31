package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.tagName;


public class CardOrderFormTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldTest() {
        driver.get("http://localhost:9999");
        driver.findElement(cssSelector("[type='text']")).sendKeys("Элина");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+79870242824");
        driver.findElement(cssSelector("[class='checkbox__box']")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id='order-success']")).getText().strip();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage, "Фактическое сообщение не соответствует ожидаемому");
    }
}
