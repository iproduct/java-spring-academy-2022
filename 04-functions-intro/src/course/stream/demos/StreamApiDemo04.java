package course.stream.demos;

import course.stream.util.Tuple2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamApiDemo04 {


    public static void main(String[] args) throws IOException {
        List<String> list = Arrays.asList("hello1", "hello2", "hello3");
        long size = list.stream().skip(1)
                .map(element -> element.substring(0, 3)).distinct().count();
        System.out.println(size);

        AtomicInteger counter1 = new AtomicInteger();
        var result = list.stream()
                .map(element -> {
                    counter1.incrementAndGet();
                    System.out.println("Calling map #" + counter1);
                    return element.substring(0, 5);
                })
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(result);

        AtomicInteger counter2 = new AtomicInteger();
        var result2 = list.stream()
                .skip(2)
                .map(element -> {
                    counter2.incrementAndGet();
                    System.out.println("Calling map #" + counter2);
                    return element.substring(0, 5);
                }).collect(Collectors.toList());
        System.out.println(result2);

        // reducers
        OptionalInt reduced = IntStream.rangeClosed(1, 10).reduce((a, b) -> a + b);
        System.out.println("Reduced: " + reduced);

        int reducedWithInitVal = IntStream.rangeClosed(1, 10).reduce(1, (a, b) -> a * b);
        System.out.println("Reduced with initial value: " + reducedWithInitVal);

        int reducedParallel = IntStream.rangeClosed(1, 10000).boxed().parallel()
                .reduce(0,
                        (a, b) -> a + b,
                        (a, b) -> {
                            System.out.printf("combiner called for %s and %s%n", a, b);
                            return a + b;
                        });
        System.out.println("Reduced with accumulator and combiner: " + reducedParallel);


        System.out.println("\nNUMBERED LINES:");
        var path = Paths.get("src/course/stream/demos/StreamApiDemo04.java");
        String fResult = Files.lines(path)
                .reduce(new Tuple2<>("", 1),
                        (acc, line) -> // accumulator
                                new Tuple2<>(acc.getV1() + acc.getV2() + ": " + line + "\n", acc.getV2() + 1),
                        (acc1, acc2) -> // combimner
                                new Tuple2<>(acc1.getV1() + "\n" + acc2.getV2(), 0)).getV1();
        System.out.println(fResult);

//        Files.lines(path)
//                .collect(HashMap<Integer, String>::new, (map, line) -> map.put(map.size(), line), Map::putAll) // Create a map of the index to the object
//                .forEach((i, o) -> { // Now we can use a BiConsumer forEach!
//                    System.out.println(String.format("%d: %s", i+1, o));
//                });
    }
}
