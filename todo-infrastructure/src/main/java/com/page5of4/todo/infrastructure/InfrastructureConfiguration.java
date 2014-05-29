package com.page5of4.todo.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.page5of4.dropwizard.discovery.zookeeper.ConfiguresZooKeeper;
import com.page5of4.dropwizard.discovery.zookeeper.ZooKeeperConfiguration;
import com.page5of4.dropwizard.eureka.server.ConfiguresEurekaServer;
import com.page5of4.dropwizard.eureka.server.EurekaServerConfiguration;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class InfrastructureConfiguration extends Configuration implements ConfiguresEurekaServer, ConfiguresZooKeeper {
   @Valid
   @NotNull
   @JsonProperty("eurekaServer")
   private final EurekaServerConfiguration eurekaServer = new EurekaServerConfiguration();

   @Override
   public EurekaServerConfiguration getEurekaServer() {
      return eurekaServer;
   }

   @Valid
   @NotNull
   @JsonProperty("zookeeper")
   private final ZooKeeperConfiguration zookeeper = new ZooKeeperConfiguration();

   @Override
   public ZooKeeperConfiguration getZooKeeper() {
      return zookeeper;
   }
}

