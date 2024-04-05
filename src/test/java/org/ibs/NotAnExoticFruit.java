package org.ibs;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class NotAnExoticFruit {

    static WebDriver driver;

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

    @Test
    void openPageAndNavigateToFoodCategory(){
        driver.get("http://localhost:8080/");

        WebElement element = driver.findElement(By.xpath("//a[@id=\"navbarDropdown\"]"));
        element.click();

        WebElement buttonFood = driver.findElement(By.xpath("//a[@href=\"/food\"]"));
        buttonFood.click();
    }

    @Test
    void addNewFood(){
        WebElement buttonAddNewFood = driver.findElement(By.xpath("//button[@data-target=\"#editModal\"]"));
        buttonAddNewFood.click();

        WebElement inputFoodName = driver.findElement(By.xpath("//input[@id=\"name\"]"));
        inputFoodName.clear();
        inputFoodName.sendKeys("Банан");

        WebElement inputFoodType = driver.findElement(By.xpath("//select[@id=\"type\"]"));
        inputFoodType.click();
        WebElement inputFoodTypeFruit = driver.findElement(By.xpath("//select[@id=\"type\"]//option[@value=\"FRUIT\"]"));
        inputFoodTypeFruit.click();

        WebElement buttonSave = driver.findElement(By.xpath("//button[@id=\"save\"]"));
        buttonSave.click();

        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void verifyNewFoodAdded() {
        WebElement carrotName = driver.findElement(By.xpath("//tbody/tr[5]/td[1]"));
        Assertions.assertEquals("Банан", carrotName.getText(), "Не соответствует значение...");

        WebElement carrotType = driver.findElement(By.xpath("//tbody/tr[5]/td[2]"));
        Assertions.assertEquals("Фрукт", carrotType.getText(), "Не соответствует значение...");

        WebElement carrotExotic = driver.findElement(By.xpath("//tbody/tr[5]/td[3]"));
        Assertions.assertEquals("false", carrotExotic.getText(), "Не соответствует значение...");
    }

    @Test
    void DataReset(){
        WebElement element = driver.findElement(By.xpath("//a[@id=\"navbarDropdown\"]"));
        element.click();

        WebElement elementResetData = driver.findElement(By.xpath("//a[@id=\"reset\"]"));
        elementResetData.click();
    }
}
