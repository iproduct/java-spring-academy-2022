package dao.impl;

import dao.IdGenerator;
import dao.Identifiable;
import dao.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositoryInMemoryImpl<K , V extends Identifiable<K>> implements Repository<K, V> {
    private Map<K, V> entities = new HashMap<>();

    public RepositoryInMemoryImpl(IdGenerator<K> idGenerator) {
        this.idGenerator = idGenerator;
    }

    private IdGenerator<K> idGenerator;

    @Override
    public Collection<V> findAll() {
        return entities.values();
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
        V old = entities.get(entity.getId());
        if(old == null) {
            return Optional.empty();
        }
        entities.put(entity.getId(), entity);
        return Optional.of(entity);
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
