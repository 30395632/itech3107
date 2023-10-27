package com.exampleytpeak.beans;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 */
@Table(name = "LoginBean")
public class LoginBean implements Serializable {//用户表
    @Column(name = "zhanghao", isId = true, autoGen = true)
    private String zhanghao;
    @Column(name = "password")//
    private String password;

    public String getZhanghao() {
        return zhanghao;
    }

    public void setZhanghao(String zhanghao) {
        this.zhanghao = zhanghao;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
