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
        synchronized (Stock.getStock()) {
            int prodForOnePurch = Stock.getStock().takeProduct(count);
            prodTaken += prodForOnePurch;
            if (!Stock.getStock().stockIsEmpty()) {
                purchNumber++;
            }
            if (!Stock.getStock().stockIsEmpty() && prodForOnePurch != 0 || Stock.getStock().stockIsEmpty() && prodForOnePurch != 0) {
                System.out.println("Покупатель " + custNumber + "; Всего куплено " + prodTaken +
                        "; Взято сейчас со склада: " + prodForOnePurch + "; на складе осталось " + Stock.getStock().getProductCount());
            }
        }
    }
}
