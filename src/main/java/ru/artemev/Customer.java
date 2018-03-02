package ru.artemev;

public class Customer {
    private int products;
    private int custNumber;
    private int count;

    Customer(int custNumber){
        this.custNumber = custNumber;
    }

    public int getCount() {
        return count;
    }

    public int getCustNumber() {
        return custNumber;
    }

    public int getProducts() {
        return products;
    }

    void takeProduct(int count, Stock stock){
        this.count = count;
        products += stock.takeProduct(count);
    }
}
