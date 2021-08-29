package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().goToHomePage();
        if (app.db().users().size() == 0) {
            app.contact().create(new UserData()
                    .withFirstname("Anna").withLastname("Khristanova").withAddress("Petrozavodsk").withHomePhone("888"));
        }

    }
    @Test
    public void testContactPhones(){

        UserData user = app.contact().all().iterator().next();
        UserData userInfoFromEditForm = app.contact().InfoFromEditForm(user);

        assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFromEditForm)));

    }

    private String mergePhones(UserData user) {

        return Arrays.asList(user.getHomePhone(), user.getMobilePhone(), user.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));

    }
    public static String cleaned (String phone){

        return phone.replaceAll("\\s", ""). replaceAll("[-()]", "");
    }
}
