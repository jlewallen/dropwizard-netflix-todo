package com.page5of4.todo.middle;

import com.codahale.metrics.health.HealthCheck;

import javax.inject.Inject;

public class MiddleHealthCheck extends HealthCheck {
   @Inject
   public MiddleHealthCheck() {
   }

   @Override
   protected Result check() throws Exception {
      return Result.healthy();
   }
}
