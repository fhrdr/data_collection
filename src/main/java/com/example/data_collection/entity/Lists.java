package com.example.data_collection.entity;

public class Lists {
    // 属性
    private Integer st_id;
    private String st_name;
    private String c_name;
    private String st_pay;
    private Integer st_need;
    private String st_duration;

    // 构造方法
    public Lists() {
    }
    public Lists(Object st_id, Object st_name, Object c_name, Object st_pay, Object st_need, Object st_duration) {
        this.st_id = (Integer) st_id;
        this.st_name = (String) st_name;
        this.c_name = (String) c_name;
        this.st_pay = (String) st_pay;
        this.st_need = (Integer) st_need;
        this.st_duration = (String) st_duration;
    }

    public Integer getSt_id() {
        return st_id;
    }

    public void setSt_id(Integer st_id) {
        this.st_id = st_id;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getSt_pay() {
        return st_pay;
    }

    public void setSt_pay(String st_pay) {
        this.st_pay = st_pay;
    }

    public Integer getSt_need() {
        return st_need;
    }

    public void setSt_need(Integer st_need) {
        this.st_need = st_need;
    }

    public String getSt_duration() {
        return st_duration;
    }

    public void setSt_duration(String st_duration) {
        this.st_duration = st_duration;
    }
}
