package com.example.data_collection.entity;

import javax.persistence.Entity;
import java.util.Date;

public class StudentStationInfo {
    private String sName;
    private String sClass;
    private String sDepartment;
    private String stName;
    private String cName;
    private Date ssTime;


    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getsDepartment() {
        return sDepartment;
    }

    public void setsDepartment(String sDepartment) {
        this.sDepartment = sDepartment;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Date getSsTime() {
        return ssTime;
    }

    public void setSsTime(Date ssTime) {
        this.ssTime = ssTime;
    }

    @Override
    public String toString() {
        return "StudentStationInfo{" +
                "sName='" + sName + '\'' +
                ", sClass='" + sClass + '\'' +
                ", sDepartment='" + sDepartment + '\'' +
                ", stName='" + stName + '\'' +
                ", cName='" + cName + '\'' +
                ", ssTime=" + ssTime +
                '}';
    }

}
