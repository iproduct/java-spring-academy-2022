package dao;

import java.util.Collection;
import java.util.Optional;

public interface Repository<K, V extends Identifiable<K>> {
    Collection<V> findAll();
    Optional<V> findById(K id);
    V create(V entity);
    Optional<V> update(V entity);
    Optional<V> deleteById(K id);
    long count();
}
