## Project structure

```
src/main/java/foodorder/
├── Main.java
├── model/
│   ├── Customer.java
│   ├── Menu.java
│   ├── MenuItem.java
│   ├── DiscountedMenuItem.java
│   ├── Order.java
│   └── OrderItem.java
├── payment/
│   ├── PaymentMethod.java
│   ├── CardPayment.java
│   ├── CashPayment.java
│   └── WalletPayment.java
├── notification/
│   ├── NotificationService.java
│   ├── EmailNotificationService.java
│   └── SmsNotificationService.java
└── service/
    └── OrderService.java
