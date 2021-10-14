package com.example.data_collection.entity;

public class Lists {
    // 属性
    private Integer st_id;
    private String st_name;
    private String c_name;
    private String st_pay;
    private String st_need;
    private String st_duration;
    private String c_start;
    private String c_end;
    private String c_address;

    // 构造方法
    public Lists() {
    }
    public Lists(Object st_id, Object st_name, Object c_name, Object st_pay, Object st_need, Object st_duration, Object c_start, Object c_end, Object c_address) {
        this.st_id = (Integer) st_id;
        this.st_name = (String) st_name;
        this.c_name = (String) c_name;
        this.st_pay = (String) st_pay;
        this.st_need = (String) st_need;
        this.st_duration = (String) st_duration;
        this.c_start = (String) c_start;
        this.c_end = (String) c_end;
        this.c_address = (String) c_address;
    }

    public String getC_start() {
        return c_start;
    }

    public void setC_start(String c_start) {
        this.c_start = c_start;
    }

    public String getC_end() {
        return c_end;
    }

    public void setC_end(String c_end) {
        this.c_end = c_end;
    }

    public String getC_address() {
        return c_address;
    }

    public void setC_address(String c_address) {
        this.c_address = c_address;
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

    public String getSt_need() {
        return st_need;
    }

    public void setSt_need(String st_need) {
        this.st_need = st_need;
    }

    public String getSt_duration() {
        return st_duration;
    }

    public void setSt_duration(String st_duration) {
        this.st_duration = st_duration;
    }
}
