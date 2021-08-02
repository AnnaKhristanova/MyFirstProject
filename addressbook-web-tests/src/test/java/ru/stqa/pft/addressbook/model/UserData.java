package ru.stqa.pft.addressbook.model;

public class UserData {
    private int id;
    private final String firstname;

    public void setId(int id) {
        this.id = id;
    }
    private final String lastname;
    private final String address;
    private final String home;

    public UserData(String firstname, String lastname, String address, String home) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.home = home;
    }
    public UserData(int id, String firstname, String lastname, String address, String home) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.home = home;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }
}
