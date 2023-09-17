package com.example.myapplication.DataCenter;

import java.io.Serializable;

/**
 * 救援者信息
 */
public class RescuerData implements Serializable {
    private int _sid; //唯一识别id
    private int _age; //年龄
    private String _name; //姓名
    private String _desc; //详情

    public int getSid() {
        return _sid;
    }

    public void setSid(int sid) {
        _sid = sid;
    }

    public int getAge() {
        return _age;
    }

    public void setAge(int age) {
        _age = age;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDesc() {
        return _desc;
    }

    public void setDesc(String desc) {
        _desc = desc;
    }
}
