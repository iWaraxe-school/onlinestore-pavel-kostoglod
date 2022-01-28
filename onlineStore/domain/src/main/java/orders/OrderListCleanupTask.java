package orders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

public class OrderListCleanupTask extends TimerTask {
    Order order = Order.getInstance();

    @Override
    public void run() {
        order.getOrderList().clear();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Clean up purchased list - " + dtf.format(now));
    }
}