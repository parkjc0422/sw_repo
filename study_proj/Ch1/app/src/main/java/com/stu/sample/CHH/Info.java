package com.chh.ttt;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Info extends RealmObject {

    @PrimaryKey
    private String name;
    private String pNum;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getpNum() {
        return pNum;
    }
    public void setpNum(String pNum) {
        this.pNum = pNum;
    }
}
