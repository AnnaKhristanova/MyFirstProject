package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationsTests extends TestBase{

  @Test
  public void UserCreationTests() throws Exception {
    app.contact().goToHomePage();
    Users before = app.contact().all();
    UserData user = new UserData()
            .withFirstname("Anna2000").withLastname("Khristanova2000").withAddress("Petrozavodsk").withHome("888");
    app.contact().create(user);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Users after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
