package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.UserData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void addNewContact() {click(By.linkText("add new"));}
    public void fillContactForm(UserData userData) {
        type(By.name("firstname"), userData.getFirstname());
        type(By.name("lastname"), userData.getLastname());
        type(By.name("address"), userData.getAddress());
        type(By.name("home"), userData.getHome());
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void initContactModification() {click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void deleteContactModification() {click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void selectContactHomePage() {click(By.name("selected[]"));
    }

    public void deleteContactHomePage() {click(By.xpath("//input[@value='Delete']"));
    }

    public void AssertTrue() {
        wd.switchTo().alert().accept();
    }

    public void createContact(UserData contact) {
        addNewContact();
        fillContactForm(new UserData("Anna", "Khristanova", "Petrozavodsk", "888"));
    }

    public boolean isThereGroup() {
        return isElementPresent(By.name("selected[]"));
    }
}
