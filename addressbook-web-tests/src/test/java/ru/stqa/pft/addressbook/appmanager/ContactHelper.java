package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

    public int index;
    public UserData contact;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void addNew() {click(By.linkText("add new"));}
    public void fillContactForm(UserData userData) {
        type(By.name("firstname"), userData.getFirstname());
        type(By.name("lastname"), userData.getLastname());
        type(By.name("address"), userData.getAddress());
        type(By.name("home"), userData.getHome());
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitContactModification() {click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void deleteContactModification() {click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void selectContactHomePage() {
        click(By.name("selected[]"));
    }

    public void deleteContactHomePage() {click(By.xpath("//input[@value='Delete']"));
    }

    public void goToHomePage() {click(By.linkText("home"));
    }

    public void AssertTrue() {
        wd.switchTo().alert().accept();
    }

    public void create(UserData user) {
        addNew();
        fillContactForm(user);
        goToHomePage();
    }

    public void modify(int index, UserData user1) {
        initContactModification(index);
        fillContactForm(user1);
        submitContactModification();
        goToHomePage();
    }

    public void delete() {
        selectContactHomePage();
        deleteContactHomePage();
        AssertTrue();
        goToHomePage();
    }


    public boolean isThereGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<UserData> list() {
        List<UserData> users = new ArrayList<UserData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for (WebElement element: elements) {
            String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute( "value"));
            users.add(new UserData().withId(id)
                    .withFirstname(firstname).withLastname(lastname));
        }
        return users;

    }
}
