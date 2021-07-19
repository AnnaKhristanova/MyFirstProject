package ru.stqa.pft.addressbook.tests;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationsTests extends TestBase{

  @Test
  public void UserCreationTests() throws Exception {
    app.getContactHelper().addNewUser();
    app.getContactHelper().fillGroupForm(new UserData("Anna", "Khristanova", "Petrozavodsk", "888"));
    app.getContactHelper().goToHomePage();
  }

}
