package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class Order {
    int id;
    User user;
    Vector<Product> products = new Vector<>();
    Date dateOrdered;
    boolean complete;

    Order(User user) {
        this.user = user;
    }
    
    // add items to order (cart)
    public void addToCart(Product product) {
        this.products.add(product);
    }

    public void getCartItems() {
        if (this.products.size() > 0) {
            Print.print(this.products);
        } else {
            Print.print("your cart is empty...");
        }
    }

    /**
     * marks order as complete, updates date and saves info to DB
     */
    public void completeOrder() {
        if (this.products.size() > 0) {
            this.dateOrdered = new Date();
            this.complete = true;

            addOrderToFile();
        } else {
            Print.print("Make sure you add products to your cart first...");
        }
    }

    public void addOrderToFile() {
        String data = this.csvString(this);
        try {
            // Creates a Writer using FileWriter
            FileWriter writer = new FileWriter("./data/orders.csv", true);
            // Writes string and line seperator to the file
            writer.write(data);
            writer.write(System.getProperty("line.separator"));
            // Closes the writer
            writer.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public String csvString(Order order) {
        this.id = Utils.getNewId("./data/orders.csv");

        String lineSeperatedProductIds = "";
        for (Product product: order.products) {
            lineSeperatedProductIds += "|" + product.id;
        } 
        return "" + order.user.phone + "," + lineSeperatedProductIds  + "," +  order.dateOrdered  + "," +  order.complete;
    }

}

class Orders {
    Vector<Order> orders = new Vector<>();

    Orders() {
        File file = new File("./data/orders.csv");
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(file);
            // process the file, one line at a time
            while (fileScanner.hasNextLine()) {
                String[] line = fileScanner.nextLine().split(",");
                if (line[3].equals("true")) {
                    // Order order = new Product();
                    // this.orders.add(order);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
// view all orders (admin only)