package foodorder.payment;

public class InstallmentPayment implements PaymentMethod {
    private final int numberOfMonths;

    public InstallmentPayment(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    @Override
    public void pay(double amount) {
        double monthlyInstallment = amount / numberOfMonths;
        System.out.printf("Approved via Valu: %d installments of %.2f%n", numberOfMonths, monthlyInstallment);
    }

    @Override
    public String getMethodName() {
        return "Installment (Valu)";
    }
}
