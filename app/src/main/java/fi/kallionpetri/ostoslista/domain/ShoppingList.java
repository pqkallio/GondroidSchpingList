package fi.kallionpetri.ostoslista.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kallionpetri on 2.11.2016.
 */

public class ShoppingList {
    private List<Product> products;
    private String name;

    public ShoppingList(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void addProduct(String name, int amount) {
        this.products.add(new Product(name, amount));
    }

    public int size() {
        return products.size();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}
