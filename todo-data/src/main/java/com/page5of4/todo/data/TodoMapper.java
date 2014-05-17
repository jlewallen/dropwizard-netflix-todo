package com.page5of4.todo.data;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoMapper implements ResultSetMapper<Todo> {
   public Todo map(int index, ResultSet r, StatementContext ctx) throws SQLException {
      return new Todo(r.getLong("id"), r.getString("name"));
   }
}
