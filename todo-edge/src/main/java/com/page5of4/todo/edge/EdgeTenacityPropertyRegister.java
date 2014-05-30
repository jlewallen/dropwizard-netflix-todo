package com.page5of4.todo.edge;

import com.google.common.collect.ImmutableMap;
import com.page5of4.todo.api.commands.CommandKeys;
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

      builder.put(CommandKeys.ADD_TODO, configuration.getTenacity());
      builder.put(CommandKeys.BULK_ADD_TODOS, configuration.getTenacity());
      builder.put(CommandKeys.GET_TODO, configuration.getTenacity());
      builder.put(CommandKeys.GET_TODOS, configuration.getTenacity());
      builder.put(CommandKeys.DELETE_TODO, configuration.getTenacity());
      builder.put(CommandKeys.DELETE_ALL_TODOS, configuration.getTenacity());

      BreakerboxConfiguration breakerboxConfiguration = new BreakerboxConfiguration();
      new TenacityPropertyRegister(builder.build(), breakerboxConfiguration).register();
   }
}