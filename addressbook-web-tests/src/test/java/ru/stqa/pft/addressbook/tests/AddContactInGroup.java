package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactInGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().goToHomePage();
        if (app.db().users().size() == 0) {
            app.contact().create(new UserData()
                    .withFirstname("Anna").withLastname("Khristanova").withAddress("Petrozavodsk").withHomePhone("888"));
        }

    }
    @Test
    public void AddContactInGroupTests(UserData user) throws Exception {
        Users before = app.db().users();
        System.out.println(before);
        UserData modifiedContact = before.iterator().next();
        app.contact().add(user);
        assertThat(app.contact().count(), equalTo(before.size()));
        Users after = app.db().users();
        System.out.println(after);
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(user)));
        verifyContactListInUIContacts();
    }
}
