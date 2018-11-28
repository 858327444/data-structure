package com.muke.set_five;

/**
 * 集合类
 * Program Name: data-structure
 * Created by yanlp on 2018/10/18
 *
 * @author yanlp
 * @version 1.0
 */
public interface Set<E> {
    void add(E e);

    void remove(E e);

    boolean isEmpty();

    int getSize();

    boolean contains(E e);
}
