package UML;

import java.util.Scanner;
import java.util.Vector;

public class Products {
    private Vector<Product> products = new Vector<>();

    public Vector<Product> getProducts() {
        return this.products;
    }

    public void addProduct(Scanner scanner) {
        // int id = Utils.getIntInRange("Product ID: ", 1, 1000, scanner);
        int id = this.products.size() + 1;
        String name = Utils.getStringInRange("Product Name: ", 1, 1000, scanner);
        Double price = Utils.getDoubleInRange("Product Price: ", 1.0, 1000.0, scanner);

        Product product = new Product(id, name, price);
        this.products.add(product);
    }
}

class Product {
    Integer id;
    String name;
    Double price;

    Product(Integer id, String name, double d) {
        this.id = id;
        this.name = name;
        this.price = d;
    }

    public String toString() {
        return "ID: " + this.id.toString() + "\n" + "Name: " + this.name + "\n" + "Price: " + this.price.toString();
    }
}