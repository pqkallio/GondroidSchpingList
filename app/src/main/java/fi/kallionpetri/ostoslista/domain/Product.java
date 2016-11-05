package fi.kallionpetri.ostoslista.domain;

/**
 * Created by kallionpetri on 2.11.2016.
 */

public class Product {
    private String name;
    private int amount;

    public Product(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
}
