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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        final int CUSTOMERS = Integer.parseInt(args[0]);
        CyclicBarrier barrier = new CyclicBarrier(CUSTOMERS);
        ExecutorService service = Executors.newFixedThreadPool(CUSTOMERS);
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < CUSTOMERS; i++) {
            Customer customer = new Customer(i);
            Future f = service.submit(() -> {
                try {
                    do {
                        barrier.await();
                        customer.takeProduct((int) (Math.random() * 10));
                        barrier.await();
                    } while (!Stock.getStock().stockIsEmpty());
                } catch (InterruptedException e) {
                    System.out.println("Ошибка. Прерывание потока.");
                } catch (BrokenBarrierException e) {
                    System.out.println("Ошибка. Нарушенное состояние барьера(barrier).");
                }
            });
            futures.add(f);
        }
        for (Future f : futures)
        {
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
        System.out.println("\n ИТОГО:");
        for (int i = 0; i < CUSTOMERS; i++) {
            System.out.println("Покупатель " + i + " приобрел " + Stock.getStock().getCustomerInfo().get(i) + " товаров");
        }
    }
}