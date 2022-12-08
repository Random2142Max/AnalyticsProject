package org.example.Models.Persons;

import java.time.LocalDate;
import java.util.Arrays;

enum Gender {
    MALE,
    FEMALE
}

public class Person {
    private Integer uid;
    private String fullName;
    private String lastName;
    private String firstName;
    private Gender gender;
    private String homeAddress;
    private LocalDate birthDate;

    public Person(String fullName) {
        this.fullName = fullName;
        String[] splitFullName = this.getLastFirstName(fullName).split("~");
        this.lastName = splitFullName[0];
        this.firstName = splitFullName[1];
    }

    public Person(Integer uid, String fullName, String gender, String homeAddress, String birthDate) {
        this.uid = uid;
        this.fullName = fullName;
        String[] splitFullName = this.getLastFirstName(fullName).split("~");
        this.lastName = splitFullName[0];
        this.firstName = splitFullName[1];
        this.gender = this.getGender(gender);
        this.homeAddress = homeAddress;
        this.birthDate = LocalDate.parse(birthDate);
    }

    protected String getLastFirstName(String fullName) {
        String[] massStrFullName = fullName.split(" ");
        switch ((int)Arrays.stream(massStrFullName).count()) {
            case 3:
                return String.format("%s %s~%s", massStrFullName[0], massStrFullName[1], massStrFullName[2]);
            case 4:
                return String.format("%s %s~%s %s", massStrFullName[0], massStrFullName[1], massStrFullName[2], massStrFullName[3]);
            case 5:
                return String.format("%s %s %s~%s %s", massStrFullName[0], massStrFullName[1], massStrFullName[2], massStrFullName[3], massStrFullName[4]);
            default:
                return String.format("%s~%s", massStrFullName[0], massStrFullName[1]);
        }
    }

    public String toString() {
        return this.homeAddress == null | this.birthDate == null | this.gender == null ? String.format(" %s — %s \n", this.uid, this.fullName) : String.format(" %s — %s — %s — %s — %s \n", this.uid, this.fullName, this.gender, this.homeAddress, this.birthDate);
    }

    public Integer getUid() {
        return this.uid;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Gender getGender() {
        return this.gender;
    }

    public String getHomeAddress() {
        return this.homeAddress;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public Gender getGender(String gender) {
        gender = gender.toLowerCase();
        return gender == "male" ? Gender.MALE : Gender.FEMALE;
    }

    public Gender getGender(Gender gender) {
        return gender;
    }

    public String addGender(Gender gender) {
        return gender.toString().toLowerCase();
    }
}
