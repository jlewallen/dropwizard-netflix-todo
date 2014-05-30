package com.page5of4.todo.api.internal;

import dagger.Provides;
import feign.Logger;

@dagger.Module(overrides = true, library = true, complete = false)
public class ApiFeignModule {
   @Provides
   Logger.Level logLevel() {
      return Logger.Level.BASIC;
   }

   @Provides
   Logger logger() {
      return new Logger() {
         @Override
         protected void log(String configKey, String format, Object... args) {
            RequestFactory.logger.info(String.format(format, args));
         }
      };
   }
}
