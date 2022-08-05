package course.stream.sam;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static course.stream.sam.Gender.FEMALE;
import static course.stream.sam.Gender.MALE;
import static course.stream.sam.PhoneKind.*;

public class PersonsDemoMain {
    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person(1L, "John", "Doe", 35, MALE, "jdoe@gmail.com",
                        List.of(new Phone(WORK, "+(1) 435345345"), new Phone(WORK, "+(359) 2 987654"), new Phone(MOBILE, "+(359) 887 986543")),
                        new Address("Sofia", "Tzar Osvoboditel 15")),
                new Person(2L, "Jane", "Doe", 29, FEMALE, "jane.doe@gmail.com",
                        List.of(new Phone(WORK, "+(1) 5678889989"), new Phone(MOBILE, "+(1) 889 665756")),
                        new Address("United States", "New York", "Wall Street 152")),
                new Person(3L, "Gorge", "Harisson", 59, MALE, "georgeh@gmail.com",
                        List.of(new Phone(HOME, "+(44) 345345464"), new Phone(MOBILE, "+(44) 445 756756")),
                        new Address("Great Britain", "London", "Baker Steet 15")),
                new Person(4L, "Ivan", "Petrov", 39, MALE, "jdoe@gmail.com",
                        List.of(new Phone(WORK, "+(359) 2 345345"), new Phone(MOBILE, "+(44) 4534535")),
                        new Address("Sofia", "Hristo botev 54")),
                new Person(4L, "Nadezda", "Petrova", 39, FEMALE, "npetrova@gmail.com",
                    List.of(new Phone(WORK, "+(359) 2 345345"), new Phone(MOBILE, "+(44) 4534535"),
                        new Phone(MOBILE, "+(1) 4534353535")),
                        new Address("Sofia", "Hristo botev 54"))
        );
        var personsByAge = new ArrayList<>(persons);
//        personsByAge.sort(new Comparator<Person>() {
//            @Override
//            public int compare(Person p1, Person p2) {
//                return Integer.compare(p1.getAge(), p2.getAge());
//            }
//        });
        personsByAge.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
        personsByAge.sort(Comparator.comparingInt(Person::getAge));
        personsByAge.forEach(System.out::println);

//        var personsByCountryThenByFirstName = new ArrayList<>(persons);
//        personsByCountryThenByFirstName.sort(
//                Comparator.comparing((Person person) -> person.getAddress().getCountry())
//                        .thenComparing(Person::getFirstName)
//        );
//        personsByCountryThenByFirstName.forEach(System.out::println);

        var personsByCountryCodeThenByFirstName = new ArrayList<>(persons);
        personsByCountryCodeThenByFirstName.sort(Comparator
                .comparing((Person person) -> {
                    Optional<Map.Entry<Integer, Long>> max = person.getPhones().stream()
                            .map(phone -> {
                                String number = phone.getNumber();
                                return Integer.valueOf(number.substring(number.indexOf("(") + 1, number.lastIndexOf(")")));
                            })
                            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                            .entrySet().stream()
                            .max(Comparator.comparingLong(Map.Entry::getValue));
                    return max.get().getKey();
                }).thenComparing(Person::getFirstName)
        );
        personsByCountryCodeThenByFirstName.forEach(System.out::println);

    }
}
