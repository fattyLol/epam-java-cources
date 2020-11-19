package com.epam.university.java.core.task060;

public class CacheFactoryImpl implements CacheFactory {
    @Override
    public Cache newInstance(int size) {
        return new CacheImpl(size);
    }
}
