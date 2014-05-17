package com.page5of4.todo.api.internal;

import com.page5of4.todo.api.TodoViewModel;
import feign.Headers;
import feign.RequestLine;

import javax.inject.Named;
import java.util.List;

public interface TodoDataRequests {
   @RequestLine("GET /todos")
   public List<TodoViewModel> getTodos();

   @RequestLine("POST /todos")
   @Headers("Content-type: application/json")
   public String addTodo(TodoViewModel todo);

   @RequestLine("DELETE /todos/{id}")
   public TodoViewModel deleteTodo(@Named("id") Integer id);
}
