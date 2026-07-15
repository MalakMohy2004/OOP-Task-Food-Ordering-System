package foodorder.service;

import foodorder.model.Customer;
import foodorder.model.MenuItem;
import foodorder.model.Order;
import foodorder.notification.NotificationService;
import foodorder.payment.PaymentContext;

public class OrderService {
    private final NotificationService notificationService;

    public OrderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public Order createOrder(Customer customer) {
        return new Order(customer);
    }

    public void addItemToOrder(Order order, MenuItem menuItem, int quantity) {
        order.addItem(menuItem, quantity);
    }

    public double calculateTotal(Order order) {
        return order.calculateTotal();
    }

    public void checkout(Order order, PaymentContext paymentContext) {
        double total = order.calculateTotal();
        paymentContext.executePayment(total);
        order.markPaid(paymentContext.getStrategyName());
        order.printSummary();
        notifyCustomer(order);
    }

    private void notifyCustomer(Order order) {
        Customer customer = order.getCustomer();
        String message = String.format(
                "Your order total is %.2f, paid via %s. Thank you!",
                order.calculateTotal(),
                order.getPaymentMethodName());
        notificationService.send(customer.getEmail(), message);
    }
}
