package com.page5of4.todo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Todo {
   @JsonProperty
   private Long id;
   @JsonProperty
   private String name;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Todo() {
   }

   public Todo(Long id, String name) {
      this.id = id;
      this.name = name;
   }
}
