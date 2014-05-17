package com.page5of4.todo.api.internal;

import com.page5of4.todo.api.TodoViewModel;
import feign.RequestLine;

import javax.inject.Named;
import java.util.List;

public interface TodoMiddleRequests {
   @RequestLine("GET /todos/{id}")
   public TodoViewModel getTodo(@Named("id") Integer id);

   @RequestLine("GET /todos/archived")
   public List<TodoViewModel> getArchivedTodos();
}
