package com.page5of4.todo.middle;

import com.google.common.collect.ImmutableMap;
import com.yammer.tenacity.core.config.BreakerboxConfiguration;
import com.yammer.tenacity.core.config.TenacityConfiguration;
import com.yammer.tenacity.core.properties.TenacityPropertyKey;
import com.yammer.tenacity.core.properties.TenacityPropertyRegister;

public class MiddleTenacityPropertyRegister {
   private final MiddleConfiguration configuration;

   public MiddleTenacityPropertyRegister(MiddleConfiguration configuration) {
      this.configuration = configuration;
   }

   public void register() {
      final ImmutableMap.Builder<TenacityPropertyKey, TenacityConfiguration> builder = ImmutableMap.builder();

      builder.put(CommandKeys.ALWAYS_SUCCEED, configuration.getTenacity());

      BreakerboxConfiguration breakerboxConfiguration = new BreakerboxConfiguration();

      new TenacityPropertyRegister(builder.build(), breakerboxConfiguration).register();
   }
}
