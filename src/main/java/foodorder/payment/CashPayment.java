package foodorder.payment;

public class CashPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.printf("Collected %.2f in cash%n", amount);
    }

    @Override
    public String getMethodName() {
        return "Cash";
    }
}
