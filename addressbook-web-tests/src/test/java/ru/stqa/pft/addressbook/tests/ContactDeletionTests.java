package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import static org.testng.AssertJUnit.assertTrue;

public class ContactDeletionTests extends TestBase{
    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereGroup()) {
            app.getContactHelper().createContact(new UserData("Anna", "Khristanova", "Petrozavodsk", "888"));
        }
        app.getContactHelper().selectContactHomePage();
        app.getContactHelper().deleteContactHomePage();
        app.getContactHelper().AssertTrue();
        app.getNavigationHelper().goToHomePage();
    }
}
