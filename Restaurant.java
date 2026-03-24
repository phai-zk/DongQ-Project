import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant extends User {
    private String restaurantId;
    private String name;
    private LocalTime openTime;
    private LocalTime closeTime;
    private boolean isOpen;
    
    // *** เพิ่มตัวแปรเก็บรายการอาหารของร้าน ***
    private List<MenuItem> menuList;

    public Restaurant(String restaurantId, String name) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.isOpen = true;
        this.menuList = new ArrayList<>(); // สร้าง List ว่างๆ รอไว้
    }

    // *** เพิ่มเมธอดสำหรับจัดการเมนู ***
    public void addMenu(MenuItem item) {
        this.menuList.add(item);
    }

    public void removeMenu(MenuItem item) {
        this.menuList.remove(item);
    }

    // เมธอดนี้ให้ลูกค้าเรียกดูเมนูของร้าน
    public List<MenuItem> getMenu() {
        return this.menuList;
    }

    // --- Getters & Setters ---
    public String getRestaurantId() { return restaurantId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean isOpen() { return isOpen; }
    public void setOpen(boolean isOpen) { this.isOpen = isOpen; }
}