package service;

import model.CartItem;
import model.Product;
import java.util.ArrayList;
import java.util.List;

public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity));
    }

    public void updateProductQuantity(int productId, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == productId) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    public void removeProduct(int productId) {
        cartItems.removeIf(item -> item.getProduct().getId() == productId);
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
