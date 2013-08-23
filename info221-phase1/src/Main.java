
import dao.ProductCollectionDAO;
import dao.ProductDAO;
import dao.ProductJdbcDAO;
import gui.MainMenu;
import java.awt.EventQueue;

public class Main {

   static ProductDAO dao = new ProductJdbcDAO();

	/**
    *
    * @param args
    */
   @SuppressWarnings("UseSpecificCatch")
	public static void main(String[] args) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainMenu(dao).setVisible(true);
			}
			
		});
	}
}
