package course.qa.spring.dao;

public interface IdGenerator<K> {
    K getNextId();
}
