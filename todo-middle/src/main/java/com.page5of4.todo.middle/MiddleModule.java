package com.page5of4.todo.middle;

import dagger.Module;
import io.dropwizard.setup.Environment;

@Module(injects = { TodoMiddleResource.class, MiddleHealthCheck.class })
public class MiddleModule {
   private final Environment environment;
   private final MiddleConfiguration configuration;

   public MiddleModule(Environment environment, MiddleConfiguration configuration) {
      this.environment = environment;
      this.configuration = configuration;
   }
}
