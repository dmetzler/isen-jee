package org.dmetzler.isen.puissance4;

import java.util.List;

import org.dmetzler.isen.puissance4.core.ChipColour;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.common.collect.Lists;

public class Puissance4Page {

    @FindBy(id = "board")
    WebElement board;

    @FindBy(id = "winner")
    WebElement winner;

    @FindBy(id = "reset")
    WebElement resetButton;

    public Puissance4Page(WebDriver driver) {
        driver.get("http://localhost:9090/puissance4-web/index.jsp");
    }

    public boolean hasBoard() {
        return board.isDisplayed();
    }

    public void play(int i) {
        List<WebElement> cols = getColumns();
        cols.get(i).click();
    }

    private List<WebElement> getColumns() {
        List<WebElement> cols = board.findElements(By
                .xpath("//a[@class='blue column']"));
        return cols;
    }

    public ChipColour getCell(int i, int j) {
        String xpath = String
                .format("//a[@class='blue column'][%d]/div", i + 1);
        List<WebElement> cells = Lists.reverse(board.findElements(By
                .xpath(xpath)));

        WebElement cell = cells.get(j);
        String cssClass = cell.getAttribute("class");
        return colourFromClass(cssClass);

    }

    private ChipColour colourFromClass(String cssClass) {
        if (cssClass.contains("red")) {
            return ChipColour.RED;
        } else if (cssClass.contains("yellow")) {
            return ChipColour.YELLOW;
        } else {
            return null;
        }
    }

    public Integer getColumnsNumber() {
        return getColumns().size();
    }

    public Integer getRowNumber() {
        String xpath = String.format("//a[@class='blue column'][%d]/div", 1);
        List<WebElement> cells = Lists.reverse(board.findElements(By
                .xpath(xpath)));
        return cells.size();
    }

    public void reset() {
        resetButton.click();
    }

    public ChipColour getWinner() {
        try {
            return colourFromClass(winner.getAttribute("class"));
        } catch (NoSuchElementException e) {
            return null;
        }

    }

}
