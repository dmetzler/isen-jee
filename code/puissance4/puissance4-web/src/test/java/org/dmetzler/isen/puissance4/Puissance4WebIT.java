package org.dmetzler.isen.puissance4;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Puissance4WebIT {

    private WebDriver driver;


    @Before
    public void doBefore() throws Exception {
        driver = new FirefoxDriver();
        // Navigate to the right place
        driver.get("http://localhost:9090/puissance4-web/index.jsp");
    }

    @After
    public void doAfter() {
        driver.quit();
    }

    @Test
    public void itCanBrowseThePage() throws Exception {
        assertThat(driver.findElement(By.id("board")));
    }

}
