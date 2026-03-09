package starter.stepdefinitions;


import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;

import net.serenitybdd.core.pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class PurchaseStepDefinitions extends PageObject {

    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    @Given("el usuario accede al sitio DemoBlaze")
    public void abrir_sitio() {

        openUrl("https://www.demoblaze.com/");
        getDriver().manage().window().maximize();
    }

    @When("agrega los siguientes productos al carrito")
    public void agregar_productos(DataTable table) {

        List<Map<String, String>> productos = table.asMaps(String.class, String.class);

        for (Map<String, String> producto : productos) {

            String nombreProducto = producto.get("producto");

            getDriver().findElement(By.linkText(nombreProducto)).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='Add to cart']")));

            getDriver().findElement(By.xpath("//a[text()='Add to cart']")).click();

            wait.until(ExpectedConditions.alertIsPresent());
            getDriver().switchTo().alert().accept();

            getDriver().navigate().to("https://www.demoblaze.com/");
        }
    }

    @And("visualiza el contenido del carrito")
    public void visualizar_carrito() {

        getDriver().findElement(By.id("cartur")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[text()='Place Order']")));
    }

    @Then("completa el formulario de compra con {string} {string} {string} {string} {string} {string}")
    public void completar_formulario(String name, String country, String city,
                                     String card, String month, String year) {

        getDriver().findElement(By.xpath("//button[text()='Place Order']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.id("country")).sendKeys(country);
        getDriver().findElement(By.id("city")).sendKeys(city);
        getDriver().findElement(By.id("card")).sendKeys(card);
        getDriver().findElement(By.id("month")).sendKeys(month);
        getDriver().findElement(By.id("year")).sendKeys(year);
    }

    @And("finaliza la compra")
    public void finalizar_compra() {

        getDriver().findElement(By.xpath("//button[text()='Purchase']")).click();
    }

    @Then("deberia ver el mensaje de confirmacion {string}")
    public void validar_confirmacion(String mensaje) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("sweet-alert")));

        String textoConfirmacion = getDriver().findElement(
                By.xpath("//h2[text()='Thank you for your purchase!']")).getText();

        if (!textoConfirmacion.contains(mensaje)) {

            throw new AssertionError("Mensaje de confirmación incorrecto");
        }
    }
}
