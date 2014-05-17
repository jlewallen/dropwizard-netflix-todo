package com.page5of4.todo.api.commands;

import com.page5of4.todo.api.TodoViewModel;
import com.page5of4.todo.api.internal.RequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

import java.util.List;

public class GetArchivedTodos extends TenacityCommand<List<TodoViewModel>> {
   protected GetArchivedTodos() {
      super(CommandKeys.GET_ARCHIVED_TODOS);
   }

   @Override
   protected List<TodoViewModel> run() throws Exception {
      return RequestFactory.middle().getArchivedTodos();
   }
}
