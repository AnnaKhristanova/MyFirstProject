package ru.stqa.pft.addressbook;

public class UserData {
    private final CharSequence firstname;
    private final CharSequence lastname;
    private final CharSequence address;
    private final CharSequence home;

    public UserData(CharSequence firstname, CharSequence lastname, CharSequence address, CharSequence home) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.home = home;
    }

    public CharSequence getFirstname() {
        return firstname;
    }

    public CharSequence getLastname() {
        return lastname;
    }

    public CharSequence getAddress() {
        return address;
    }

    public CharSequence getHome() {
        return home;
    }
}
