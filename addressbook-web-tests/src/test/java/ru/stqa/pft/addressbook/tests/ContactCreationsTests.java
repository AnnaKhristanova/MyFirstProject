package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;
import java.util.Set;

public class ContactCreationsTests extends TestBase{

  @Test
  public void UserCreationTests() throws Exception {
    app.contact().goToHomePage();
    Set<UserData> before = app.contact().all();
    UserData user = new UserData()
            .withFirstname("Anna1000").withLastname("Khristanova1000").withAddress("Petrozavodsk").withHome("888");
    app.contact().create(user);
    Set<UserData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(user);
    Assert.assertEquals(before, after);
  }



}
