package com.page5of4.todo.middle;

import com.codahale.metrics.JmxReporter;
import com.page5of4.dropwizard.EurekaClientBundle;
import com.yammer.tenacity.core.bundle.TenacityBundleBuilder;
import dagger.ObjectGraph;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Main extends Application<MiddleConfiguration> {
   public static void main(String[] args) throws Exception {
      new Main().run(new String[] { "server", System.getProperty("dropwizard.config") });
   }

   @Override
   public void initialize(Bootstrap<MiddleConfiguration> bootstrap) {
      bootstrap.addBundle(new EurekaClientBundle());
      bootstrap.addBundle(TenacityBundleBuilder
         .newBuilder()
         .propertyKeyFactory(new CommandKeyFactory())
         .propertyKeys(CommandKeys.values())
         .build());
   }

   @Override
   public void run(MiddleConfiguration configuration, Environment environment) {
      JmxReporter.forRegistry(environment.metrics()).build().start();

      new MiddleTenacityPropertyRegister(configuration).register();

      ObjectGraph objectGraph = ObjectGraph.create(new MiddleModule(environment, configuration));
      environment.healthChecks().register("middle", objectGraph.get(MiddleHealthCheck.class));
      environment.jersey().register(objectGraph.get(TodoMiddleResource.class));
   }
}

