package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().goToHomePage();
        if (app.db().users().size() == 0) {
            app.contact().create(new UserData()
                    .withFirstname("Anna").withLastname("Khristanova").withAddress("Petrozavodsk").withHomePhone("888"));
        }

    }
    @Test
    public void testContactDeletion() throws Exception {

        Users before = app.db().users();
        UserData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Users after = app.db().users();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
