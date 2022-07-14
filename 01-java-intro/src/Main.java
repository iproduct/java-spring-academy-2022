import model.Person;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person(1L, "John", "Doe", 35);
        System.out.println(p1);
        Person p2 = new Person(2L, "Jane", "Doe", 25);
        System.out.println(p2);
        System.out.printf("Are p1 and p2 equal? -> %b%n", p1.equals(p2));
        Person p3 = new Person(2L, "Jane", "Doe", 26);
        System.out.println(p2);
        System.out.printf("Are p2 and p3 equal? -> %b%n", p2.equals(p3));
        System.out.printf("Are p2.hashcode = %d and p3.hashcode = %d%n", p2.hashCode(), p3.hashCode());
        System.out.printf("Are p2.identityHashcode = %d and p3.identityHashcode = %d%n",
                System.identityHashCode(p2), System.identityHashCode(p3));
        System.out.printf("Are p2 == p3? -> %b%n", p2 == p3);
    }
}
