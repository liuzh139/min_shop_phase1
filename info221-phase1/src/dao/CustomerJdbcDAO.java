/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author liuzh139
 */
public class CustomerJdbcDAO implements CustomerDAO {

   @Override
   public void save(Customer customer) {
      try (
              // get connection to database
              Connection connection = JdbcConnection.getConnection();
              // create the SQL statement
              PreparedStatement stmt = connection.prepareStatement(
                      "merge into customer (username, name, address, carddetails, password) values (?,?,?,?,?)");) {
// copy the data from the product domain object into the statement
         stmt.setString(1, customer.getUsername());
         stmt.setString(2, customer.getName());
         stmt.setString(3, customer.getAddress());
         stmt.setString(4, customer.getCardDetails());
         stmt.setString(5, customer.getPassoword());
// execute the statement
         stmt.executeUpdate();
      } catch (SQLException ex) {
         throw new DAOException(ex.getMessage(), ex);
      }
   }
}
