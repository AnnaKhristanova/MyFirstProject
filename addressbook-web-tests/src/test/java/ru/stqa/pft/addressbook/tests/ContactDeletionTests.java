package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.getContactHelper().goToHomePage();
        if (!app.getContactHelper().isThereGroup()) {
            app.getContactHelper().createContact(new UserData("Anna", "Khristanova", "Petrozavodsk", "888"));
        }

    }
    @Test(enabled = false)
    public void testContactDeletion(){

        List<UserData> before = app.getContactHelper().getContactList();
        app.getContactHelper().deleteContact();
        List<UserData> after = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        Assert.assertEquals(after.size(), index);
        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
