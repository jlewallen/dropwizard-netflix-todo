package com.page5of4.todo.edge;

import com.codahale.metrics.health.HealthCheck;

import javax.inject.Inject;

public class EdgeHealthCheck extends HealthCheck {
   @Inject
   public EdgeHealthCheck() {
   }

   @Override
   protected Result check() throws Exception {
      return Result.healthy();
   }
}
