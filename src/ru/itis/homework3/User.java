package ru.itis.homework3;

public class User {
    private long id;
    private Short age;
    private String firstName;
    private String lastName;

    public User(long id, short age, String firstName, String lastName) {
        this.age = age;
        this.id = id;
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }

    public Short getAge() { return age; }

    public void setAge(Short id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return (getId().toString()  + " " + getFirstName() + " " + getLastName() + " " + getAge().toString());
    }
}

