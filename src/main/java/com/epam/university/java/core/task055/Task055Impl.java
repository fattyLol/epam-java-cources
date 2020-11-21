package com.epam.university.java.core.task055;

public class Task055Impl implements Task055{

    @Override
    public ProcessingContext createContext(String path) {
        return new ProcessingContextImpl(path);
    }
}
