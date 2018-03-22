package ru.artemev;

class Customer {
    private int custNumber;

    Customer(int custNumber){
        this.custNumber = custNumber;
    }

    void takeProduct(int count){
        Stock.getStock().takeProduct(count, custNumber);
    }
}
