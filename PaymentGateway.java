public class PaymentGateway {
    public boolean pay(double amount, String orderId) {
        System.out.println("PaymentGateway: Processing payment of " + amount + " for order " + orderId);
        return true; // Mocking successful payment
    }
}