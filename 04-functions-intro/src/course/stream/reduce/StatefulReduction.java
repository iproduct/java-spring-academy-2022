package course.stream.reduce;

import course.stream.util.Tuple2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatefulReduction {
    public static void main(String[] args) throws IOException {
        var path = Paths.get("src/course/stream/reduce/StatefulReduction.java");
        Files.lines(path) //.parallel()
                .collect(
                        ArrayList<Tuple2<Integer, String>>::new, // supplier
                        (list, line) -> list.add(new Tuple2<>(list.size(), line)), //accumulator
                        ArrayList::addAll) // Create a map of the index to the object
                .forEach((t) -> { // Now we can use a BiConsumer forEach!
                    System.out.println(String.format("%d: %s", t.getV1(), t.getV2()));
                });
    }
}
