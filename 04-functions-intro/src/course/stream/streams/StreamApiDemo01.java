package course.stream.streams;

import course.stream.util.Tuple2;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static course.stream.util.StreamUtil.zip;

public class StreamApiDemo01 {
    public static <T> Stream<T> createStream(Collection<T> values){
        return values == null ? Stream.empty() : values.stream();
    }
    public static void main(String[] args) {
        var empty = Stream.empty();
        empty.forEach(System.out::println);

//        var strStream = createStream(List.of("hello", "functional", "java", "stream", "api"))
//                .forEach(System.out::println);
//        createStream(null)
//                .forEach(System.out::println);

        var stringStream = Stream.of("hello", "functional", "java", "stream", "api");
//        stringStream.forEach(System.out::println);

        String[] strArray = {"hello", "functional", "java", "stream", "api"};
//        Arrays.stream(strArray, 1,4).forEach(System.out::println);

//        var builder = Stream.<String>builder().add("A").add("B").add("C");
//        stringStream.forEach(builder::add);
//        builder.build().forEach(System.out::println);

        var randomStream = Stream.generate(() -> Math.random()).limit(100);
//        randomStream.forEach(System.out::println);

        var counterStream = IntStream.iterate(1, i -> i + 1).boxed(); //.mapToObj(Integer::valueOf); //.limit(100).skip(10);
//        var counterStream2 = IntStream.rangeClosed(1, 100);
//        var countedRandoms = zip(randomStream, counterStream, (val, count) -> count + ": " + val);
//        countedRandoms.forEach(System.out::println);

//        var counter = new AtomicLong();
//        var countedRandoms2 = new Random().doubles(100).boxed()
//                .parallel()
//                .map(val -> new Tuple2<>(counter.incrementAndGet(), val))
//                .collect(Collectors.toList());
//
//        var countedRandomsSorted = countedRandoms2.stream()
//                .sorted(Comparator.comparing(Tuple2::getV1))
//                .map(tuple -> tuple.getV1() + ": " + tuple.getV2());
//        countedRandomsSorted.forEach(System.out::println);

        var chars = stringStream
                .flatMapToInt(str -> str.chars())
                .mapToObj(Character::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(chars);

    }
}
