package start;

import bll.ProductBLL;
import model.Client;
import bll.ClientBLL;
import model.Product;
import presentation.MainController;
import presentation.View;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {

		View view = new View();
		MainController controller = new MainController(view);
		view.setVisible(true);

	}
	
	

}
