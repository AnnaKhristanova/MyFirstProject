package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationsTests extends TestBase{

  @Test
  public void UserCreationTests() throws Exception {
    app.getNavigationHelper().goToHomePage();
    List<UserData> before = app.getContactHelper().getContactList();
    app.getContactHelper().addNewContact();
    UserData user = new UserData("Anna", "Khristanova", "Petrozavodsk", "888");
    app.getContactHelper().fillContactForm(user);
    app.getNavigationHelper().goToHomePage();
    List<UserData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(user);
    Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()) ;
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
