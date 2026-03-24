import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant extends User {
	private String restaurantId;
	private String name;
	private LocalDateTime openTime;
	private LocalDateTime closeTime;
	private List<Feedback> feedbacks;
	private QueueManager queueManager;
	
	// *** เพิ่มตัวแปรเก็บรายการอาหารของร้าน ***
	private List<MenuItem> menuList;
	public Restaurant(String name) {
		this.menuList = new ArrayList<>(); // สร้าง List ว่างๆ รอไว้
		this.feedbacks = new ArrayList<>();
		this.queueManager = new QueueManager();
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
		queueManager.assignQueue(order);
	}

	public void displayQueue() {
		queueManager.displayCurrentQueue();
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
	public String getRestaurantId() { return restaurantId; }

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