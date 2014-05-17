package com.page5of4.todo.data;

import com.codahale.metrics.annotation.Timed;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
   private final TodoRepository repository;

   @Inject
   public TodoResource(TodoRepository repository) {
      this.repository = repository;
   }

   @Timed
   @GET
   public List<Todo> all() {
      return repository.all();
   }

   @Timed
   @POST
   @Path("/")
   public Todo add(String name) {
      return find(repository.add(name));
   }

   @Timed
   @GET @Path("/{id}")
   public Todo find(@PathParam("id") Long id) {
      return repository.findById(id);
   }
}
