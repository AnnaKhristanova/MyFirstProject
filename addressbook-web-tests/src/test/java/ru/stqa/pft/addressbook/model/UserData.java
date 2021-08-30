package ru.stqa.pft.addressbook.model;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("user")
@Entity
@Table(name="addressbook")
public class UserData {
    @XStreamOmitField
    @Id
    @Column(name="id")
    private  int  id = Integer.MAX_VALUE;
    @Column(name="firstname")
    private  String firstname;
    @Column(name="lastname")
    private  String lastname;
    @Transient
    private  String address;
    @Column(name="home")
    @Type(type = "text")
    private  String home;
    @Column(name="mobile")
    @Type(type = "text")
    private String mobile;
    @Type(type = "text")
    @Column(name="work")
    private String work;
    @Transient
    private String allPhones;
    @Transient
    private String email;
    @Transient
    private String email2;
    @Transient
    private String email3;
    @Transient
    private String allEmails;
    @Transient
    private String photo;

    public Groups getGroups() {
        return new Groups(groups);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="address_in_groups", joinColumns = @JoinColumn(name="id"),inverseJoinColumns = @JoinColumn(name="group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();


    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", home='" + home + '\'' +
                ", mobile='" + mobile + '\'' +
                ", work='" + work + '\'' +
                ", allPhones='" + allPhones + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", allEmails='" + allEmails + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserData withAddress(String address) {
        this.address = address;
        return this;
    }

    public UserData withHomePhone(String home) {
        this.home = home;
        return this;
    }

    public UserData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }
    public UserData withWorkPhone(String work) {
        this.work = work;
        return this;
    }
    public UserData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }
    public UserData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public UserData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }
    public UserData withEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public UserData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {return address;}

    public String getHomePhone() {
        return home;
    }

    public String getMobilePhone() {return mobile;}

    public String getWorkPhone() {return work;}

    public String getAllPhones() {return allPhones;}

    public String getEmail() {return email;}
    public String getEmail2() {return email2;}

    public String getEmail3() {return email3;}

    public String getAllEmails() {return allEmails;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id && Objects.equals(firstname, userData.firstname) && Objects.equals(lastname, userData.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    public File getPhoto() {return new File(photo);}
}



