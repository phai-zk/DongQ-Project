import java.util.PriorityQueue;
import java.util.Comparator;

public class QueueManager {
    // ใช้ PriorityQueue เพื่อจัดเรียงลำดับออเดอร์อัตโนมัติ
    private PriorityQueue<Order> queueList;

    public QueueManager() {
        // กำหนดให้ PriorityQueue เรียงลำดับจากเวลาที่ต้องการรับ (requestedPickupTime) เร็วที่สุดไปช้าที่สุด
        this.queueList = new PriorityQueue<>(Comparator.comparing(Order::getRequestedPickupTime));
    }

    public void assignQueue(Order order) {
        this.queueList.add(order); // พอ Add เข้าไป มันจะจัดเรียงตำแหน่งให้เองอัตโนมัติ
        System.out.println("QueueManager: Order " + order.getOrderId() + " added to queue. Pickup Time: " + order.getRequestedPickupTime());
    }

    // ฟังก์ชันสำหรับดูคิวทั้งหมดที่เรียงแล้ว
    public void displayCurrentQueue() {
        System.out.println("\n--- Current Queue (Sorted by Pickup Time) ---");
        // สร้าง PriorityQueue ชั่วคราวเพื่อปริ้นท์โดยไม่ให้ข้อมูลในคิวหลักหาย
        PriorityQueue<Order> tempQueue = new PriorityQueue<>(this.queueList);
        int qNumber = 1;
        while (!tempQueue.isEmpty()) {
            Order o = tempQueue.poll();
            System.out.println("Queue #" + qNumber + " | Order: " + o.getOrderId() + " | Time: " + o.getRequestedPickupTime());
            qNumber++;
        }
        System.out.println("---------------------------------------------");
    }
}