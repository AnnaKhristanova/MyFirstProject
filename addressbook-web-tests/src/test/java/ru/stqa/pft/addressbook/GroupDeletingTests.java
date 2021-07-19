package ru.stqa.pft.addressbook;


import org.testng.annotations.Test;


public class GroupDeletingTests extends TestBase {

  @Test
  public void testUntitledTestCase() throws Exception {
    goToGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnGroupPage();
  }

}
