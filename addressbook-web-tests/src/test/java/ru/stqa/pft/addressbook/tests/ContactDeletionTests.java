package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().goToHomePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new UserData("Anna", "Khristanova", "Petrozavodsk", "888"));
        }

    }
    @Test(enabled = false)
    public void testContactDeletion(){

        List<UserData> before = app.contact().list();
        app.contact().delete();
        List<UserData> after = app.contact().list();
        int index = before.size() - 1;
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
