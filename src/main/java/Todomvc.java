import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.time.Duration;

public class Todomvc {
    WebDriver driver;
    JavascriptExecutor js;

    @BeforeAll
    public static void init() {
        WebDriverManager.operadriver().setup();

    }

    @BeforeEach
    public void initDriver() {
        driver = new OperaDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(90));

        js = (JavascriptExecutor) driver;
    }

    private void platformChoice(String platform) {
        WebElement item = driver.findElement(By.linkText(platform));
        item.click();
    }

    private void addTodo(String todo) {
        WebElement item = driver.findElement(By.className("new-todo"));
        item.sendKeys(todo);
        item.sendKeys(Keys.RETURN);
    }

    private void checkTodo(int itemNumber) {
        WebElement item = driver.findElement(By.cssSelector("li:nth-child(" + itemNumber + ") .toggle"));
        item.click();
    }

    private void assertItemLeft(int expectedItemLeftResult) {
        WebElement item = driver.findElement(By.xpath("//footer/*/span | //footer/span"));
            String futureResult = String.format("$d item left", expectedItemLeftResult);
            ExpectedConditions.textToBePresentInElement(item, futureResult);
    }

    public static void takeScreenshot(WebDriver webdriver,String fileWithPath) throws Exception{

        //TODO:Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //TODO:Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //TODO:Move image file to new destination
        File DestFile=new File(fileWithPath);

        //TODO:Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);

    }


    @Test
    public void todoCaseTesting() throws Exception {

        //TODO:Naviguer vers le site de TodoMVC
        driver.get("https://todomvc.com");
        Thread.sleep(2000);

        //TODO:Maximiser la fenêtre
        driver.manage().window().maximize();
        Thread.sleep(2000);

        //TODO:Choisir la platform "Backbone.js"
        platformChoice("Backbone.js");
        Thread.sleep(2000);

        //TODO:Ajouter 3 todo
        addTodo("Plan the camp");
        Thread.sleep(2000);
        addTodo("Take the dog on a walk");
        Thread.sleep(2000);
        addTodo("Study SOLID principals");
        Thread.sleep(2000);

        //TODO:Cocher le deuxième todo
        checkTodo(2);
        Thread.sleep(2000);

        //TODO:Cocher le troisième todo
        checkTodo(3);
        Thread.sleep(2000);

        //TODO:Vérification du champs item left
        assertItemLeft(1);
        Thread.sleep(3000);

        takeScreenshot(driver, "I:\\OneDrive - Ministere de l'Enseignement Superieur et de la Recherche Scientifique\\Desktop\\TP3 screenshot\\screenshot.png"); ;
    }

    @ParameterizedTest
    @ValueSource(strings = {"Backbone.js",
            "AngularJS",
            "Dojo",
            "React"})
    public void todosCaseTesting(String platform) throws Exception {

        //TODO:Naviguer vers le site de TodoMVC
        driver.get("https://todomvc.com");
        Thread.sleep(2000);

        //TODO:Maximiser la fenêtre
        driver.manage().window().maximize();
        Thread.sleep(2000);

        //TODO:Choisir la platform "Backbone.js"
        platformChoice(platform);
        Thread.sleep(2000);

        //TODO:Ajouter 3 todo
        addTodo("Plan the camp");
        Thread.sleep(2000);
        addTodo("Take the dog on a walk");
        Thread.sleep(2000);
        addTodo("Study SOLID principals");
        Thread.sleep(2000);

        //TODO:Cocher le deuxième todo
        checkTodo(2);
        Thread.sleep(2000);

        //TODO:Cocher le troisième todo
        checkTodo(3);
        Thread.sleep(2000);

        //TODO:Vérification du champs item left
        assertItemLeft(1);
        Thread.sleep(2000);

        //TODO:Take a screenshot at the end of the test scenario
        takeScreenshot(driver, "I:\\OneDrive - Ministere de l'Enseignement Superieur et de la Recherche Scientifique\\Desktop\\TP3 screenshot\\screenshot "+platform+".png"); ;


    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}
