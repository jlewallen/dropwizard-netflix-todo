package com.page5of4.todo.middle;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoMiddleResource {
   @Inject
   public TodoMiddleResource() {
   }

   @GET
   public String success() {
      return new AlwaysSucceed().execute();
   }
}
