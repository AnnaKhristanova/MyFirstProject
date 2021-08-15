package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        type(By.name("home"), userData.getHomePhone());
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void submitContactModification() {click(By.xpath("//div[@id='content']/form/input[22]"));
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
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
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
    public int count() {
        return  wd.findElements(By.name("selected[]")).size();
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
            String[] phones = element.findElement(By.cssSelector("td:nth-child(6)")).getText().split("\n");
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute( "value"));
            contactCache.add(new UserData().withId(id)
                    .withFirstname(firstname).withLastname(lastname).withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
        }
        return new Users(contactCache);
    }

    public UserData InfoFromEditForm(UserData user) {

        initContactModificationById(user.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new UserData().withId(user.getId()).withFirstname(firstname).withLastname(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }
}
