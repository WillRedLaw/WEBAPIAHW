package com.banking.bank;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class Person implements Serializable {

    protected String firstname;
    protected String surname;
    protected String email;
    protected String address;

    public Person() {
        firstname = new String();
        surname = new String();
        email = new String();
        address = new String();
    }

    public Person(String firstname,
                  String surname,
                  String email,
                  String address) {

        setFirstname(firstname);
        setSurname(surname);
        setEmail(email);
        setAddress(address);
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

}
