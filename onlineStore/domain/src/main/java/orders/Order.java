package orders;

import products.Product;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Order extends Thread{
    private final CopyOnWriteArrayList<Product> orderList = new CopyOnWriteArrayList<>();
    public static Order orderInstance;

    public CopyOnWriteArrayList<Product> getOrderList() {
        return orderList;
    }

    public static Order getInstance() {
        if (orderInstance != null) {
            return orderInstance;
        }
        synchronized (Order.class) {
            if (orderInstance == null) {
                orderInstance = new Order();
            }
            return orderInstance;
        }
    }

    public void addProduct(Product product) {
        orderList.add(product);
    }

    public void addProductToOrder (Product product) {
        ProcessProduct processProduct = new ProcessProduct(product);
        processProduct.start();
        OrderListEraser orderEraser = OrderListEraser.getInstance();
        if (orderEraser.getState().name().equals("NEW")) {
            orderEraser.setDaemon(true);
            orderEraser.start();
        }
    }
}

class OrderListEraser extends Thread{
    private final int TIMEOUT = 120;
    public static OrderListEraser eraserInstance;

    public static OrderListEraser getInstance() {
        if (eraserInstance != null) {
            return eraserInstance;
        }
        synchronized (OrderListEraser.class) {
            if (eraserInstance == null) {
                eraserInstance = new OrderListEraser();
            }
            return eraserInstance;
        }
    }

    public void run() {
        do {
            try {
                Thread.sleep(TIMEOUT*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Order.getInstance().getOrderList().clear();
        } while (true);
    }
}

class ProcessProduct extends Thread {
    private final Product product;

    public ProcessProduct(Product product) {
        this.product = product;
    }

    public void run() {
        LocalTime startAddingTime = LocalTime.now();
        System.out.printf("[%s] Adding %s to the order ...%n",
                startAddingTime,
                product.getName());
        Random random = new Random();
        int r = random.nextInt(29) + 1;
        try{
            Thread.sleep(r*1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        Order.getInstance().addProduct(product);
        LocalTime finishAddingTime = LocalTime.now();
        System.out.printf("[%s] %s is added to the order%n",
                finishAddingTime,
                product.getName());
    }
}
