package com.exampleytpeak.beans;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "StudentBean")

public class StudentBean {
    @Column(name = "id", isId = true, autoGen = true)
    private String id;
    @Column(name = "name")//
    private String name;
    @Column(name = "studentid")//
    private String studentid;
    @Column(name = "zhouci")//
    private String zhouci;
    @Column(name = "course")//
    private String course;
    @Column(name = "ischuqin")//
    private boolean ischuqin;

    public boolean isIschuqin() {
        return ischuqin;
    }

    public void setIschuqin(boolean ischuqin) {
        this.ischuqin = ischuqin;
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

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getZhouci() {
        return zhouci;
    }

    public void setZhouci(String zhouci) {
        this.zhouci = zhouci;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
