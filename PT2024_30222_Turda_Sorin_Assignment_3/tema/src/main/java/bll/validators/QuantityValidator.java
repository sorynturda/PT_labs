package bll.validators;

import model.Product;

public class QuantityValidator implements Validator<Product> {
    private static final int MIN_QTY = 0;
    private static final int MAX_QTY = 50;

    @Override
    public void validate(Product product) {
        if (product.getQuantity() < MIN_QTY || product.getQuantity() > MAX_QTY)
            throw new IllegalArgumentException("Enter a valid quantity");
    }
}
