package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().goToHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new UserData()
                    .withFirstname("Anna").withLastname("Khristanova").withAddress("Petrozavodsk").withHomePhone("888"));
        }

    }
    @Test
    public void testContactPhones(){

        UserData user = app.contact().all().iterator().next();
        UserData userInfoFromEditForm = app.contact().InfoFromEditForm(user);
        assertThat(user.getHomePhone(), equalTo(cleaned(userInfoFromEditForm.getHomePhone())));
        assertThat(user.getMobilePhone(), equalTo(cleaned(userInfoFromEditForm.getMobilePhone())));
        assertThat(user.getWorkPhone(), equalTo(cleaned(userInfoFromEditForm.getWorkPhone())));
    }
    public String cleaned (String phone){

        return phone.replaceAll("\\s", ""). replaceAll("[-()]", "");
    }
}
