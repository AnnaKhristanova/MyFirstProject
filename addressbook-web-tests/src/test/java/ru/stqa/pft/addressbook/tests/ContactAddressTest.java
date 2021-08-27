package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.get().contact().goToHomePage();
        if (app.get().contact().all().size() == 0) {
            app.get().contact().create(new UserData()
                    .withFirstname("Anna").withLastname("Khristanova").withAddress("Petrozavodsk").withHomePhone("888"));
        }

    }

    @Test
    public void testContactPhones() {

        UserData user = app.get().contact().all().iterator().next();
        System.out.println("user=" + app.get().contact().all().iterator().next());
        UserData userInfoFromEditForm = app.get().contact().InfoFromEditForm(user);
        System.out.println("userInfoFromEditForm=" + app.get().contact().InfoFromEditForm(user));
        System.out.println("user=" + user.getAddress());
        System.out.println("userInfoFromEditForm=" + userInfoFromEditForm.getAddress());
        assertThat(user.getAddress(), equalTo(userInfoFromEditForm.getAddress()));
    }
}


