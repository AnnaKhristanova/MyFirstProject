package ru.stqa.pft.sandbox.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.sandbox.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnGroupPage() {
      click(By.linkText("groups"));
    }

    public void subminGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type (By.name("group_name"), groupData.getName());
        type (By.name("group_footer"), groupData.getFooter());
        type (By.name("group_header"), groupData.getHeader());
    }

    public void initGroupCreation() {
      click(By.name("new"));
    }

    public void deleteSelectedGroups() {
      click(By.name("delete"));
    }

    public void selectGroup() {
      click(By.name("selected[]"));
    }

    public void initGroupModification() { click(By.name("edit"));

    }

    public void submitGroupModification() { click(By.name("update"));
    }
}
