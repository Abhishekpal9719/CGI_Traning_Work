package controller;

import model.Product;
import service.CartService;

public class Main {
    public static void main(String[] args) {
        CartService cartService = new CartService();

        // Create products
        Product p1 = new Product(1, "Laptop", 50000);
        Product p2 = new Product(2, "Phone", 20000);
        Product p3 = new Product(3, "Headphones", 2000);

        // Add products to cart
        cartService.addProduct(p1, 1);
        cartService.addProduct(p2, 2);
        cartService.addProduct(p3, 3);

        // Display cart items
        System.out.println("Cart Items:");
        cartService.getCartItems().forEach(System.out::println);

        // Update quantity
        cartService.updateProductQuantity(2, 1);
        System.out.println("\nAfter updating quantity of Phone:");
        cartService.getCartItems().forEach(System.out::println);

        // Remove product
        cartService.removeProduct(3);
        System.out.println("\nAfter removing Headphones:");
        cartService.getCartItems().forEach(System.out::println);

        // Total price
        System.out.println("\nTotal Price: " + cartService.calculateTotalPrice());
    }
}
