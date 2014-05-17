package com.page5of4.todo.data;

import dagger.Module;
import dagger.Provides;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

@Module(injects = { TodoResource.class, DataHealthCheck.class })
public class DataModule {
   private final Environment environment;
   private final DataConfiguration configuration;

   public DataModule(Environment environment, DataConfiguration configuration) {
      this.environment = environment;
      this.configuration = configuration;
   }

   @Provides
   public DBI dbi() {
      try {
         return new DBIFactory().build(environment, configuration.getDataSourceFactory(), "db");
      }
      catch(ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   @Provides
   public TodoRepository repository(DBI dbi) {
      try(Handle h = dbi.open()) {
         h.execute("CREATE TABLE todo (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100))");
      }
      return dbi.onDemand(TodoRepository.class);
   }
}
