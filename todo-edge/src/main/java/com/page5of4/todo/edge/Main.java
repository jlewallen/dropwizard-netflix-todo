package com.page5of4.todo.edge;

import com.codahale.metrics.JmxReporter;
import com.page5of4.dropwizard.EurekaClientBundle;
import com.page5of4.dropwizard.discovery.DiscoveryMetadataProvider;
import com.page5of4.todo.api.commands.CommandKeyFactory;
import com.page5of4.todo.api.commands.CommandKeys;
import com.yammer.tenacity.core.bundle.TenacityBundleBuilder;
import dagger.ObjectGraph;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Main extends Application<EdgeConfiguration> {
   public static void main(String[] args) throws Exception {
      new Main().run(new String[] { "server", System.getProperty("dropwizard.config") == null ? "src/main/resources/todo-edge.yml" : System.getProperty("dropwizard.config") });
   }

   @Override
   public void initialize(Bootstrap<EdgeConfiguration> bootstrap) {
      System.setProperty("eureka.metadata.jms.broker", "BROKER");

      bootstrap.addBundle(new EurekaClientBundle(Arrays.<DiscoveryMetadataProvider>asList(new CountingMetadataProvider())));
      bootstrap.addBundle(new AssetsBundle("/assets/", "/assets/", "index.html"));
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

   public static class CountingMetadataProvider implements DiscoveryMetadataProvider {
      private final AtomicLong counter = new AtomicLong();

      @Override
      public Map<String, String> getMetadata() {
         return Collections.singletonMap("counter", Long.toString(counter.addAndGet(1)));
      }
   }
}

