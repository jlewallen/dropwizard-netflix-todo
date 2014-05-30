package com.page5of4.todo.data;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(TodoMapper.class)
public interface TodoRepository {
   @SqlUpdate("INSERT INTO todo (name) values (:name)")
   @GetGeneratedKeys
   long add(@Bind("name") String name);

   @SqlQuery("SELECT * FROM TODO WHERE id = :id")
   Todo findById(@Bind("id") long id);

   @SqlUpdate("DELETE FROM TODO")
   void deleteAllTodos();

   @SqlUpdate("DELETE FROM TODO WHERE id = :id")
   void deleteById(@Bind("id") long id);

   @SqlQuery("SELECT * FROM todo")
   List<Todo> all();

   @SqlQuery("SELECT COUNT(*) FROM todo")
   Integer numberOfTodos();
}
