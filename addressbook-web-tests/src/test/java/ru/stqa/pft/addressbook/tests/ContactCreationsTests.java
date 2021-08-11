package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationsTests extends TestBase{

  @Test
  public void UserCreationTests() throws Exception {
    app.contact().goToHomePage();
    List<UserData> before = app.contact().list();
    UserData user = new UserData()
            .withFirstname("Anna").withLastname("Khristanova").withAddress("Petrozavodsk").withHome("888");
    app.contact().create(user);
    List<UserData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(user);
    Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()) ;
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }



}
