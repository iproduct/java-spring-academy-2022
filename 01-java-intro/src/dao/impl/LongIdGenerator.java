package dao.impl;

import dao.IdGenerator;

public class LongIdGenerator implements IdGenerator<Long> {
    private long nextId = 0;
    @Override
    public Long getNextId() {
        return ++nextId;
    }
}
