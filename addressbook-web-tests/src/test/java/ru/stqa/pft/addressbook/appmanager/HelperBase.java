package ru.stqa.pft.addressbook.appmanager;

import net.bytebuddy.matcher.ElementMatcher;
import org.openqa.selenium.*;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd=wd;
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        wd.findElement(locator).click();
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)){
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }
    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }
}
