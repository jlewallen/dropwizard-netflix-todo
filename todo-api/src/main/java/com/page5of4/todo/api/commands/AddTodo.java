package com.page5of4.todo.api.commands;

import com.page5of4.todo.api.TodoViewModel;
import com.page5of4.todo.api.internal.RequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

public class AddTodo extends TenacityCommand<TodoViewModel> {
   private final TodoViewModel todo;
   private final boolean fail;

   public AddTodo(TodoViewModel todo, boolean fail) {
      super(CommandKeys.ADD_TODO);
      this.todo = todo;
      this.fail = fail;
   }

   @Override
   protected TodoViewModel run() throws Exception {
      if(fail) {
         throw new RuntimeException("Failure");
      }
      RequestFactory.data().addTodo(todo);
      return todo;
   }
}
