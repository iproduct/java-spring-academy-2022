package course.qa.dao;

public interface Identifiable<K> {
    K getId();
    void setId(K id);
}
