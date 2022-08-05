package course.stream.sam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.SIZED;

public class Nio2Lambda {
    static <A, B, C> Stream<C> zip(
            Stream<A> s1,
            Stream<B> s2,
            BiFunction<A, B, C> combiner) {
        final var i2 = s2.iterator();
        return s1.map(x1 -> i2.hasNext() ? combiner.apply(x1, i2.next()) : null)
                .takeWhile(Objects::nonNull);
    }

    public static <A, B, C> Stream<C> zip2(Stream<A> streamA, Stream<B> streamB, BiFunction<A, B, C> zipper) {
        Objects.requireNonNull(zipper);
        Spliterator<? extends A> aSpliterator = Objects.requireNonNull(streamA).spliterator();
        Spliterator<? extends B> bSpliterator = Objects.requireNonNull(streamB).spliterator();

        // Zipping looses DISTINCT and SORTED characteristics
        int characteristics = ((aSpliterator.characteristics() & bSpliterator.characteristics()
                & ~(Spliterator.DISTINCT | Spliterator.SORTED))
                | (aSpliterator.characteristics() & SIZED | bSpliterator.characteristics() & SIZED));

        long zipSize = (aSpliterator.getExactSizeIfKnown() >= 0) ?
                ((bSpliterator.getExactSizeIfKnown() >= 0) ?
                        Math.min(aSpliterator.estimateSize(), bSpliterator.estimateSize())
                        : aSpliterator.estimateSize())
                :bSpliterator.getExactSizeIfKnown();

        final Iterator<A> iteratorA = Spliterators.iterator(aSpliterator);
        final Iterator<B> iteratorB = Spliterators.iterator(bSpliterator);
        final Iterator<C> iteratorC = new Iterator<C>() {
            @Override
            public boolean hasNext() {
                return iteratorA.hasNext() && iteratorB.hasNext();
            }

            @Override
            public C next() {
                return zipper.apply(iteratorA.next(), iteratorB.next());
            }
        };
        Spliterator<C> split = zipSize > 0 ? Spliterators.spliterator(iteratorC, zipSize, characteristics):
                Spliterators.spliteratorUnknownSize(iteratorC, characteristics);
        return StreamSupport.stream(split, streamA.isParallel() || streamA.isParallel());
    }

    public static <T> Stream<T> iteratorToFiniteStream(Iterator<T> iterator, boolean parallel) {
        final Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }

    public static void main(String[] args) {
        var path = Paths.get("src/course/stream/sam/Nio2Lambda.java");
        try {
            var lines = Files.lines(path).parallel();//map(line -> line.toUpperCase());
            var numbers = IntStream.iterate(1, x -> x + 1).boxed().parallel();
            var results = zip(numbers, lines, (Integer n, String line) -> n + ": " + line);
            results.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        IntStream s = IntStream.of(1, 2, 3, 4);
//        long count = s.peek(System.out::println).limit(2).count();
//        System.out.println(count);
    }
}
