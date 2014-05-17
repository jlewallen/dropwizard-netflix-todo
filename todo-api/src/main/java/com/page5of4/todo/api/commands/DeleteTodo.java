package com.page5of4.todo.api.commands;

import com.page5of4.todo.api.TodoViewModel;
import com.page5of4.todo.api.internal.RequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

public class DeleteTodo extends TenacityCommand<TodoViewModel> {
   private Integer id;

   public DeleteTodo(Integer id) {
      super(CommandKeys.DELETE_TODO);
      this.id = id;
   }

   @Override
   protected TodoViewModel run() throws Exception {
      return RequestFactory.data().deleteTodo(id);
   }
}
