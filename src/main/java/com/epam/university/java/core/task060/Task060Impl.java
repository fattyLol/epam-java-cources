package com.epam.university.java.core.task060;

public class Task060Impl implements Task060 {
    @Override
    public Cache createCache(int size) {
        CacheFactory factory = new CacheFactoryImpl();
        return factory.newInstance(size);
    }

    @Override
    public void set(Cache cache, int key, String value) {
        if (cache == null) {
            throw new IllegalArgumentException();
        }
        cache.setNode(key, value);
    }

    @Override
    public String get(Cache cache, int key) {
        if (cache == null) {
            throw new IllegalArgumentException();
        }
        return cache.getNode(key);
    }
}
