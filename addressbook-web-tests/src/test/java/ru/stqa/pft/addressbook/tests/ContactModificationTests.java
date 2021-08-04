package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereGroup()) {
            app.getContactHelper().createContact(new UserData("Anna", "Khristanova", "Petrozavodsk", "888"));
        }
        List<UserData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContactHomePage(before.size() - 1);
        app.getContactHelper().initContactModification(before.size() - 1);
        UserData user = new UserData(before.get(before.size() - 1).getId(),"Anna100","Khristanova100", "Petrozavodsk1", "888");
        app.getContactHelper().fillContactForm(user);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
        List<UserData> after = app.getContactHelper().getContactList();
        before.remove(before.size() - 1);
        before.add(user);
        Comparator<? super UserData> byId = Comparator.comparingInt(UserData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
