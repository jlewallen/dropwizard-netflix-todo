package com.page5of4.todo.api.commands;

import com.page5of4.todo.api.TodoViewModel;
import com.page5of4.todo.api.internal.RequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

public class GetTodo extends TenacityCommand<TodoViewModel> {
   private final Integer id;

   public GetTodo(Integer id) {
      super(CommandKeys.GET_TODO);
      this.id = id;
   }

   @Override
   protected TodoViewModel run() throws Exception {
      return RequestFactory.middle().getTodo(id);
   }
}
