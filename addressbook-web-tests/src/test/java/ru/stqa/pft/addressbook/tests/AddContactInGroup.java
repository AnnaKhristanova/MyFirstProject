package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertNotEquals;

public class AddContactInGroup extends TestBase {
    private int counter;
    private Groups before;
    private Groups after;


    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("testNew"));
        }
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        app.contact().goToHomePage();
        if (app.db().users().size() == 0) {
            app.contact().create(new UserData()
                    .withFirstname("Anna").withLastname("Khristanova").withAddress("Petrozavodsk").withHomePhone("888"));
        }
    }
    @Test
    public void testAddContactToGroup() {

        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        Users users = app.db().users();
        counter = 0;
        for (UserData user: users) {
            if (user.getGroups().size() == 0) {
                before = user.getGroups();
                app.contact().add(user);
                counter = 1;
                after = user.getGroups();
                break;
            } else {
                counter = 0;
            }
        }
        if (counter==0){
            UserData user = new UserData()
                    .withFirstname("Anna").withLastname("Khristanova").withAddress("Petrozavodsk").withHomePhone("888");
            before = user.getGroups();
            app.contact().create(user);
            app.contact().add(user);
            after = user.getGroups();
        }
        assertNotEquals(before, after);

    }
}