public abstract class User {
    protected String userId;
    protected String username;
    protected String password;
    protected String phone;

    public boolean login(String username, String password) {
        if (username.length() <= 5) {
            System.out.println("Fail to login");
            return false;
        }
        userId = username.substring(0, 5);
        this.username = username;
        this.password = password;
        phone = "000-000-0000";
        System.out.println("Login Success! Welcome " + this.username);
        return true;
    }

    public boolean register(String username, String phone, String password, String confirmPass) {
        if (username.length() <= 5 && !password.equals(confirmPass) && phone.matches("^\\+?[1-9]\\d{1,10}$")) {
            System.out.println("Fail to register");
            return false;
        }
        userId = username.substring(0, 5);
        this.username = username;   
        this.password = password;
        this.phone = phone;
        System.out.println("Register Success! Welcome " + this.username);
        return true;
    }

    public void logout() {
        userId = "";
        username = "";
        password = "";
        phone = "";
    }

    // --- Getters & Setters ---
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}