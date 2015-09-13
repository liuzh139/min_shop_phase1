/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author liuzh139
 */
public class ProductJdbcDAO implements ProductDAO {

   @Override
   public void save(Product product) {
      try (
              // get connection to database
              Connection connection = JdbcConnection.getConnection();
              // create the SQL statement
              PreparedStatement stmt = connection.prepareStatement(
                      "merge into products (id, name, description, category, price, stock) values (?,?,?,?,?,?)");) {
// copy the data from the product domain object into the statement
         stmt.setInt(1, product.getId());
         stmt.setString(2, product.getName());
         stmt.setString(3, product.getDescription());
         stmt.setString(4, product.getCategory());
         stmt.setDouble(5, product.getPrice());
         stmt.setDouble(6, product.getStock());
// execute the statement
         stmt.executeUpdate();
      } catch (SQLException ex) {
         throw new DAOException(ex.getMessage(), ex);
      }
   }

   @Override
   public void delete(Product product) {
      try {
         // get a connection to the database
         Connection connection = JdbcConnection.getConnection();
         // create the SQL statement
         PreparedStatement stmt =
                 connection.prepareStatement("delete from products where id = ?");
         // copy the data from the product domain object into the statement
         stmt.setInt(1, product.getId());
         // execute the query
         stmt.executeUpdate();
      } catch (SQLException ex) {
         throw new DAOException(ex.getMessage(), ex);
      }
   }

   @Override
   public Product getById(Integer ProductId) {
      Product p = null;
      try {
         // get a connection to the database
         Connection connection = JdbcConnection.getConnection();
         // create the SQL statement
         PreparedStatement stmt =
                 connection.prepareStatement("select * from products where id = ?");
         // copy the data from the student domain object into the statement
         stmt.setInt(1, ProductId);
         // execute the query
         ResultSet rs = stmt.executeQuery();
// We are using a list in order to preserve the order in which
// the data was returned from the SQL query.
// iterate through the query results
         if (rs.next()) {
// get the data out of the query
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String category = rs.getString("category");
            Double price = rs.getDouble("price");
            Double stock = rs.getDouble("stock");

// use the data to create a student object
            p = new Product(id, name, description, category, price, stock);
         }
         return p;
      } catch (SQLException ex) {
         throw new DAOException(ex.getMessage(), ex);
      }
   }

   @Override
   public Collection<String> getCategory() {
      try (
              // get a connection to the database
              Connection connection = JdbcConnection.getConnection();
              // create the SQL statement
              PreparedStatement stmt =
                      connection.prepareStatement("select distinct category from products order by category");
              // execute the query
              ResultSet rs = stmt.executeQuery();) {
// Create a collection for holding the student we get from the query.
// We are using a list in order to preserve the order in which
// the data was returned from the SQL query.
         Collection<String> categories = new ArrayList<>();
// iterate through the query results
         while (rs.next()) {
// get the data out of the query
            String category = rs.getString("category");
// and put it in the collection
            categories.add(category);
         }
         return categories;
      } catch (SQLException ex) {
         throw new DAOException(ex.getMessage(), ex);
      }
   }

   @Override
   public Collection<Product> getAll() {
      try (
              // get a connection to the database
              Connection connection = JdbcConnection.getConnection();
              // create the SQL statement
              PreparedStatement stmt =
                      connection.prepareStatement("select * from products order by id");
              // execute the query
              ResultSet rs = stmt.executeQuery();) {
// Create a collection for holding the student we get from the query.
// We are using a list in order to preserve the order in which
// the data was returned from the SQL query.
         Collection<Product> products = new ArrayList<>();
// iterate through the query results
         while (rs.next()) {
// get the data out of the query
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String category = rs.getString("category");
            Double price = rs.getDouble("price");
            Double stock = rs.getDouble("stock");

// use the data to create a student object
            Product p = new Product(id, name, description, category, price, stock);
// and put it in the collection
            products.add(p);
         }
         return products;
      } catch (SQLException ex) {
         throw new DAOException(ex.getMessage(), ex);
      }
   }

   @Override
   public Collection<Product> getByCategory(String category) {
      Collection<Product> getByCategory = new ArrayList();
      try {
         // get a connection to the database
         Connection connection = JdbcConnection.getConnection();
         // create the SQL statement
         PreparedStatement stmt =
                 connection.prepareStatement("select * from products where category = ?");
         // copy the data from the product domain object into the statement
         stmt.setString(1, category);
         // execute the query
         ResultSet rs = stmt.executeQuery();
// We are using a list in order to preserve the order in which
// the data was returned from the SQL query.
// iterate through the query results
         while (rs.next()) {
// get the data out of the query
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            Double price = rs.getDouble("price");
            Double stock = rs.getDouble("stock");

// use the data to create a product object
            Product p = new Product(id, name, description, category, price, stock);
            getByCategory.add(p);
         }
         return getByCategory;
      } catch (SQLException ex) {
         throw new DAOException(ex.getMessage(), ex);
      }
   }
}
