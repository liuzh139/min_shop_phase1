/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.ProductDAO;
import domain.Product;
import gui.ProductReportDialog;
import gui.helpers.SimpleListModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import static org.fest.swing.core.matcher.DialogMatcher.withTitle;
import static org.fest.swing.core.matcher.JButtonMatcher.withText;
import org.fest.swing.fixture.DialogFixture;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

/**
 *
 * @author liuzh139
 */
public class ProductReportDialogTest {

   private Collection<Product> products;
   private Collection<Product> getByCategory;
   private ProductDAO dao;
   private Product muffin;
   private Product juice;
   private ProductReportDialog dialog;
   private DialogFixture fest;

   public ProductReportDialogTest() {
   }

   @Before
   public void setUp() {
      // create some students for testing with
      muffin = new Product(11111, "Muffin", "Cupcake", "Snack", 5.00, 10);
      juice = new Product(22222, "Juice", "Orange", "SoftDrink", 4.00, 20);
// add the products to collection for testing with
      products = new TreeSet<>();
      products.add(muffin);
      products.add(juice);
      
      getByCategory = new TreeSet<>();
      getByCategory.add(muffin);
      
      Collection<String> cats = new TreeSet<>();
      cats.add("Snack");
      cats.add("SoftDrink");
      
// create a mock for the DAO
      dao = mock(ProductDAO.class);
// stub the getAll method to return the test students collection
      when(dao.getAll()).thenReturn(products);
// stub the getById method to return the appropriate test student based on the passed ID
      when(dao.getById(11111)).thenReturn(muffin);
      when(dao.getById(22222)).thenReturn(juice);
      when(dao.getByCategory(muffin.getCategory())).thenReturn(getByCategory);
      when(dao.getCategory()).thenReturn(cats);
      
      
// create dialog passing mocked DAO
      dialog = new ProductReportDialog(null, true, dao);
   }

   @After
   public void tearDown() {
      //clean up FEST so that it is ready for the next test
      
      if(fest != null) {
         fest.cleanUp();
      }
   }

   @Test
   public void testReportView() {
// use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
// slow down the FEST robot a bit .. default is 30 millis
      fest.robot.settings().delayBetweenEvents(75);
// ensure getAll was called
      verify(dao).getAll();
// get the JList’s model
      SimpleListModel model = (SimpleListModel) fest.list().component().getModel();
// check that the model actually contains the students
      assertTrue("Ensure list contain Muffin", model.contains(muffin));
      assertTrue("Ensure list contain Juice", model.contains(juice));
      assertTrue("Ensure list contains the correct number of products",
              model.getSize() == products.size());
   }
   @Test
   public void testReportDelete() {
// use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
// slow down the FEST robot a bit 
      fest.robot.settings().delayBetweenEvents(75);
// select Jill in the list
      fest.list().selectItem(muffin.toString());
// click the delete button
      fest.button("btnDelete").click();
// ensure a confirmation dialog is displayed
      DialogFixture confirmDialog = fest.dialog(withTitle("Select an Option")
              .andShowing()).requireVisible();
// click the No button on the confirmation dialog
      confirmDialog.button(withText("No")).click();
// verify that the delete method did NOT get called on the mock DAO
      verify(dao, never()).delete(null);
// select Jill again
      fest.list().selectItem(muffin.toString());
// click the delete button again
      fest.button("btnDelete").click();
// ensure a confirmation dialog is displayed again
      confirmDialog = fest.dialog(withTitle("Select an Option")
              .andShowing()).requireVisible();
// click the Yes button on the confirmation dialog
      confirmDialog.button(withText("Yes")).click();
// create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
      ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
// verify that the DAO.save method was called, and capture the passed student
      verify(dao).delete(argument.capture());
// retrieve the passed student from the captor
      Product deletedProduct = argument.getValue();
// ensure that the correct student was deleted
      assertEquals("Deleted student should be muffin", muffin, deletedProduct);
   }

   @Test
   public void testReportSearch() {
// use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
// slow down the FEST robot a bit 
      fest.robot.settings().delayBetweenEvents(75);
// TODO: enter Jack’s ID into the search box
      fest.textBox("txtSearch").selectAll().enterText(juice.getId().toString());
// TODO: click the search button
      fest.button("btnSearch").click();
// ensure that getById was called and was passed Jack’s ID
      verify(dao).getById(juice.getId());
// TODO: get the JList’s model
      SimpleListModel model = (SimpleListModel) fest.list().component().getModel();
// TODO: ensure that the list is displaying ONLY Jack
      assertTrue("Ensure list contain juice", model.contains(juice));
      assertTrue(model.getSize() == 1);
   }

   @Test
   public void testReportEdit() {
// use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
// slow down the FEST robot a bit 
      fest.robot.settings().delayBetweenEvents(75);
// TODO: select Jill in the list
      fest.list().selectItem(muffin.toString());
// TODO: click the edit button
      fest.button("btnEdit").click();
// ensure that the StudentDialog appears
      DialogFixture editDialog = fest.dialog("productDialog").requireVisible();
// TODO: ensure the StudentDialog contains the correct student
      //check if jill's id match's id from the text box? 
// (make sure the ID text field has Jill’s ID in it)
      editDialog.textBox("txtId").requireText(muffin.getId().toString());
   }

   @Test
   public void testCategoryFilter() {
      // use FEST to control the dialog
      fest = new DialogFixture(dialog);
      fest.show();
      // slow down the FEST robot a bit 
      fest.robot.settings().delayBetweenEvents(75);
      fest.comboBox("cmbCategory").selectItem(muffin.getCategory());

      verify(dao).getByCategory(muffin.getCategory());
      // TODO: get the JList’s model
      SimpleListModel model = (SimpleListModel) fest.list().component().getModel();
      // TODO: ensure that the list is displaying muffin.
      assertTrue("Ensure list contain muffin", model.contains(muffin));
      assertTrue(model.getSize() == getByCategory.size());
   }
}
