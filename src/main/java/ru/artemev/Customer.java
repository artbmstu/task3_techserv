package ru.artemev;

class Customer {
    private int custNumber;
    private int prodTaken;
    private int purchNumber;

    Customer(int custNumber){
        this.custNumber = custNumber;
    }

    int getCustNumber() {
        return custNumber;
    }

    int getProdTaken() {
        return prodTaken;
    }

    int getPurchNumber() {
        return purchNumber;
    }

    void takeProduct(int count){
        int prodForOnePurch = Stock.getStock().takeProduct(count, custNumber);
        prodTaken += prodForOnePurch;
        if (!Stock.getStock().stockIsEmpty()) {
            purchNumber++;
        }
    }
}
