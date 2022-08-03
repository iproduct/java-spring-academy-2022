package dao;

public interface IdGenerator<K> {
    K getNextId();
}
