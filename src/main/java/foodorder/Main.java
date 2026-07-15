package foodorder;

import foodorder.model.Customer;
import foodorder.model.DiscountedMenuItem;
import foodorder.model.Menu;
import foodorder.model.MenuItem;
import foodorder.model.Order;
import foodorder.notification.EmailNotificationService;
import foodorder.notification.NotificationService;
import foodorder.payment.CardPayment;
import foodorder.payment.CashPayment;
import foodorder.payment.InstallmentPayment;
import foodorder.payment.PaymentContext;
import foodorder.payment.PaymentMethod;
import foodorder.service.OrderService;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Burger", 5.50));
        menu.addItem(new MenuItem("Fries", 2.50));
        menu.addItem(new MenuItem("Soda", 1.75));
        menu.addItem(new DiscountedMenuItem("Combo Deal", 10.00, 20));

        menu.printMenu();

        Customer customer = new Customer("Samir Mohammed", "samir@example.com", "0100000000");

        NotificationService notificationService = new EmailNotificationService();
        OrderService orderService = new OrderService(notificationService);

        Order order = orderService.createOrder(customer);
        orderService.addItemToOrder(order, menu.getItems().get(0), 2);
        orderService.addItemToOrder(order, menu.getItems().get(1), 1);
        orderService.addItemToOrder(order, menu.getItems().get(3), 1);

        System.out.printf("Total before payment: %.2f%n", orderService.calculateTotal(order));

        String customerChoice = "installment";
        PaymentMethod selectedStrategy = resolvePaymentMethod(customerChoice);
        PaymentContext paymentContext = new PaymentContext(selectedStrategy);

        orderService.checkout(order, paymentContext);
    }

    private static PaymentMethod resolvePaymentMethod(String choice) {
        return switch (choice) {
            case "card" -> new CardPayment("4111111111111234");
            case "installment" -> new InstallmentPayment(6);
            case "cash" -> new CashPayment();
            default -> throw new IllegalArgumentException("Unsupported payment method: " + choice);
        };
    }
}
