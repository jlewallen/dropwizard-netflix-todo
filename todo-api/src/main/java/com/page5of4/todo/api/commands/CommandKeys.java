package com.page5of4.todo.api.commands;

import com.yammer.tenacity.core.properties.TenacityPropertyKey;

public enum CommandKeys implements TenacityPropertyKey {
   ADD_TODO,
   GET_TODOS,
   DELETE_TODO,
   GET_TODO,
   BULK_ADD_TODOS
}
