package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;
import java.util.List;


public class ContactHelper extends HelperBase{


    private UserData user;

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
        contactCache = null;
        goToHomePage();
    }

    public void modify(UserData user) {
        initContactModificationById(user.getId());
        fillContactForm(user);
        submitContactModification();
        contactCache = null;
        goToHomePage();
    }

    private void initContactModificationById(int id)  {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
        wd.findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    public void delete(UserData user) {
        selectContactHomePageById(user.getId());
        deleteContactHomePage();
        AssertTrue();
        contactCache = null;
        goToHomePage();
    }

    private void selectContactHomePageById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

    }


    public boolean isThereGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    private Users contactCache = null;


    public Users all() {

         if (contactCache != null){
             return new Users(contactCache);
         }
        contactCache = new Users();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for (WebElement element: elements) {
            String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute( "value"));
            contactCache.add(new UserData().withId(id)
                    .withFirstname(firstname).withLastname(lastname));
        }
        return new Users(contactCache);
    }
}
