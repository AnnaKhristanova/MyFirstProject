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

    public void initContactModification() {click(By.xpath("//tr[3]/td[8]/a/img"));
    }

    public void submitContactModification() {click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void deleteContactModification() {click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void selectContactHomePage() {click(By.xpath("//tr[3]/td/input"));
    }

    public void deleteContactHomePage() {click(By.xpath("//input[@value='Delete']"));
    }

    public void AssertTrue() {
        wd.switchTo().alert().accept();
    }
}
