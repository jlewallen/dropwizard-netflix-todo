package com.page5of4.todo.data;

import com.codahale.metrics.JmxReporter;
import com.page5of4.dropwizard.EurekaClientBundle;
import dagger.ObjectGraph;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Main extends Application<DataConfiguration> {
   public static void main(String[] args) throws Exception {
      new Main().run(new String[] { "server", System.getProperty("dropwizard.config") });
   }

   @Override
   public void initialize(Bootstrap<DataConfiguration> bootstrap) {
      bootstrap.addBundle(new EurekaClientBundle());
   }

   @Override
   public void run(DataConfiguration configuration, Environment environment) throws ClassNotFoundException {
      JmxReporter.forRegistry(environment.metrics()).build().start();

      ObjectGraph objectGraph = ObjectGraph.create(new DataModule(environment, configuration));
      environment.healthChecks().register("data", objectGraph.get(DataHealthCheck.class));
      environment.jersey().register(objectGraph.get(TodoResource.class));
   }
}

