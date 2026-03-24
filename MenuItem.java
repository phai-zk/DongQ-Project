public class MenuItem {
    private String itemId;
    private String name;
    private double price;

    public MenuItem(String itemId, String name, double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

    public void update(String name, double price) {
        setName(name);
        setPrice(price);
    }

    // --- Getters & Setters ---
    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MenuItem)) return false;
        MenuItem item = (MenuItem)obj;
        return this.name.equals(item.getName()) && this.itemId.equals(item.getItemId());
    }

    @Override
    public String toString() {
        return "MenuItem [itemId=" + itemId + ", name=" + name + ", price=" + price + "]";
    }

}