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
                    .withFirstname("Anna").withLastname("Khristanova").withAddress("Petrozavodsk").withHomePhone("888"));
        }

    }

    @Test
    public void testGroupModification() {
        Users before = app.contact().all();
        System.out.println("before=" + app.contact().all());
        UserData modifiedContact = before.iterator().next();
        UserData user = new UserData()
                .withId(modifiedContact.getId())
                .withFirstname("Anna500").withLastname("Khristanova500").withAddress("Petrozavodsk").withHomePhone("888");
        app.contact().modify(user);
        assertThat(app.contact().count(), equalTo(before.size()));
        Users after = app.contact().all();
        System.out.println("after=" + app.contact().all());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(user)));
    }
}
