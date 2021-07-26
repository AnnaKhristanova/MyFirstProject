package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class ContactDeletionTests extends TestBase{
    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContactHomePage();
        app.getContactHelper().deleteContactHomePage();
        app.getContactHelper().AssertTrue();
        app.getNavigationHelper().goToHomePage();
    }
}
