package com.page5of4.todo.api.commands;

import com.page5of4.todo.api.TodoViewModel;
import com.yammer.tenacity.core.TenacityCommand;

import java.util.Date;
import java.util.Random;

public class BulkAddTodo extends TenacityCommand<Integer> {
   private static final Random random = new Random();
   private final Integer number;
   private final Integer failurePercent;

   public BulkAddTodo(Integer number, Integer failurePercent) {
      super(CommandKeys.BULK_ADD_TODOS);
      this.number = number;
      this.failurePercent = failurePercent;
   }

   @Override
   protected Integer run() throws Exception {
      Date now = new Date();
      for(Integer i = 0; i < number; ++i) {
         TodoViewModel newTodo = new TodoViewModel();
         newTodo.setName("Bulk Todo #" + i + " B#" + now.getTime());
         boolean fail = (random.nextInt(100) + 1) < failurePercent;
         new AddTodo(newTodo, fail).queue();
      }
      return number;
   }
}
