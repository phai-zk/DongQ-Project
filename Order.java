import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private String customerId;
    
    // *** เพิ่มตัวแปรอ้างอิงถึงร้านค้า ***
    private Restaurant restaurant; 
    
    private List<OrderItem> items;
    private double totalAmount;
    private LocalDateTime requestedPickupTime;
    private LocalDateTime createAt;
    private String queueNumber;
    private OrderStatus status;

    // *** อัปเดต Constructor ให้รับ Restaurant เข้ามาด้วย ***
    public Order(String customerId, Restaurant restaurant, LocalDateTime requestedPickupTime) {
        this.orderId = "ORD-" + System.currentTimeMillis();
        this.customerId = customerId;
        this.restaurant = restaurant; // เก็บข้อมูลร้าน
        this.requestedPickupTime = requestedPickupTime;
        this.createAt = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.status = OrderStatus.WAITING;
        assignQueueNumber();
    }

    private void assignQueueNumber() {
        this.queueNumber = "Q-" + (int)(Math.random() * 1000);
    }

    public void addOrderItem(OrderItem item) {
        this.items.add(item);
        calculateTotal();
    }

    public void calculateTotal() {
        this.totalAmount = 0;
        for (OrderItem item : items) {
            this.totalAmount += item.getSubTotal();
        }
    }

    // --- Getters ---
    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public Restaurant getRestaurant() { return restaurant; } // *** เพิ่ม Getter สำหรับร้าน ***
    public List<OrderItem> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
    public LocalDateTime getRequestedPickupTime() { return requestedPickupTime; }
    public String getQueueNumber() { return queueNumber; }
    public OrderStatus getStatus() { return status; }
}