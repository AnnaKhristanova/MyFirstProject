package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationsTests extends TestBase{

  @Test
  public void UserCreationTests() throws Exception {
    app.contact().goToHomePage();
    Users before = app.contact().all();
    File photo = new File("src/test/resources/image_2021_05_30T15_13_35_761Z.png");
    UserData user = new UserData()
            .withFirstname("Anna2000").withLastname("Khristanova2000").withAddress("Petrozavodsk").withHomePhone("888").withPhoto(photo);
    app.contact().create(user);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Users after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void  testCurrentDir(){
  File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/image_2021_05_30T15_13_35_761Z.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
