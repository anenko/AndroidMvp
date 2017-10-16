package com.alex.pattern.interfaces;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Alex
 */

public interface IRepository<M> {

    void add(M item);

    void update(M item);

    Single<List<M>> getAll(int take, int skip);

    void delete(int id);
}
