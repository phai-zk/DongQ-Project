import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    static Scanner sc = new Scanner(System.in);
    static List<Restaurant> restaurants;
    static List<Customer> customers;
    static PaymentGateway gateway;
    static Notification notification;

    public static void main(String[] args) {
        initailize();
        System.out.println();
        customerWorkFlow();
        System.out.println();
        System.out.println("Restaurant WorkFlow");
        restaurantWorkFlow();
    }

    static void initailize() {
        restaurants = new ArrayList<>();
        customers = new ArrayList<>();
        gateway = new PaymentGateway();
        notification = new Notification();

        System.out.println("======= Restuarant Setup =======");
        restaurantRegister();
        restaurantSetAvalible();
        restaurantAddMenu();
        restaurantUpdatemunu();
        restaurantRemoveMenu();
        System.out.println("================================");
    }

    static void customerWorkFlow() {
        System.out.println("======= Customer ========");
        boolean status = customerLogin();
        if (!status) return;
        System.out.println("======= Ordering ========");
        List<Order> orders = customerOrdered();
        for (int i = 0; i < customers.size(); i++) {
            customerPay(orders.get(i).getRestaurant(), orders.get(i));
            customerGiveFeedback(customers.get(i), orders.get(i));
        }
    }

    static void restaurantWorkFlow() {
        Restaurant restaurant = restaurants.get(0);
        if (!restaurant.login("1234@gmail.com", "1234")) return;

        System.out.println("====== Set PreParing ======");
        restaurant.setPreParing();
        restaurant.displayQueue();
        
        System.out.println("====== Set Cancle ======");
        restaurant.cancleOrder();
        restaurant.displayQueue();
        
        System.out.println("====== Set Finish ======");
        restaurant.setFinish();
        restaurant.displayQueue();

        System.out.println();
        System.out.println("====== View Feedback ======");
        restaurant.viewFeedback();

        System.out.println("====== Customer View Status ======");
        customers.get(0).veiwOrderStatus();
        System.out.println("====== Customer recieved order ======");
        customers.get(0).pickUpOrder();
    }

    // ลูกค้า
    static boolean customerLogin() {
        Customer customer1 = new Customer(sc);
        boolean regisSuccess = customer1.register("Ice", "Ice@gmail.com", "0000000000", "lnwza", "lnwza");
        if (!regisSuccess)
            return false;
        boolean login1 = customer1.login("Ice@gmail.com", "lnwza");

        Customer customer2 = new Customer(sc);
        regisSuccess = customer2.register("Beam", "Beam@gmail.com", "0000000000", "lnwza", "lnwza");
        if (!regisSuccess)
            return false;
        boolean login2 = customer2.login("Beam@gmail.com", "lnwza");

        // Customer customer3 = new Customer();
        // regisSuccess = customer3.register("Anwa", "Anwa@gmail.com", "0000000000", "lnwza", "lnwza");
        // if (!regisSuccess)
        //     return false;
        // boolean login3 = customer3.login("Anwa@gmail.com", "lnwza");

        customers.add(customer1);
        customers.add(customer2);
        // customers.add(customer3);
        return login1 && login2;
    }

    static List<Order> customerOrdered() {
        List<Order> orders = new ArrayList<>();
        for (Customer customer : customers) {
            System.out.println(customer.getUserName());
            Restaurant restaurant = customer.selectedShops(restaurants);
            char isBuy;
            List<OrderItem> orderItems = new ArrayList<>();
            do {
                System.out.println("=== Select menu ===");
                OrderItem orderItem = customer.selectedMenu(restaurant);
                orderItems.add(orderItem);
                System.out.print("Buy(y/n): ");
                isBuy = sc.next().toLowerCase().charAt(0);
            } while (isBuy != 'y');

            System.out.print("Select pick up time (00:00): ");
            sc.nextLine();
            String pickupString = sc.nextLine();
            LocalDateTime pickupTime;
            try {
                String[] timeSTR = pickupString.split(":");
                int h = Integer.parseInt(timeSTR[0]);
                int m = Integer.parseInt(timeSTR[1]);
                pickupTime = LocalDateTime.now().withHour(h).withMinute(m);
            } catch (Exception e) {
                return null;
            }

            // CreateOrder
            Order order = new Order(customer.getUserId(), restaurant, pickupTime);
            for (OrderItem orderItem : orderItems) {
                order.addOrderItem(orderItem);
            }
            orders.add(order);
        }
        return orders;
    }

    static void customerPay(Restaurant restaurant, Order order) {
        Payment payment = new Payment(PaymentMethod.E_WALLET, order.getTotalAmount(), order.getOrderId());
        payment.processPayment(gateway, order);

        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            restaurant.assignQueue(order);
        }
    }

    static void customerGiveFeedback(Customer customer, Order order) {
        customer.sentFeedBack(order);
    }

    // ร้านค้า
    static void restaurantRegister() {
        boolean isSucces;

        Restaurant shop1 = new Restaurant("Auntie's Thai Kitchen", sc);
        isSucces = shop1.register("ICE 1", "1234@gmail.com", "0970465847", "1234", "1234");

        if (!isSucces)
            return;

        Restaurant shop2 = new Restaurant("Tokyo Sushi Bar", sc);
        isSucces = shop2.register("ICE 2", "1234@gmail.com", "0970465847", "1234", "1234");
        if (!isSucces)
            return;

        restaurants.add(shop1);
        restaurants.add(shop2);
        for (Restaurant restaurant : restaurants) {
            System.out.println(restaurant);
        }
    }

    static void restaurantSetAvalible() {
        LocalDateTime time1 = LocalDateTime.now().withHour(13).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime time2 = LocalDateTime.now().withHour(18).withMinute(0).withSecond(0).withNano(0);
        restaurants.get(0).setAvalibleTime(time1, time2);
        restaurants.get(1).setAvalibleTime(time1, time2);
        for (Restaurant restaurant : restaurants) {
            System.out.println(restaurant);
        }
    }

    static void restaurantAddMenu() {
        MenuItem item = new MenuItem("M01", "Fried Rice", 40.0);
        System.out.println("====== ADD MENU =========");
        restaurants.get(0).addMenu(item);
        restaurants.get(0).addMenu(new MenuItem("M02", "Fried Egg", 10.0));
        for (MenuItem menu : restaurants.get(0).getMenu()) {
            System.out.println(menu);
        }
    }

    static void restaurantUpdatemunu() {
        System.out.println("====== UPDATE MENU =========");
        MenuItem item = new MenuItem("M01", "Fried Rice", 40.0);
        MenuItem item2 = new MenuItem("M01", "Basil Fried Rice", 60.0);
        restaurants.get(0).updateMenu(item, item2);
        for (MenuItem menu : restaurants.get(0).getMenu()) {
            System.out.println(menu);
        }
    }

    static void restaurantRemoveMenu() {
        MenuItem item = new MenuItem("M01", "Fried Rice", 40.0);
        System.out.println("====== BEFORE REMOVE MENU =========");

        restaurants.get(1).addMenu(new MenuItem("S01", "Salmon Sashimi", 250.0));
        restaurants.get(1).addMenu(new MenuItem("S02", "Miso Soup", 40.0));
        restaurants.get(1).addMenu(item);

        for (MenuItem menu : restaurants.get(1).getMenu()) {
            System.out.println(menu);
        }

        restaurants.get(1).removeMenu(item);

        System.out.println("======= AFTER REMOVE MENU ==========");
        for (MenuItem menu : restaurants.get(1).getMenu()) {
            System.out.println(menu);
        }
    }

}