package course.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositoryMemoryImpl<V extends Identifiable<K>, K> implements Repository<V, K> {
    private long nextId = 0;
    private Map<K, V> entities = new HashMap<>();
    private IdGenerator<K> idGenerator;

    public RepositoryMemoryImpl() {
    }

    public RepositoryMemoryImpl(IdGenerator<K> idGenerator) {
        this.idGenerator = idGenerator;
    }

    public IdGenerator<K> getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(IdGenerator<K> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public Collection<V> findAll() {
        return entities.values();
    }

    @Override
    public Optional<V> findById(K id) {
        return Optional.ofNullable(entities.get(id)); // O(1)
    }

    @Override
    public V create(V entity) {
        entity.setId(idGenerator.getNextId());
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public V update(V entity) {
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<V> deleteById(K id) {
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public long count() {
        return entities.size();
    }
}
