public abstract class User {
    protected String userId;
    protected String username;
    protected String password;
    protected String phone;

    public boolean login() {
        System.out.println("User logged in.");
        return true;
    }

    public void logout() {
        System.out.println("User logged out.");
    }

    // --- Getters & Setters ---
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}