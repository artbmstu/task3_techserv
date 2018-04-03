package ru.artemev;

import ru.artemev.interfaces.Stocks;

class Stock implements Stocks {
    private int productCount = 1000;

    public int getProductCount() {
        return productCount;
    }

    @Override
    public synchronized int takeProduct(int count, int custNumber){
       if ((productCount - count) < 0){
           count = productCount;
           productCount = 0;
       } else {
           productCount -= count;
       }
        printText(count, custNumber);
        return count;
    }
    @Override
    public void printText(int count, int custNumber){
        if (!stockIsEmpty() && count != 0 ||stockIsEmpty() && count != 0) {
            System.out.println("Покупатель " + custNumber + "; Взято сейчас со склада: " + count + "; на складе осталось " + getProductCount());
        }
    }
    @Override
    public boolean stockIsEmpty(){
        return productCount == 0;
    }
}
