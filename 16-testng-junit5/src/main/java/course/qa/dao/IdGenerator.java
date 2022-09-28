package course.qa.dao;

public interface IdGenerator<K> {
    K getNextId();
}
