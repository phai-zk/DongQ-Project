public abstract class User {
	protected String userId;
	protected String username;
	protected String password;
	protected String email;
	protected String phone;

	public boolean login(String email, String password) {
		if (this.email.equals(email) && this.password.equals(password)) {
			System.out.println("Login Success! Welcome " + this.username);
			return true;
		}
		System.out.println("Fail to login");
		return false;
	}

	public boolean register(String username, String email, String phone, String password, String confirmPass) {
		String mail_regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		String phoneRegex = "^\\\\+?[1-9]\\\\d{1,10}$";
		if (username.length() <= 5 && !password.equals(confirmPass) && !phone.matches(phoneRegex)
				&& !email.matches(mail_regex)) {
			System.out.println("Fail to register");
			return false;
		}
		userId = "UR-" + System.currentTimeMillis();
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.email = email;
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

	public String getUserName() { return username; }

	public void setUserName(String username) { this.username = username; }

	public String getPhone() { return phone; }

	public void setPhone(String phone) { this.phone = phone; }

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

}