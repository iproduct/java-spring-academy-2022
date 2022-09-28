package course.qa.dao.impl;

import course.qa.dao.IdGenerator;
import course.qa.dao.Identifiable;
import course.qa.dao.Repository;

import java.util.*;

public class RepositoryMemoryImpl<K, V extends Identifiable<K>> implements Repository<K, V> {
    private Map<K, V> entities = new HashMap<K, V>();
    private IdGenerator<K> idGenerator;

    public RepositoryMemoryImpl(IdGenerator<K> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public List<V> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public Optional<V> findById(K id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public V create(V entity) {
        entity.setId(idGenerator.getNextId());
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<V> update(V entity) {
        var old = findById(entity.getId());
        if(old.isPresent()){
            entities.put(entity.getId(), entity);
            return Optional.of(entity);
        }
        return Optional.empty();
    }

    @Override
    public Optional<V> deleteById(K id) {
        var old = findById(id);
        if(old.isPresent()){
            entities.remove(id);
            return old;
        }
        return Optional.empty();
    }

    @Override
    public long count() {
        return entities.size();
    }
}
