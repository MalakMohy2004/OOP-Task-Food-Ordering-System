package foodorder.payment;

public interface PaymentMethod {
    void pay(double amount);

    String getMethodName();
}
