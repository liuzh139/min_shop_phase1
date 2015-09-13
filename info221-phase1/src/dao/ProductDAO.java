/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author liuzh139
 */
public interface ProductDAO {

   void save(Product product);

   void delete(Product product);

   Product getById(Integer ProductId);

   Collection<String> getCategory();

   Collection<Product> getAll();

   Collection<Product> getByCategory(String category);
}
