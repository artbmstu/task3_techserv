package ru.artemev;

public class Stock {
    private int productCount = 1000;
    public int getProductCount() {
        return productCount;
    }

    int takeProduct(int count){
       if ((productCount - count) < 0){
           int finalProduct = productCount;
           productCount = 0;
           return finalProduct;
       } else {
           productCount -= count;
           return count;
       }
    }

    boolean stockIsEmpty(){
        return productCount == 0;
    }
}
