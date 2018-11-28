package com.muke.segmenttree_eight;

/**
 * 融合器
 */
public interface Merger<E> {
    E merge(E a, E b);
}
