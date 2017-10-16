package com.alex.pattern.interfaces;

/**
 * Created by Alex
 */

public interface IMapper<From, To> {

    To toRealm(From from);
    From fromRealm(To to);
}
