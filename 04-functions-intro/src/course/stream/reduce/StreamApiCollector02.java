package course.stream.reduce;

import java.util.Random;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiCollector02 {
    public static boolean isPrime(int n) {
//        var divider = IntStream.rangeClosed(2, (int)Math.sqrt(n)).filter(i -> n % i == 0).findAny();
//        return divider.isPresent();
        return !IntStream.rangeClosed(2, (int)Math.sqrt(n)).anyMatch(i -> n % i == 0);

    }

    public static void main(String[] args) {
        var treeSetCollector = Collector.of(
                TreeSet<Double>::new,  // supplier
                TreeSet<Double>::add, //accumulator
                (left, right) -> { left.addAll(right); return left; }, //combiner
                (TreeSet<Double> tsResult) -> tsResult.stream().map(d -> d.toString()) //finisher
                        .collect(Collectors.joining(", ")));

        var result = new Random().doubles(10).boxed().parallel()
                .collect(treeSetCollector);
        System.out.println(result);


//        var chars = stringStream
//                .flatMapToInt(str -> str.chars())
//                .mapToObj(Character::toString)
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        System.out.println(chars);

    }
}
