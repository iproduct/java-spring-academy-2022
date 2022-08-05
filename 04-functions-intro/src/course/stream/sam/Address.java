package course.stream.sam;

import java.util.StringJoiner;

public class Address {
    private String country = "Bulgaria";
    private String city;
    private String streetAdress;

    public Address() {
    }

    public Address(String city, String streetAdress) {
        this.city = city;
        this.streetAdress = streetAdress;
    }

    public Address(String country, String city, String streetAdress) {
        this.country = country;
        this.city = city;
        this.streetAdress = streetAdress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAdress() {
        return streetAdress;
    }

    public void setStreetAdress(String streetAdress) {
        this.streetAdress = streetAdress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (!country.equals(address.country)) return false;
        if (!city.equals(address.city)) return false;
        return streetAdress.equals(address.streetAdress);
    }

    @Override
    public int hashCode() {
        int result = country.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + streetAdress.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
                .add("country='" + country + "'")
                .add("city='" + city + "'")
                .add("streetAdress='" + streetAdress + "'")
                .toString();
    }
}
