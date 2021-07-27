package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereGroup()) {
            app.getContactHelper().createContact(new UserData("Anna", "Khristanova", "Petrozavodsk", "888"));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new UserData("Anna1", "Khristanova1", "Petrozavodsk1", "999"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}