package com.page5of4.todo.edge;

import com.codahale.metrics.JmxReporter;
import com.page5of4.dropwizard.EurekaClientBundle;
import com.yammer.tenacity.core.bundle.TenacityBundleBuilder;
import dagger.ObjectGraph;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Main extends Application<EdgeConfiguration> {
   public static void main(String[] args) throws Exception {
      new Main().run(new String[] { "server", System.getProperty("dropwizard.config") });
   }

   @Override
   public void initialize(Bootstrap<EdgeConfiguration> bootstrap) {
      bootstrap.addBundle(new EurekaClientBundle());
      bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
      bootstrap.addBundle(TenacityBundleBuilder
         .newBuilder()
         .propertyKeyFactory(new CommandKeyFactory())
         .propertyKeys(CommandKeys.values())
         .build());
   }

   @Override
   public void run(EdgeConfiguration configuration, Environment environment) {
      JmxReporter.forRegistry(environment.metrics()).build().start();

      new EdgeTenacityPropertyRegister(configuration).register();

      ObjectGraph objectGraph = ObjectGraph.create(new EdgeModule(environment, configuration));
      environment.healthChecks().register("edge", objectGraph.get(EdgeHealthCheck.class));
      environment.jersey().register(objectGraph.get(TodoEdgeResource.class));
   }
}

