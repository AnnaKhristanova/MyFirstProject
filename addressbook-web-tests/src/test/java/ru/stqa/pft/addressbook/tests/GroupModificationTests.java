package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{

 @Test
    public void testGroupModification() {
     app.getNavigationHelper().goToGroupPage();
     int before = app.getGroupHelper().getGroupCount();
  if (!app.getGroupHelper().isThereGroup()) {
   app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
  }
     app.getGroupHelper().selectGroup(before - 1);
     app.getGroupHelper().initGroupModification();
     app.getGroupHelper().fillGroupForm(new GroupData("test1.1", "test1.2", "test1.3"));
     app.getGroupHelper().submitGroupModification();
     app.getGroupHelper().returnGroupPage();
     int after = app.getGroupHelper().getGroupCount();
     Assert.assertEquals(after, before);
 }
}
