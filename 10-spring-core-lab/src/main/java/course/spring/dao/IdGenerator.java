package course.spring.dao;

@FunctionalInterface
public interface IdGenerator<K> {
    K getNextId();
}
