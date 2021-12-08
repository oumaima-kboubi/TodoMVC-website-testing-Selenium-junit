import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.opera.OperaDriver;

import java.time.Duration;

public class Todomvc {
    WebDriver driver;
    JavascriptExecutor js;

    @BeforeAll
    public static  void init(){
        WebDriverManager.operadriver().setup();

    }

    @BeforeEach
    public void initDriver(){
        driver = new OperaDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(90));

        js = (JavascriptExecutor) driver;
    }

    private void platformChoice(String platform) {
        WebElement element = driver.findElement(By.linkText(platform));
        element.click();
    }
    private void addTodo(String todo) {
        WebElement element = driver.findElement(By.className("new-todo"));
        element.sendKeys(todo);
        element.sendKeys(Keys.RETURN);
    }

    @Test
    public void todoCaseTesting() throws InterruptedException {

        //TODO:Naviguer vers le site de todomvc
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
    }



    @AfterEach
    public void quitDriver() throws InterruptedException {
        driver.quit();
    }
}
