package com.muke.array_zero;

import java.io.Serializable;

/**
 * Program Name: Array
 * Created by yanlp on 2018/10/11
 *
 * @author yanlp
 * @version 1.0
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 5804058169280153078L;
    public String name;
    public int score;

    public Student() {
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("Student (name: %s,score: %d)", name, score);
    }

    public static void main(String[] args) {
        Array<Student> list = new Array<>();
        list.addLast(new Student("小红", 90));
        list.addLast(new Student("小李", 88));
        list.addLast(new Student("小王", 99));
        System.out.println(list);

    }
}
