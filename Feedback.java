import java.time.LocalDateTime;

public class Feedback {
	private String feedbackId;
	private int rating;
	private String comment;
	private LocalDateTime createdAt;

	public void sentFeedback(int rating, String comment) {
		this.feedbackId = "FB-" + System.currentTimeMillis();
		this.rating = rating;
		this.comment = comment;
		this.createdAt = LocalDateTime.now();
	}

	// --- Getters ---
	public String getFeedbackId() { return feedbackId; }
	public int getRating() { return rating; }
	public String getComment() { return comment; }
	public LocalDateTime getCreateAt() { return this.createdAt; }
}
