package database;

import java.util.Observable;
import java.util.UUID;

public abstract class Database<T> extends Observable
{
    public Database() {
    }

    public abstract void add(UUID id, T t);

    public abstract T get(UUID id);
}
