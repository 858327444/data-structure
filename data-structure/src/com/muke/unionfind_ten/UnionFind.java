package com.muke.unionfind_ten;

/**
 * 并查集
 * Program Name: data-structure
 * Created by yanlp on 2018/12/5
 *
 * @author yanlp
 * @version 1.0
 */
public interface UnionFind {
    /**
     * 获取个数.
     *
     * @return
     */
    int getSize();

    /**
     * 索引为p 和 索引为q ,两个元素是否连接
     *
     * @param p
     * @param q
     * @return
     */
    boolean isConnected(int p, int q);

    /**
     * 将索引为p 和 索引为q 的两个元素,连接在一起
     *
     * @param p
     * @param q
     */
    void unionElements(int p, int q);
}
