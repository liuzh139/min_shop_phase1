/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.ProductCollectionDAO;
import dao.ProductDAO;
import domain.Product;
import java.util.Collection;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author liuzh139
 */
public class DaoTest {

   private ProductDAO dao = new ProductCollectionDAO();
   private int muffinId = 11111;
   private Product muffin;
   private int juiceId = 22222;
   private Product juice;

   public DaoTest() {
   }

   @Before
   public void setUp() {
      muffin = new Product(muffinId, "Muffin", "Cupcake", "Snack", 4, 10);
      juice = new Product(juiceId, "Jill", "Orange juice", "Soft Drink", 4, 20);
      dao.save(muffin);
      dao.save(juice);
   }

   @After
   public void tearDown() {
      dao.delete(muffin);
      dao.delete(juice);
   }

   @Test
   public void testDaoSaveAndDelete() {
// create student for testing
      Product candybar = new Product(33333, "CandyBar", "Strawberry flavour", "Snack", 2, 10);
// save the student using DAO
      dao.save(candybar);
// retreive the same student via DAO
      Product retrieved = dao.getById(33333);
// ensure that the student we saved is the one we got back
      assertEquals("Retrieved product should be the same as the saved one",
              candybar, retrieved);
// delete the student via the DAO
      dao.delete(candybar);
// try to retrieve the deleted student
      retrieved = dao.getById(33333);
// ensure that the student was not retrieved (should be null)
      assertNull("Student should no longer exist", retrieved);
   }

   @Test
   public void testDaoGetAll() {
// call getAll
      Collection<Product> products = dao.getAll();
// ensure the result includes the test students
      assertTrue("Jack should exist in the result", products.contains(muffin));
      assertTrue("Jill should exist in the result", products.contains(juice));
// find Jack .. result is not a map, so we have to sequentially search
      for (Product s : products) {
         if (s.equals(muffin)) {
// ensure that Jack’s details were correctly retrieved
            assertEquals(muffin.getId(), s.getId());
            assertEquals(muffin.getName(), s.getName());
            assertEquals(muffin.getCategory(), s.getCategory());
            assertEquals(muffin.getDescription(), s.getDescription());
            assertEquals(muffin.getPrice(), s.getPrice());
            assertEquals(muffin.getStock(), s.getStock());

         }
      }
   }

   @Test
   public void testDaoGetById() {
      Collection<Product> products = dao.getAll();
// get Jack using getById method
      Product retrieved = dao.getById(muffinId);
// ensure that we got back Jack and not another student
      assertTrue("Muffin should exist in the result", products.contains(retrieved));
// ensure that Jack’s details were properly retrieved (which
// indirectly tests that the details were properly saved).
      for (Product s : products) {
         if (s.equals(retrieved)) {
// ensure that Jack’s details were correctly retrieved
            assertEquals(retrieved.getId(), s.getId());
            assertEquals(retrieved.getName(), s.getName());
            assertEquals(retrieved.getCategory(), s.getCategory());
            assertEquals(retrieved.getDescription(), s.getDescription());
            assertEquals(retrieved.getPrice(), s.getPrice());
            assertEquals(retrieved.getStock(), s.getStock());
         }
      }
// call getById using a non-existent ID
      Product nonExist = dao.getById(123456);
// ensure that the result is null
      assertNull("This one should not exist.", nonExist);
   }
   // TODO add test methods here.
   // The methods must be annotated with annotation @Test. For example:
   //
   // @Test
   // public void hello() {}
}
