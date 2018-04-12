package com.reactiveejb.application.model;

import java.io.Serializable;

public class User implements Serializable {

    private String user;
    private long id;

    public User(String user, long id) {
        this.user = user;
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", id=" + id +
                '}';
    }
}
