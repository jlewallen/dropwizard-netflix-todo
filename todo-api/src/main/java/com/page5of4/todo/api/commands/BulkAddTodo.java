package com.page5of4.todo.api.commands;

import com.page5of4.todo.api.TodoViewModel;
import com.yammer.tenacity.core.TenacityCommand;

import java.util.Date;

public class BulkAddTodo extends TenacityCommand<Integer> {
   private Integer number;

   public BulkAddTodo(Integer number) {
      super(CommandKeys.ADD_TODO);
      this.number = number;
   }

   @Override
   protected Integer run() throws Exception {
      Date now = new Date();
      for(Integer i = 0; i < number; ++i) {
         TodoViewModel newTodo = new TodoViewModel();
         newTodo.setName("Bulk Todo #" + i + " B#" + now.getTime());
         new AddTodo(newTodo).execute();
      }
      return number;
   }
}
