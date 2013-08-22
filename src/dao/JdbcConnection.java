package dao;

import java.sql.Connection;
import java.sql.SQLException;
import org.h2.jdbcx.JdbcConnectionPool;

/**
 *
 * @author liuzh139
 */
public class JdbcConnection {

   private static final String url = "jdbc:h2:tcp://localhost/project/products";
   private static final String username = "sa";
   private static final String password = "";

   private static JdbcConnectionPool pool = JdbcConnectionPool.create(url, username, password);

   public static Connection getConnection() {
      try {
         return pool.getConnection();
      } catch (SQLException ex) {
         throw new RuntimeException(ex);
      }
   }
}