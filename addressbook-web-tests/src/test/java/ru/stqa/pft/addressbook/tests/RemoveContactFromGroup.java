package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class RemoveContactFromGroup extends TestBase {




    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withFooter("test2").withHeader("test3"));
        }
        if (app.db().users().size() == 0) {
            app.contact().goToHomePage();
            app.contact().create(new UserData().withFirstname("Анна").withLastname("Христанова"));
        }
    }

    @Test
    public void testRemoveContactFromGroup() throws Exception {

        Groups dbGroups = app.db().groups();
        app.contact().goToHomePage();
        app.group().allGroupsForDeletingContacts();

}
    }
