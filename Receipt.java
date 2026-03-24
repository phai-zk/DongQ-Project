import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {
    private String receiptId;
    private Order order;
    private LocalDateTime generatedAt;

    public Receipt(Order order) {
        this.receiptId = "REC-" + System.currentTimeMillis();
        this.order = order;
        this.generatedAt = LocalDateTime.now();
    }

    public void printReceipt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("========================================");
        System.out.println("                RECEIPT                 ");
        System.out.println("========================================");
        
        // *** เพิ่มการแสดงชื่อร้านบนใบเสร็จ ***
        System.out.println("Restaurant : " + this.order.getRestaurant().getName());
        
        System.out.println("Receipt No : " + this.receiptId);
        System.out.println("Date/Time  : " + this.generatedAt.format(formatter));
        System.out.println("Order No   : " + this.order.getOrderId());
        if(this.order.getQueueNumber() != null) {
            System.out.println("Queue No   : " + this.order.getQueueNumber());
        }
        System.out.println("----------------------------------------");
        System.out.println("Items:");
        for (OrderItem item : this.order.getItems()) {
            System.out.printf("- %-20s x%-2d %7.2f\n", 
                item.getMenuItem().getName(), item.getQuantity(), item.getSubTotal());
        }
        System.out.println("----------------------------------------");
        System.out.printf("Total Amount: %23.2f\n", this.order.getTotalAmount());
        System.out.println("========================================\n");
    }
}