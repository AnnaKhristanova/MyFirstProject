package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().goToHomePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new UserData("Anna", "Khristanova", "Petrozavodsk", "888"));
        }

    }

    @Test
    public void testGroupModification() {
        List<UserData> before = app.contact().list();
        int index = before.size() - 1;
        UserData user1 = new UserData(before.get(index).getId(),"Anna500","Khristanova500", "Petrozavodsk1", "888");
        app.contact().modify(index, user1);
        List<UserData> after = app.contact().list();
        before.remove(index);
        before.add(user1);
        Comparator<? super UserData> byId = Comparator.comparingInt(UserData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }


}
