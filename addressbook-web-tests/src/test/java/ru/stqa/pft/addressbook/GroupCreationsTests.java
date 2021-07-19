package ru.stqa.pft.addressbook;


import org.testng.annotations.*;


public class GroupCreationsTests extends TestBase {

  @Test
  public void testGroupCreations() throws Exception {
    app.goToGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.subminGroupCreation();
    app.returnGroupPage();
  }

}
