package com.sam.model;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Person {

    private int personId;
    @NotBlank(message = "Name cannot be blank")
    private String name;

    public Person(){}

    public Person(int personId, String name){
        this.personId = personId;
        this.name = name;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                '}';
    }
}
