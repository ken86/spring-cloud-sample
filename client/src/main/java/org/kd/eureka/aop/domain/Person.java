package org.kd.eureka.aop.domain;

public class Person {

    private String name;
    private String idStr;

    public Person(String name, String idStr) {
        this.name = name;
        this.idStr = idStr;
    }

    public String getName() {
        return name;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public void setName(String name) {
        this.name = name;
    }
}
