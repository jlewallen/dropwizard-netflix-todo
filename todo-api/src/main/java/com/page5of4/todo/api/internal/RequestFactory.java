package com.page5of4.todo.api.internal;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSModule;
import org.slf4j.LoggerFactory;

public class RequestFactory {
   static final org.slf4j.Logger logger = LoggerFactory.getLogger(RequestFactory.class);

   private static TodoDataRequests data;
   private static TodoMiddleRequests middle;

   public RequestFactory() {
   }

   public static void initialize() {
      Feign.Builder feignBuilder = Feign.builder()
         .contract(new JAXRSModule.JAXRSContract())
         .encoder(new JacksonEncoder())
         .decoder(new JacksonDecoder())
         .logger(new Logger() {
            @Override
            protected void log(String configKey, String format, Object... args) {
               logger.info(String.format(format, args));
            }
         })
         .logLevel(Logger.Level.BASIC);

      data = feignBuilder.target(TodoDataRequests.class, "http://127.0.0.1:9000");
      middle = feignBuilder.target(TodoMiddleRequests.class, "http://127.0.0.1:9010");
   }

   public static TodoDataRequests data() {
      return data;
   }

   public static TodoMiddleRequests middle() {
      return middle;
   }
}
