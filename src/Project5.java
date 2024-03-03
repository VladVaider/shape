import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Project5 {
    public static void main(String[] args) {
        Stream<Integer> first = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> second = Stream.of(6, 7, 8);

        Stream<Integer> zipped = zip(first, second);
        zipped.forEach(System.out::println);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();

        Iterator<T> iterator = new Iterator<T>() {
            boolean firstStreamHasNext = iterator1.hasNext();
            boolean secondStreamHasNext = iterator2.hasNext();

            @Override
            public boolean hasNext() {
                return firstStreamHasNext && secondStreamHasNext;
            }

            @Override
            public T next() {
                T nextValue = null;
                if (firstStreamHasNext) {
                    nextValue = iterator1.next();
                    firstStreamHasNext = iterator1.hasNext();
                }

                if (secondStreamHasNext) {
                    if (nextValue == null) {
                        nextValue = iterator2.next();
                    } else {
                        T secondNextValue = iterator2.next();
                        if (secondNextValue != null) {
                            nextValue = secondNextValue;
                        }
                    }
                    secondStreamHasNext = iterator2.hasNext();
                }
                return nextValue;
            }
        };

        // Створюємо стрім з отриманого ітератора
        Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED);
        return StreamSupport.stream(spliterator, false);
    }
}
