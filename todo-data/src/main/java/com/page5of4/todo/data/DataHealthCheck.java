package com.page5of4.todo.data;

import com.codahale.metrics.health.HealthCheck;

import javax.inject.Inject;

public class DataHealthCheck extends HealthCheck {
   @Inject
   public DataHealthCheck() {
   }

   @Override
   protected Result check() throws Exception {
      return Result.healthy();
   }
}
