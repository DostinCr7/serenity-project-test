package starter.stepdefinitions;

import io.cucumber.java.en.*;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class PurchaseStepDefinitions extends PageObject {

    WebDriverWait wait;

    @Given("the user opens the Demoblaze homepage")
    public void open_homepage() {

        openUrl("https://www.demoblaze.com/");
        getDriver().manage().window().maximize();

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    @When("the user adds two products to the cart")
    public void add_two_products() {
        // Esperar que carguen los productos
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Samsung galaxy s6")));

        // Producto 1
        getDriver().findElement(By.linkText("Samsung galaxy s6")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Add to cart")));

        getDriver().findElement(By.linkText("Add to cart")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        getDriver().switchTo().alert().accept();

        getDriver().navigate().to("https://www.demoblaze.com/");

        // Producto 2
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Nokia lumia 1520")));

        getDriver().findElement(By.linkText("Nokia lumia 1520")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText("Add to cart")));

        getDriver().findElement(By.linkText("Add to cart")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        getDriver().switchTo().alert().accept();
    }

    @And("the user completes the purchase form")
    public void complete_form() {

        getDriver().findElement(By.id("cartur")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[text()='Place Order']")));

        getDriver().findElement(By.xpath("//button[text()='Place Order']")).click();

        // Esperar que el modal aparezca
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        getDriver().findElement(By.id("name")).sendKeys("Joel");
        getDriver().findElement(By.id("country")).sendKeys("Ecuador");
        getDriver().findElement(By.id("city")).sendKeys("Quito");
        getDriver().findElement(By.id("card")).sendKeys("123456789");
        getDriver().findElement(By.id("month")).sendKeys("03");
        getDriver().findElement(By.id("year")).sendKeys("2026");

        getDriver().findElement(By.xpath("//button[text()='Purchase']")).click();
    }

    @Then("the purchase should be completed successfully")
    public void validate_purchase() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("sweet-alert")));

        String confirmationText = getDriver()
                .findElement(By.className("sweet-alert"))
                .getText();

        if (!confirmationText.contains("Thank you for your purchase!")) {
            throw new AssertionError("Purchase was not successful");
        }

        getDriver().findElement(By.xpath("//button[text()='OK']")).click();
    }
}
