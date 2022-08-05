package course.stream.sam;

import java.util.List;
import java.util.StringJoiner;

public class Person implements Comparable<Person>{
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private String email;
    private List<Phone> phones = List.of();
    private Address address;

    public Person() {
    }

    public Person(Long id) {
        this.id = id;
    }

    public Person(String firstName, String lastName, int age, Gender gender, String email, List<Phone> phones, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phones = phones;
        this.address = address;
    }

    public Person(Long id, String firstName, String lastName, int age, Gender gender, String email, List<Phone> phones, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phones = phones;
        this.address = address;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return getId() != null ? getId().equals(person.getId()) : person.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("age=" + age)
                .add("gender=" + gender)
                .add("email='" + email + "'")
                .add("phones=" + phones)
                .add("address=" + address)
                .toString();
    }

    @Override
    public int compareTo(Person other) {
        return this.getId().compareTo(other.getId());
    }
}
