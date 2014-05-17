package com.page5of4.todo.edge;

import com.google.common.collect.ImmutableMap;
import com.yammer.tenacity.core.config.BreakerboxConfiguration;
import com.yammer.tenacity.core.config.TenacityConfiguration;
import com.yammer.tenacity.core.properties.TenacityPropertyKey;
import com.yammer.tenacity.core.properties.TenacityPropertyRegister;

public class EdgeTenacityPropertyRegister {
   private final EdgeConfiguration configuration;

   public EdgeTenacityPropertyRegister(EdgeConfiguration configuration) {
      this.configuration = configuration;
   }

   public void register() {
      final ImmutableMap.Builder<TenacityPropertyKey, TenacityConfiguration> builder = ImmutableMap.builder();

      BreakerboxConfiguration breakerboxConfiguration = new BreakerboxConfiguration();

      new TenacityPropertyRegister(builder.build(), breakerboxConfiguration).register();
   }
}
