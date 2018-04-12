package com.reactiveejb.application.controller;

import com.reactiveejb.application.model.User;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Stateless
public class UserService {

    @Asynchronous
    public Future<User> getUser() {
        long id = new Date().getTime();
        User user = new User("User " + id, id);
        return new AsyncResult<>(user);
    }

    @Asynchronous
    public void doSomeSlowStuff(User user) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
