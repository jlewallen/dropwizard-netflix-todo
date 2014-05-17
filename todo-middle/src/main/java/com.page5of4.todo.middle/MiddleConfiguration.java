package com.page5of4.todo.middle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.page5of4.dropwizard.ConfiguresEurekaClient;
import com.page5of4.dropwizard.EurekaClientConfiguration;
import com.yammer.tenacity.core.config.TenacityConfiguration;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MiddleConfiguration extends Configuration implements ConfiguresEurekaClient {
   @Valid
   @NotNull
   @JsonProperty
   private TenacityConfiguration tenacity = new TenacityConfiguration();

   @Valid
   @NotNull
   @JsonProperty
   private EurekaClientConfiguration eureka = new EurekaClientConfiguration();

   public TenacityConfiguration getTenacity() {
      return tenacity;
   }

   public EurekaClientConfiguration getEureka() {
      return eureka;
   }
}

