import java.util.*;

public class Customer extends User {

    public Restaurant selectedShops(List<Restaurant> restaurants) {
        Scanner sc = new Scanner(System.in);
        int idx;
        
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + restaurants.get(i).getName());
        }

        do { 
            System.out.print("Selected: ");
            idx = sc.nextInt();
        }
        while (idx <= 0 || idx > restaurants.size());
        
        Restaurant selectedShop = restaurants.get(idx - 1); 
        System.out.println("Customer selected: " + selectedShop.getName() + "\n");
        return selectedShop;
    }

    public OrderItem selectedMenu(Restaurant restaurant) {
        List<MenuItem> menuItems = restaurant.getMenu();
        Scanner sc = new Scanner(System.in);
        int idx;
        int qty = 0;
        restaurant.showMenu();

        do { 
            System.out.print("Selected: ");
            idx = sc.nextInt();
            System.out.print("Quantity: ");
            qty = sc.nextInt();
        }
        while (idx <= 0 || idx > menuItems.size());

        OrderItem orderItem = new OrderItem(menuItems.get(idx - 1), qty);
        return orderItem;
    }
    
    public void sentFeedBack(Order order) {
        Feedback feedback = new Feedback(username);
        Scanner sc = new Scanner(System.in);
        System.out.print("Rate (0-5): ");
        int rate = sc.nextInt();
        System.out.print("Comment: ");
        sc.nextLine();
        String comment = sc.nextLine();         
        feedback.sentFeedback(rate, comment, order);
    }
}