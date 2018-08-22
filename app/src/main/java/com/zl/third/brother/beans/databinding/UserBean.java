package com.zl.third.brother.beans.databinding;

/**
 * Created by longzhao on 2018/5/29.
 */

public class UserBean {

    private String name;
    private String password;

    public UserBean(String name,String password) {
      this.name = name;
      this.password=password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
