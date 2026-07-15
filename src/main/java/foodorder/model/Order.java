package foodorder.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final Customer customer;
    private final List<OrderItem> orderItems = new ArrayList<>();
    private String paymentMethodName;
    private boolean paid;

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addItem(MenuItem menuItem, int quantity) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getMenuItem().getName().equals(menuItem.getName())) {
                orderItem.increaseQuantity(quantity);
                return;
            }
        }
        orderItems.add(new OrderItem(menuItem, quantity));
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : orderItems) {
            total += item.getSubtotal();
        }
        return total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return orderItems;
    }

    public void markPaid(String methodName) {
        this.paymentMethodName = methodName;
        this.paid = true;
    }

    public boolean isPaid() {
        return paid;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void printSummary() {
        System.out.println("Order Summary for " + customer.getName());
        for (OrderItem item : orderItems) {
            System.out.printf("%dx %s = %.2f%n", item.getQuantity(), item.getMenuItem().getName(), item.getSubtotal());
        }
        System.out.printf("Total: %.2f%n", calculateTotal());
        System.out.println("Payment method: " + (paid ? paymentMethodName : "Not paid"));
    }
}
