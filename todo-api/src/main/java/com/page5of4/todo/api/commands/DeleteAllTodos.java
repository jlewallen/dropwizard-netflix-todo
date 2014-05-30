package com.page5of4.todo.api.commands;

import com.page5of4.todo.api.internal.RequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

public class DeleteAllTodos extends TenacityCommand<Integer> {

   public DeleteAllTodos() {
      super(CommandKeys.DELETE_ALL_TODOS);
   }

   @Override
   protected Integer run() throws Exception {
      return RequestFactory.data().deleteAllTodos();
   }
}
