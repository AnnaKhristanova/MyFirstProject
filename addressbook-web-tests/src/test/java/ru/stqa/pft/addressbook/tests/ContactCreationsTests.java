package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;

public class ContactCreationsTests extends TestBase{

  @Test
  public void UserCreationTests() throws Exception {
    app.getContactHelper().addNewContact();
    app.getContactHelper().fillContactForm(new UserData("Anna", "Khristanova", "Petrozavodsk", "888"));
    app.getNavigationHelper().goToHomePage();
  }

}
