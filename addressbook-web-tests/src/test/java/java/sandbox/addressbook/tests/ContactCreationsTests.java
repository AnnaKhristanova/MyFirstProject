package ru.stqa.pft.sandbox.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.sandbox.addressbook.model.UserData;

public class ContactCreationsTests extends TestBase{

  @Test
  public void UserCreationTests() throws Exception {
    app.getContactHelper().addNewContact();
    app.getContactHelper().fillContactForm(new UserData("Anna", "Khristanova", "Petrozavodsk", "888"));
    app.getNavigationHelper().goToHomePage();
  }

}
