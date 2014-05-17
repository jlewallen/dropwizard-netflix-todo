package com.page5of4.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.page5of4.dropwizard.ConfiguresEurekaClient;
import com.page5of4.dropwizard.EurekaClientConfiguration;
import com.yammer.tenacity.core.config.TenacityConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DataConfiguration extends Configuration implements ConfiguresEurekaClient {
   @Valid
   @NotNull
   @JsonProperty
   private TenacityConfiguration tenacity = new TenacityConfiguration();

   @Valid
   @NotNull
   @JsonProperty
   private DataSourceFactory database = new DataSourceFactory();

   @Valid
   @NotNull
   @JsonProperty
   private EurekaClientConfiguration eureka = new EurekaClientConfiguration();

   public DataSourceFactory getDataSourceFactory() {
      return database;
   }

   public TenacityConfiguration getTenacity() {
      return tenacity;
   }

   public EurekaClientConfiguration getEureka() {
      return eureka;
   }
}


