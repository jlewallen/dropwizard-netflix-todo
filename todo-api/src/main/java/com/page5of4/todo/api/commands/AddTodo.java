package com.page5of4.todo.api.commands;

import com.page5of4.todo.api.TodoViewModel;
import com.page5of4.todo.api.internal.RequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

public class AddTodo extends TenacityCommand<TodoViewModel> {
   private final TodoViewModel todo;

   public AddTodo(TodoViewModel todo) {
      super(CommandKeys.ADD_TODO);
      this.todo = todo;
   }

   @Override
   protected TodoViewModel run() throws Exception {
      RequestFactory.data().addTodo(todo);
      return todo;
   }
}
