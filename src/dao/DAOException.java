/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author liuzh139
 */
public class DAOException extends RuntimeException {

   public DAOException(String message) {
      super(message);
   }

   public DAOException(String message, Throwable cause) {
      super(message, cause);
   }
}
