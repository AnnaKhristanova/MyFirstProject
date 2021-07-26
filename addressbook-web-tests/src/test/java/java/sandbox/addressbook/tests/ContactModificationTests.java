package ru.stqa.pft.sandbox.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.addressbook.model.UserData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new UserData("Anna1", "Khristanova1", "Petrozavodsk1", "999"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
