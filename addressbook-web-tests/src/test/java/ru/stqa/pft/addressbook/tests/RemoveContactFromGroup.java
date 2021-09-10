package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.testng.Assert.assertNotEquals;


public class RemoveContactFromGroup extends TestBase {

    private GroupData group;
    private Groups before;
    private int id;
    private Groups after;
    private int counter;

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("testNew"));
        }

        app.contact().goToHomePage();
        if (app.db().users().size() == 0) {
            UserData user = new UserData()
                    .withFirstname("Anna").withLastname("Khristanova").withAddress("Petrozavodsk").withHomePhone("888");
            app.contact().create(user);
            app.contact().addTo(user);

        }

        Users users = app.db().users();
        for (UserData user: users) {
            if (user.getGroups().size() != 0)  {
                counter = 1;
                break;
            } counter = 0;

            }
        if (counter==0){
            UserData user = users.iterator().next();
            app.contact().addTo(user);
        }

        }


    @Test
    public void testRemoveContactFromGroup() {

        Groups groups = app.db().groups();
        Users users = app.db().users();
        for (UserData user: users) {
            if (user.getGroups().size() != 0)  {
                before = user.getGroups();
                group = groups.iterator().next();
                app.contact().selectGroupFromMenuList(group);
                app.contact().selectContactFromList(user);
                app.contact().removeContactFromGroup();
                counter =1;
                id = user.getId();
                UserData modyfiedUser = app.db().user(id);
                after = modyfiedUser.getGroups();
                break;

            }

        }
        assertNotEquals(before, after);
    }
}