public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    private double subTotal;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        calculateSubTotal();
    }

    private void calculateSubTotal() {
        this.subTotal = this.menuItem.getPrice() * this.quantity;
    }

    // --- Getters & Setters ---
    public MenuItem getMenuItem() { return menuItem; }
    public void setMenuItem(MenuItem menuItem) { this.menuItem = menuItem; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { 
        this.quantity = quantity; 
        calculateSubTotal(); 
    }

    public double getSubTotal() { return subTotal; }
}