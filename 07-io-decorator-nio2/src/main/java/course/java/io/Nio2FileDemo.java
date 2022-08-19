package course.java.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Nio2FileDemo {
    public static void main(String[] args) {
        Path p1 = Paths.get("./../07-io-decorator-nio2/src/main/java/course/java/io/Nio2FileDemo.java");
        Path p2 = p1.toAbsolutePath();
        System.out.println(p2);
        System.out.println("Is absolute: " + p2.isAbsolute());
        System.out.println("Exists: " + Files.exists(p2));
        Path p3 = p2.normalize();
        System.out.println("Normalized: " + p3);
        Path p4 = Paths.get("./src/main/java/course/java/model/Reading.java").toAbsolutePath();
        Path relativeFromP3toP4 = p3.relativize(p4);
        System.out.println(relativeFromP3toP4);
        AtomicInteger i = new AtomicInteger();

        try {
//            System.out.println(
//                    Files.lines(p3).map(line -> (i.incrementAndGet()) + ": " + line)
//                    .collect(Collectors.joining("\n"))
//            );
                    Files.lines(p3).map(line -> (i.incrementAndGet()) + ": " + line)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
