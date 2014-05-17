package com.page5of4.todo.edge;

import com.page5of4.todo.api.TodoViewModel;
import com.page5of4.todo.api.commands.AddTodo;
import com.page5of4.todo.api.commands.DeleteTodo;
import com.page5of4.todo.api.commands.GetTodo;
import com.page5of4.todo.api.commands.GetTodos;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoEdgeResource {
   @Inject
   public TodoEdgeResource() {
   }

   @GET
   public List<TodoViewModel> all() {
      return new GetTodos().execute();
   }

   @GET
   @Path("/{id}")
   public TodoViewModel get(@PathParam("id") Integer id) {
      return new GetTodo(id).execute();
   }

   @DELETE
   @Path("/{id}")
   public TodoViewModel delete(@PathParam("id") Integer id) {
      return new DeleteTodo(id).execute();
   }

   @POST
   public TodoViewModel add(TodoViewModel todoViewModel) {
      return new AddTodo(todoViewModel).execute();
   }
}
