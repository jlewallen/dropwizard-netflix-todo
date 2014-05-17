package com.page5of4.todo.api.commands;

import com.page5of4.todo.api.TodoViewModel;
import com.yammer.tenacity.core.TenacityCommand;

import java.util.List;

public class GetTodos extends TenacityCommand<List<TodoViewModel>> {
   public GetTodos() {
      super(CommandKeys.GET_TODOS);
   }

   @Override
   protected List<TodoViewModel> run() throws Exception {
      return null;
   }
}
