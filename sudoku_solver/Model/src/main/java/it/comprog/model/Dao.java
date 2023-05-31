package it.comprog.model;

import java.io.IOException;

public interface Dao<T> {

    T read() throws IOException, ClassNotFoundException;

    void write(T object) throws IOException;
}
