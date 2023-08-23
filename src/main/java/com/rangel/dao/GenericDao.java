package com.rangel.dao;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDao<T, I extends Serializable> {

    public abstract void save(T obj);

    public abstract List<T> list();

    public abstract void edit(T obj);

    public abstract void delete(I id);
}
