package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import java.util.Set;



public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().goToHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new UserData()
                    .withFirstname("Anna").withLastname("Khristanova").withAddress("Petrozavodsk").withHome("888"));
        }

    }
    @Test
    public void testContactDeletion() throws Exception {

        Set<UserData> before = app.contact().all();
        UserData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Set<UserData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }

}
