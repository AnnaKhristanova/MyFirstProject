package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Users before = app.contact().all();
        UserData modifiedContact = before.iterator().next();
        UserData user = new UserData()
                .withId(modifiedContact.getId())
                .withFirstname("Anna700").withLastname("Khristanova700").withAddress("Petrozavodsk").withHome("888");
        app.contact().modify(user);
        Users after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(before, equalTo(before.without(modifiedContact).withAdded(user)));

    }


}
