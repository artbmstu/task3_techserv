package ru.artemev;

class Stock {
    private static Stock stock;
    private int productCount = 1000;

    int getProductCount() {
        return productCount;
    }

    synchronized static Stock getStock(){
        if (stock == null){
            stock = new Stock();
            return stock;
        } else return stock;
    }

    synchronized int takeProduct(int count, int custNumber){
       if ((productCount - count) < 0){
           count = productCount;
           productCount = 0;
       } else {
           productCount -= count;
       }
        printText(count, custNumber);
        return count;
    }
    void printText(int count, int custNumber){
        if (!stockIsEmpty() && count != 0 ||stockIsEmpty() && count != 0) {
            System.out.println("Покупатель " + custNumber + "; Взято сейчас со склада: " + count + "; на складе осталось " + getProductCount());
        }
    }
    boolean stockIsEmpty(){
        return productCount == 0;
    }
}
