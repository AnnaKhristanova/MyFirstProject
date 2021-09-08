package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

public class RemoveContactFromGroup extends TestBase {




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
    public void testRemoveContactFromGroup() throws Exception {

        Groups groups = app.db().groups();
        Users users = app.db().users();

        app.contact().goToHomePage();


}
    }
