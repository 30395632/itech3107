package com.exampleytpeak.beans;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

@Table(name = "CourseBean")
public class CourseBean implements Serializable {
    @Column(name = "id", isId = true, autoGen = true)
    private String id;
    @Column(name = "name")//
    private String name;
    @Column(name = "starttime")//
    private String starttime;
    @Column(name = "enttime")//
    private String enttime;
    @Column(name = "zhouci")//
    private String zhouci;

    public String getZhouci() {
        return zhouci;
    }

    public void setZhouci(String zhouci) {
        this.zhouci = zhouci;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEnttime() {
        return enttime;
    }

    public void setEnttime(String enttime) {
        this.enttime = enttime;
    }
}
