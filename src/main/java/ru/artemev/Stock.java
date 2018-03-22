package ru.artemev;

import java.util.HashMap;
import java.util.Map;

class Stock {
    private static Stock stock;
    private int productCount = 1000;
    private Map<Integer, Integer> customerInfo;
    private Stock(){
        customerInfo = new HashMap<>();
    }

    synchronized static Stock getStock(){
        if (stock == null){
            stock = new Stock();
            return stock;
        } else return stock;
    }

    Map<Integer, Integer> getCustomerInfo() {
        return customerInfo;
    }

    synchronized void takeProduct(int count, int custNumber){
       if ((productCount - count) < 0){
           count = productCount;
           productCount = 0;
           addCustomerInfo(custNumber, count);
       } else {
           productCount -= count;
           addCustomerInfo(custNumber, count);
       }
       if (this.productCount != 0 && count != 0 || this.productCount == 0 && count != 0) {
           System.out.println("Покупатель " + custNumber + "; Всего куплено " + customerInfo.get(custNumber) +
                   "; Осталось на складе " + this.productCount + "; Взято сейчас со склада: " + count);
       }
    }

    private void addCustomerInfo(int custNumber, int count){
        if (customerInfo.containsKey(custNumber)) {
            customerInfo.put(custNumber, customerInfo.get(custNumber) + count);
        } else customerInfo.put(custNumber, count);
    }
    boolean stockIsEmpty(){
        return productCount == 0;
    }
}
