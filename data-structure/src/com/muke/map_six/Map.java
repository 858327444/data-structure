package com.muke.map_six;

/**
 * 映射Map
 * Program Name: data-structure
 * Created by yanlp on 2018/10/18
 *
 * @author yanlp
 * @version 1.0
 */
public interface Map<K, V> {
    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    int getSize();

    boolean isEmpty();
}
