package com.stu.sample.cp5;

import java.io.Serializable;

public class SerialData implements Serializable {
    private String name;
    private transient String password;
    private String email;
    public int age;
    public SerialData(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public String toString() { return "(" + name + ", " + password + ", " + email + ", " + age + ")"; }

}
