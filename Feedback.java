import java.time.LocalDateTime;

public class Feedback {
	private String feedbackId;
	private String orderId;
	private String customerName;
	private int rating;
	private String comment;
	private LocalDateTime createdAt;

	public Feedback(String customerName) {
		this.customerName = customerName;
	}

	public void sentFeedback(int rating, String comment, Order order) {
		this.feedbackId = "FB-" + System.currentTimeMillis();
		this.rating = rating;
		this.comment = comment;
		this.createdAt = LocalDateTime.now();
		this.orderId = order.getOrderId();

		order.getRestaurant().recievedFeedback(this);
	}

	// --- Getters ---
	public String getFeedbackId() { return feedbackId; }
	public int getRating() { return rating; }
	public String getComment() { return comment; }
	public String getOrderId() { return orderId; }
	public LocalDateTime getCreateAt() { return this.createdAt; }

	@Override
	public String toString() {
		return "Feedback [by=" + customerName + ", OrderId=" + orderId + ", rating=" + rating + ", comment=" + comment + ", createdAt="
				+ createdAt + "]";
	}
}
