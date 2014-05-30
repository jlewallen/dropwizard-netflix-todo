package com.page5of4.todo.api.internal;

import feign.Feign;
import feign.jackson.JacksonModule;
import feign.jaxrs.JAXRSModule;
import feign.ribbon.LoadBalancingTarget;
import org.slf4j.LoggerFactory;

public class RequestFactory {
   static final org.slf4j.Logger logger = LoggerFactory.getLogger(RequestFactory.class);

   private static TodoDataRequests data;
   private static TodoMiddleRequests middle;

   public RequestFactory() {
   }

   public static void initialize() {
      data = Feign.create(LoadBalancingTarget.create(TodoDataRequests.class, "http://todo-data-api"), new JacksonModule(), new JAXRSModule(), new ApiFeignModule());
      middle = Feign.create(LoadBalancingTarget.create(TodoMiddleRequests.class, "http://todo-middle-api"), new JacksonModule(), new JAXRSModule(), new ApiFeignModule());
   }

   public static TodoDataRequests data() {
      return data;
   }

   public static TodoMiddleRequests middle() {
      return middle;
   }
}
