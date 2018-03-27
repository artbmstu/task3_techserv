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

    int takeProduct(int count){
       if ((productCount - count) < 0){
           count = productCount;
           productCount = 0;
           return count;
       } else {
           productCount -= count;
           return count;
       }
    }

    boolean stockIsEmpty(){
        return productCount == 0;
    }
}
