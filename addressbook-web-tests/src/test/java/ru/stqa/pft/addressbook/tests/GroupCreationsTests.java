package ru.stqa.pft.addressbook.tests;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationsTests extends TestBase {
@DataProvider
public Iterator<Object[]> validGroups() throws IOException {
  BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/group.xml"));
  String xml = "";
  String line = reader.readLine();
  while (line != null){
   xml += line;
   line = reader.readLine();
  }
  XStream xStream = new XStream();
  xStream.addPermission(AnyTypePermission.ANY);
  xStream.processAnnotations(GroupData.class);
  List<GroupData> groups =  (List<GroupData>) xStream.fromXML(xml);
  return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
}

  @Test(dataProvider = "validGroups")
  public void testGroupCreations(GroupData group) throws Exception {
  app.goTo().groupPage();
  Groups before = app.db().groups();
  app.group().create(group);
  assertThat(app.group().count(), equalTo(before.size() + 1));
  Groups after = app.db().groups();
  assertThat(after, equalTo(before
          .withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyGroupListInUI();
  }

  @Test(enabled = false)
  public void testBudGroupCreations() throws Exception {

    app.goTo().groupPage();
    Groups before = app.db().groups();
    GroupData group = new GroupData().withName("test2'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before));
  }
}
