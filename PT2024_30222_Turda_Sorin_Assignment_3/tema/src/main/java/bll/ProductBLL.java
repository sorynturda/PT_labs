package bll;

import bll.validators.QuantityValidator;
import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private ProductDAO productDAO;
	private List<Validator<Product>> validators;


    public ProductBLL() {
        validators = new ArrayList<>();
        validators.add(new QuantityValidator());
        productDAO = new ProductDAO();
    }

    public Product findProductById(int id) {
        Product pr = productDAO.findById(id);
        if (pr == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return pr;
    }

    /**
     * Calls the method to find all products in the product table
     * @return List of product
     */
    public List<Product> findProducts() {
        List<Product> list = productDAO.findAll();
        if (list == null) {
            throw new NoSuchElementException("The client table is empty!");
        }
        return list;
    }

    /**
     * Updates the item in table
     * @param product
     * @return
     */
    public int updateProduct(Product product){
        return productDAO.update(product).getId();
    }
    public int insertProduct(Product product) {
        for(Validator<Product> v : validators)
            v.validate(product);
        return productDAO.insert(product).getId();
    }
    public int deleteProduct(Product product) {
        return productDAO.delete(product).getId();
    }
}
