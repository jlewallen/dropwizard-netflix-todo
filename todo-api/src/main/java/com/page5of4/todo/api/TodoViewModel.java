package com.page5of4.todo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoViewModel {
   public Long id;
   public String name;

   @JsonProperty
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   @JsonProperty
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
