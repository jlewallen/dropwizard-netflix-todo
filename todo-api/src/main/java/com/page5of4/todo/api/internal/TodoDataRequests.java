package com.page5of4.todo.api.internal;

import com.page5of4.todo.api.TodoViewModel;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.List;

public interface TodoDataRequests {
   @GET
   @Path("/todos")
   public List<TodoViewModel> getTodos();

   @POST
   @Path("/todos")
   @Consumes(MediaType.APPLICATION_JSON)
   public TodoViewModel addTodo(TodoViewModel todo);

   @DELETE
   @Path("/todos")
   public Integer deleteAllTodos();

   @DELETE
   @Path("/todos/{id}")
   public TodoViewModel deleteTodo(@Named("id") Integer id);

   @GET
   @Path("/todos/{id}")
   public TodoViewModel getTodo(@Named("id") Integer id);
}
