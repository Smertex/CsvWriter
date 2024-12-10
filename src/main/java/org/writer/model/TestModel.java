package org.writer.model;

import by.smertex.annotation.Csv;

@Csv(exclude = {"person"})
public class TestModel {
    private String username;

    private String firstname;

    private String lastname;

    private int age;

    private Person person;

    public String getUsername() {
        return username;
    }

    public TestModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public TestModel setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public TestModel setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public int getAge() {
        return age;
    }

    public TestModel setAge(int age) {
        this.age = age;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public TestModel setPerson(Person person) {
        this.person = person;
        return this;
    }
}
