package org.ibs.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseTests {
    public static WebDriver driver;

    @BeforeAll
    static void openGooglePage() {

        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver",
                "A:\\QA\\Java\\Тестирование Java\\Проекты IDE\\qa-practice-3\\src\\test\\resources\\chromedriver.exe");

        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

}
