package src;

import java.util.Date;
import java.util.Vector;

public class Order {
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
            Utils.print(this.products);
        } else {
            Utils.print("your cart is empty...");
        }
    }

    public void completeOrder() {
        if (this.products.size() > 0) {
            this.dateOrdered = new Date();
            this.complete = true;
        } else {
            Utils.print("Make sure you add products to your cart first...");
        }
    }
}

class Orders {}
// view all orders (admin only)