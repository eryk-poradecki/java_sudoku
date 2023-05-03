package it.comprog.model;

public interface Dao<T> {

    T read();

    void write(T object);
}
