import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class QueueManager {
    // ใช้ PriorityQueue เพื่อจัดเรียงลำดับออเดอร์อัตโนมัติ
    static private Map<String, PriorityQueue<Order>> queueList = new HashMap<>();

    static public void assignQueue(Order order) {
        String restuarantId = order.getRestaurant().getUserId();
        String customerId = order.getCustomerId();
        System.out.println(restuarantId);
        queueList.putIfAbsent(restuarantId, new PriorityQueue<>(Comparator.comparing(Order::getRequestedPickupTime)));
        queueList.get(restuarantId).add(order); // พอ Add เข้าไป มันจะจัดเรียงตำแหน่งให้เองอัตโนมัติ

        queueList.putIfAbsent(customerId, new PriorityQueue<>(Comparator.comparing(Order::getStatus)));
        queueList.get(customerId).add(order); // พอ Add เข้าไป มันจะจัดเรียงตำแหน่งให้เองอัตโนมัติ
    }

    // ฟังก์ชันสำหรับดูคิวทั้งหมดที่เรียงแล้ว
    static public void displayCurrentQueue(String id) {
        System.out.println("\n--- Current Queue (Sorted by Pickup Time) ---");
        // สร้าง PriorityQueue ชั่วคราวเพื่อปริ้นท์โดยไม่ให้ข้อมูลในคิวหลักหาย
        PriorityQueue<Order> tempQueue =  new PriorityQueue<>(queueList.get(id));
        int qNumber = 1;
        while (!tempQueue.isEmpty()) {
            Order o = tempQueue.poll();
            System.out.println("Queue #" + qNumber + " | Status: " + o.getStatus() + " | Order: " + o.getOrderId() + " | Time: " + o.getRequestedPickupTime());
            qNumber++;
        }
        System.out.println("---------------------------------------------");
    }

    static public PriorityQueue<Order> getOrderQueue(String id) {
        return queueList.get(id);
    }

    static public void cancleOrder(String restid, Order order) {
        queueList.get(restid).remove(order);

        String customerId = order.getCustomerId();
        queueList.get(customerId).remove(order);
        order.updateStatus(OrderStatus.REJECTED);
        queueList.get(customerId).add(order);
        Notification.sendNotification("Sorry your order have rejected");
        
    }

}