package com.page5of4.todo.edge;

import com.yammer.tenacity.core.TenacityCommand;

public class AlwaysSucceed extends TenacityCommand<String> {
   public AlwaysSucceed() {
      super(CommandKeys.ALWAYS_SUCCEED);
   }

   @Override
   protected String run() throws Exception {
      return "success";
   }
}
