package com.reactiveejb.application.controller.rest;

import com.reactiveejb.application.controller.UserService;
import com.reactiveejb.application.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Stateless
@Path("/userResource")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    public void userResource(@Suspended AsyncResponse response) {
        try {
            Future<User> resUserFuture = userService.getUser();

            while (!resUserFuture.isDone()) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            }

            response.resume(Response.ok(resUserFuture.get()).build());
        } catch (InterruptedException | ExecutionException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
