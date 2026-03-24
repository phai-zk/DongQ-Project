import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // --- ส่วนเตรียมข้อมูลของระบบ (System Setup) ---
        // 1. จำลองว่าแอปนี้มีร้านค้าลงทะเบียนไว้ 2 ร้าน
        Restaurant shop1 = new Restaurant("R001", "Auntie's Thai Kitchen");
        shop1.addMenu(new MenuItem("M01", "Basil Fried Rice", 60.0));
        shop1.addMenu(new MenuItem("M02", "Fried Egg", 10.0));

        Restaurant shop2 = new Restaurant("R002", "Tokyo Sushi Bar");
        shop2.addMenu(new MenuItem("S01", "Salmon Sashimi", 250.0));
        shop2.addMenu(new MenuItem("S02", "Miso Soup", 40.0));

        // เก็บร้านค้าทั้งหมดลงใน List ของระบบ (เหมือนเป็นหน้าแรกของแอป)
        List<Restaurant> appRestaurants = new ArrayList<>();
        appRestaurants.add(shop1);
        appRestaurants.add(shop2);

        // จำลองข้อมูลลูกค้า
        Customer customer = new Customer("C001", "John Doe", "john@example.com");


        System.out.println("========== FOOD DELIVERY APP ==========\n");

        // ==========================================
        // STEP 1: ลูกค้าดูร้านค้าทั้งหมดในระบบ (View Shops)
        // ==========================================
        System.out.println("--- 1. Available Restaurants ---");
        for (int i = 0; i < appRestaurants.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + appRestaurants.get(i).getName());
        }
        System.out.println();

        // ==========================================
        // STEP 2: ลูกค้าเลือกร้านค้า (Select a Shop)
        // ==========================================
        System.out.println("--- 2. Customer selects a shop ---");
        // สมมติว่าลูกค้าเลือกร้านที่ 2 (Tokyo Sushi Bar) คือ index 1 ใน List
        Restaurant selectedShop = appRestaurants.get(1); 
        System.out.println("Customer selected: " + selectedShop.getName() + "\n");

        // ==========================================
        // STEP 3: ลูกค้าดูเมนูของร้านที่เลือก (View Menu)
        // ==========================================
        System.out.println("--- 3. Menu of " + selectedShop.getName() + " ---");
        List<MenuItem> shopMenu = selectedShop.getMenu(); // ดึงเมนูมาจากร้านที่เลือก
        for (int i = 0; i < shopMenu.size(); i++) {
            MenuItem m = shopMenu.get(i);
            System.out.println("[" + (i + 1) + "] " + m.getName() + " - " + m.getPrice() + " THB");
        }
        System.out.println();

        // ==========================================
        // STEP 4: ลูกค้าเลือกเมนูและสร้างออเดอร์ (Select Menu & Order)
        // ==========================================
        System.out.println("--- 4. Customer places an order ---");
        // สร้าง Order โดยระบุชื่อลูกค้า และ ร้านที่เลือก
        Order order = new Order(customer.getUserId(), selectedShop, LocalDateTime.of(2023, 10, 25, 12, 30));
        
        // ลูกค้าเลือก แซลมอนซาชิมิ (index 0) จำนวน 1 ที่
        order.addOrderItem(new OrderItem(shopMenu.get(0), 1)); 
        // ลูกค้าเลือก ซุปมิโซะ (index 1) จำนวน 2 ที่
        order.addOrderItem(new OrderItem(shopMenu.get(1), 2)); 
        
        order.calculateTotal();
        System.out.println("Order created! Total amount: " + order.getTotalAmount() + " THB\n");

        // --- ส่วนของการจ่ายเงินและออกใบเสร็จ (กระบวนการเดิม) ---
        PaymentGateway gateway = new PaymentGateway();
        Notification notification = new Notification();
        QueueManager queueManager = new QueueManager();

        Payment payment = new Payment(PaymentMethod.E_WALLET, order.getTotalAmount(), order.getOrderId());
        payment.processPayment(gateway, order, notification);
        
        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            queueManager.assignQueue(order);
        }
    }
}