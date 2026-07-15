package foodorder.payment;

public class PaymentContext {
    private PaymentMethod strategy;

    public PaymentContext(PaymentMethod strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PaymentMethod strategy) {
        this.strategy = strategy;
    }

    public void executePayment(double amount) {
        strategy.pay(amount);
    }

    public String getStrategyName() {
        return strategy.getMethodName();
    }
}
