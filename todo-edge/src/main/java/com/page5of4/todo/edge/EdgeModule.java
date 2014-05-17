package com.page5of4.todo.edge;

import dagger.Module;
import io.dropwizard.setup.Environment;

@Module(injects = { TodoEdgeResource.class, EdgeHealthCheck.class })
public class EdgeModule {
   private final Environment environment;
   private final EdgeConfiguration configuration;

   public EdgeModule(Environment environment, EdgeConfiguration configuration) {
      this.environment = environment;
      this.configuration = configuration;
   }
}
