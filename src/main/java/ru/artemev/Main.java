package ru.artemev;

//Требуется реализовать систему склад - покупатели, работающую по следующим правилам:
//        1. На складе учитывается остаток товара. Начальное количество = 1000
//        2. Количество покупателей указывается в параметрах запуска системы. Все покупатели стартуют одновременно и совершают покупки параллельно.
//        3. Класс покупатель может за одну покупку "купить" (уменьшить остаток на складе) случайное количество единиц товара от 1 до 10. Если на
// складе осталось меньше требуемого товара, покупатель забирает остаток.
//        4. Когда товар на складе заканчивается, каждый покупатель должен вывести в консоль общее количество купленного товара, количество
// покупок и завершить работу.
//        5. Требуется обеспечить равномерное количество покупок, т.е. количество покупок, сделанных каждым покупателем не должно отличаться
// больше чем на 1.

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        final int CUSTOMERS = Integer.parseInt(args[0]);
        CyclicBarrier barrier = new CyclicBarrier(CUSTOMERS);
        Stock stock = new Stock();
        ExecutorService service = Executors.newFixedThreadPool(CUSTOMERS);
        for (int i = 0; i < CUSTOMERS; i++) {
            Customer customer = new Customer(i);
            service.execute(() -> {
                try {
                    do {
                        barrier.await();
                        synchronized (Customer.class) {
                            customer.takeProduct((int) (Math.random() * 10) , stock);
                            System.out.println("Покупатель " + customer.getCustNumber() + "; Всего куплено " +
                                    customer.getProducts() + "; Осталось на складе " + stock.getProductCount() + "; Взято сейчас со склада: " + customer.getCount());
                        }
                        barrier.await();
                    } while (!stock.stockIsEmpty());
                } catch (InterruptedException e) {
                    System.out.println("Ошибка. Прерывание потока.");
                } catch (BrokenBarrierException e) {
                    System.out.println("Ошибка. Нарушенное состояние барьера(barrier).");
                }
            });
        }
        service.shutdown();
    }
}