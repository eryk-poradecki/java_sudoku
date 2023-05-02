package it.comprog;

public interface Dao<T> {

    T read();

    void write(T object);
}
