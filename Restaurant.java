import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Restaurant extends User {
	private String name;
	private LocalDateTime openTime;
	private LocalDateTime closeTime;
	private List<Feedback> feedbacks;
	
	// *** เพิ่มตัวแปรเก็บรายการอาหารของร้าน ***
	private List<MenuItem> menuList;
	private Scanner sc;

	public Restaurant(String name, Scanner sc) {
		this.menuList = new ArrayList<>(); // สร้าง List ว่างๆ รอไว้
		this.feedbacks = new ArrayList<>();
		this.sc = sc;
		this.name = name;
	}

	// *** เพิ่มเมธอดสำหรับจัดการเมนู ***
	public void addMenu(MenuItem item) {
		if (this.menuList.contains(item)) return;
		this.menuList.add(item);
	}

	public void removeMenu(MenuItem item) {
		if (!this.menuList.contains(item)) return;
		this.menuList.remove(item);
	}

	public void updateMenu(MenuItem oldItem, MenuItem newItem) {
		if (!this.menuList.contains(oldItem)) return;
		int idx = this.menuList.indexOf(oldItem);
		this.menuList.get(idx).update(newItem.getName(), newItem.getPrice());
	}

	public void showMenu(){
        for (int i = 0; i < menuList.size(); i++) {
			MenuItem menu = menuList.get(i);
            System.out.println("[" + (i + 1) + "]\t" + menu.getName() + "\t" + menu.getPrice()+" Bath");
        }
	}

	public void assignQueue(Order order) {
		QueueManager.assignQueue(order);
	}

	public void displayQueue() {
		QueueManager.displayCurrentQueue(this.userId);
	}

	public void setPreParing() {
		PriorityQueue<Order> queue = QueueManager.getOrderQueue(userId);
		Order order = queue.peek();
		order.updateStatus(OrderStatus.PREPARING);
		Notification.sendNotification("Your Order in preparing.");
	}

	public void setFinish() {
		PriorityQueue<Order> queue = QueueManager.getOrderQueue(userId);
		Order order = queue.poll();
		order.updateStatus(OrderStatus.COMPLETED);
		Notification.sendNotification("Your Order is finish.");
	}

	public void cancleOrder() {
		displayQueue();
		PriorityQueue<Order> queue = QueueManager.getOrderQueue(userId);
		List<Order> orderList = new ArrayList<>(queue);
		System.out.print("Accept Order: ");
		int idx = sc.nextInt();
		QueueManager.cancleOrder(userId, orderList.get(idx));
	}

	// เมธอดนี้ให้ลูกค้าเรียกดูเมนูของร้าน
	public List<MenuItem> getMenu() {
		return this.menuList;
	}
	
	public void viewFeedback() {
		for (Feedback feedback : feedbacks) {
			System.out.println(feedback);
		}
	}

	public void recievedFeedback(Feedback feedback) {
		this.feedbacks.add(feedback);
	}

	// --- Getters & Setters ---
	public String getRestaurantId() { return userId; }

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	public LocalDateTime getOpenTime(LocalDateTime openTime) { return this.openTime; }
	public void setOpenTime(LocalDateTime openTime) { this.openTime = openTime; }

	public LocalDateTime getCloseTime(LocalDateTime closeTime) { return this.closeTime; }
	public void setCloseTime(LocalDateTime closeTime) { this.closeTime = closeTime; }

	public List<Feedback> getFeedbacks() { return this.feedbacks; }

	public boolean isOpen() { 
		LocalDateTime now = LocalDateTime.now();

        boolean isPastOpenTime = !now.isBefore(openTime);
        boolean isBeforeCloseTime = now.isBefore(closeTime);

        return isPastOpenTime && isBeforeCloseTime;
	}

	public void setAvalibleTime(LocalDateTime openTime, LocalDateTime closeTime) {
		setOpenTime(openTime);
		setCloseTime(closeTime);
	}

	@Override
	public String toString() {
		return "Restaurant [name=" + name + ", openTime=" + openTime + ", closeTime="
				+ closeTime + "]";
	}

}