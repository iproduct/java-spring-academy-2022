package course.stream.reduce;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiReduction01 {
    public static boolean isPrime(int n) {
//        var divider = IntStream.rangeClosed(2, (int)Math.sqrt(n)).filter(i -> n % i == 0).findAny();
//        return divider.isPresent();
        return !IntStream.rangeClosed(2, (int)Math.sqrt(n)).anyMatch(i -> n % i == 0);

    }

    public static void main(String[] args) {

        var stringStream = Stream.of("hello", "functional", "java", "stream", "api");
        var intStream = IntStream.iterate(2, i -> i + 1);
        var primes = intStream.filter(StreamApiReduction01::isPrime).limit(100);
//        System.out.println(
//                primes.mapToObj(Integer::toString).collect(Collectors.joining(", ","[", "]")));
//        stringStream.takeWhile(val -> !val.equalsIgnoreCase("java"))
//                .forEach(System.out::println);

//        System.out.printf("Count of words: %s%n", stringStream.count());
//        System.out.printf("Sum lengths of all words: %s%n", stringStream.mapToInt(String::length).sum());
//        System.out.printf("Sum lengths of all words: %s%n", stringStream.mapToInt(String::length).average());
//        System.out.printf("Sum lengths of all words: %s%n", stringStream.mapToInt(String::length).min().orElse(-1));
//        System.out.printf("Sum lengths of all words: %s%n",
//                Stream.<String>empty().mapToInt(String::length)
//                        .max()
//                        .orElseGet(
//                                ()-> primes.skip(41).findFirst().getAsInt()
//                        )
//        );

        var sum = IntStream.rangeClosed(1,10).boxed().parallel().reduce(
                0,
                (a, v) -> {
                    System.out.printf("Reducing [%s]: %d and %d%n", Thread.currentThread().getName(), a, v);
                    return a + v;
                }, //reducer
                (a1, a2) -> {
                    System.out.printf("Combining accumulators [%s]: %d and %d%n",
                            Thread.currentThread().getName(), a1, a2);
                    return a1 + a2;
                });
        System.out.printf("Sum[%s] = %d%n", Thread.currentThread().getName(), sum);


//        var chars = stringStream
//                .flatMapToInt(str -> str.chars())
//                .mapToObj(Character::toString)
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        System.out.println(chars);

    }
}
