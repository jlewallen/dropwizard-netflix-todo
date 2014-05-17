package com.page5of4.todo.api.internal;

public class RequestFactory {
   private static TodoDataRequests data;
   private static TodoMiddleRequests middle;

   public RequestFactory() {
      // this.todo = Feign.create(LoadBalancingTarget.create(TodoDataRequests.class, "http://todo-data"), new GsonModule());
      // this.middle = Feign.create(LoadBalancingTarget.create(TodoMiddleRequests.class, "http://todo-middle"), new GsonModule());
   }

   public static TodoDataRequests data() {
      return data;
   }

   public static TodoMiddleRequests middle() {
      return middle;
   }
}
