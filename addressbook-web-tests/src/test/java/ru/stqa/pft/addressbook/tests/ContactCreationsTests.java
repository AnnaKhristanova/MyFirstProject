package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationsTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validUsers() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/users.xml"));
    String xml = "";
    String line = reader.readLine();
    while (line != null){
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    xStream.addPermission(AnyTypePermission.ANY);
    xStream.processAnnotations(UserData.class);
    List<UserData> users =  (List<UserData>) xStream.fromXML(xml);
    return users.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validUsers")
  public void UserCreationTests(UserData user) throws Exception {
    app.contact().goToHomePage();
    Users before = app.db().users();
    System.out.println(before);
    app.contact().create(user);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Users after = app.db().users();
    System.out.println(after);
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
