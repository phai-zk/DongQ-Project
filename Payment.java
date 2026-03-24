public class Payment {
    private PaymentMethod method;
    private double amount;
    private String orderId;
    private PaymentStatus status;

    public Payment(PaymentMethod method, double amount, String orderId) {
        this.method = method;
        this.amount = amount;
        this.orderId = orderId;
        this.status = PaymentStatus.PENDING;
        System.out.println("Payment: Created Payment Object for order " + orderId);
    }

    // *** จุดที่แก้ไข: เปลี่ยนพารามิเตอร์ตัวที่ 2 จาก Receipt เป็น Order ***
    public void processPayment(PaymentGateway gateway, Order order) {
        boolean isSuccess = gateway.pay(this.amount, this.orderId);
        
        if (isSuccess) {
            this.status = PaymentStatus.SUCCESS;
            Notification.sendNotification("Payment Success for order " + this.orderId);
            
            // *** จุดที่แก้ไข: สร้าง Receipt ใหม่ตรงนี้โดยส่ง order เข้าไป แล้วสั่งพิมพ์ ***
            Receipt receipt = new Receipt(order);
            receipt.printReceipt();
            
        } else {
            this.status = PaymentStatus.FAILED;
            Notification.sendNotification("Payment Failed for order " + this.orderId);
        }
    }

    // --- Getters & Setters ---
    public PaymentMethod getMethod() { return method; }
    public void setMethod(PaymentMethod method) { this.method = method; }

    public double getAmount() { return amount; }
    public String getOrderId() { return orderId; }
    public PaymentStatus getStatus() { return status; }
}