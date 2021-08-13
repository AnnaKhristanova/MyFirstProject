package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().goToHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new UserData()
                    .withFirstname("Anna").withLastname("Khristanova").withAddress("Petrozavodsk").withHome("888"));
        }

    }

    @Test
    public void testGroupModification() {
        Set<UserData> before = app.contact().all();
        UserData modifiedContact = before.iterator().next();
        UserData user = new UserData().withId(modifiedContact.getId())
                .withFirstname("Anna700").withLastname("Khristanova700").withAddress("Petrozavodsk").withHome("888");
        app.contact().modify(user);
        Set<UserData> after = app.contact().all();
        before.remove(modifiedContact);
        before.add(user);
        Assert.assertEquals(before, after);

    }


}
