package model.builder;

import model.Person;

public class PersonBuilder {
    private Person builded = new Person();

    public PersonBuilder() {
    }

    public PersonBuilder(String name) {
        setFirstAndLastNames(name);
    }

    public PersonBuilder setName(String name) {
        setFirstAndLastNames(name);
        return this;
    }

    public PersonBuilder setAge(int age) {
        builded.setAge(age);
        return this;
    }

    public PersonBuilder setId(Long id) {
        builded.setId(id);
        return this;
    }

    public Person build() {
        return builded;
    }

    // utility method
    protected void setFirstAndLastNames(String name) {
        String[] names = name.trim().split("\\s+");
        builded.setFirstName(names[0]);
        builded.setLastName(names[names.length-1]);
    }
}
