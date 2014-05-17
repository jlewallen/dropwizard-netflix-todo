package com.page5of4.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Todo {
   @JsonProperty
   public final Long id;
   @JsonProperty
   public final String name;

   public Todo(Long id, String name) {
      this.id = id;
      this.name = name;
   }
}
