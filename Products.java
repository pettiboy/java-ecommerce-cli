package UML;

import java.util.Vector;

public class Products {
    private Vector<String> products = new Vector<>();

    public Vector<String> getProducts() {
        return this.products;
    }

    public void addProduct() {
        this.products.add("Product 1");
    }
}
