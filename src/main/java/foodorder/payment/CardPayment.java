package foodorder.payment;

public class CardPayment implements PaymentMethod {
    private final String cardNumber;

    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        String maskedCard = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        System.out.printf("Charged %.2f to card %s%n", amount, maskedCard);
    }

    @Override
    public String getMethodName() {
        return "Card";
    }
}
