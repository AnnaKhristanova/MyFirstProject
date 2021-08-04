package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ContactDeletionTests extends TestBase{
    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereGroup()) {
            app.getContactHelper().createContact(new UserData("Anna", "Khristanova", "Petrozavodsk", "888"));
        }
        List<UserData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContactHomePage(before.size() - 1);
        app.getContactHelper().deleteContactHomePage();
        app.getContactHelper().AssertTrue();
        app.getNavigationHelper().goToHomePage();
        List<UserData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
