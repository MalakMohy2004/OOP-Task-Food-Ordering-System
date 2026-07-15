# Food Ordering System

A small Java console application built for the "Object-Oriented Programming" internship
session exercise.

## Requirements covered

- View menu items
- Add items to an order
- Choose payment method
- Calculate total price
- Print order summary
- Send notification to customer

## How the OOP pillars and SOLID principles are applied

- **Encapsulation**: `Order`, `MenuItem`, `Customer`, `OrderItem` keep their fields private
  and expose behavior through methods.
- **Abstraction**: `PaymentMethod` and `NotificationService` are interfaces; callers only
  depend on `pay(amount)` / `send(recipient, message)`, not on how each implementation works.
- **Inheritance**: `DiscountedMenuItem extends MenuItem` and overrides `getPrice()`.
- **Polymorphism**: `CardPayment`, `CashPayment`, `WalletPayment` all implement
  `PaymentMethod`; `EmailNotificationService` and `SmsNotificationService` both implement
  `NotificationService`. `OrderService` calls them through the interface type.
- **Single Responsibility**: `Menu`, `Order`, `OrderService`, and the payment/notification
  classes each own one job.
- **Open/Closed**: a new payment method or notification channel can be added without
  modifying `OrderService`.
- **Dependency Inversion**: `OrderService` depends on the `NotificationService` interface,
  not a concrete implementation; the concrete class is injected in `Main`.

## Strategy Pattern (payment methods)

Payment is implemented as the classic Strategy pattern:

- `PaymentMethod` — the strategy interface (`pay(amount)`, `getMethodName()`)
- `CardPayment`, `InstallmentPayment`, `CashPayment` — three interchangeable strategies
- `PaymentContext` — the context that holds a reference to the current strategy and
  delegates `executePayment(amount)` to it, without knowing which concrete strategy it is

`OrderService.checkout()` only talks to `PaymentContext`, so a new payment method can be
added by writing one new class that implements `PaymentMethod` — nothing else in the
codebase changes.

```
PaymentMethod strategy = new InstallmentPayment(6);
PaymentContext context = new PaymentContext(strategy);
context.executePayment(total);

context.setStrategy(new CashPayment());
context.executePayment(total);
```

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
```

## Run it

With Maven:

```bash
mvn compile exec:java -Dexec.mainClass="foodorder.Main"
```

Or plain javac/java:

```bash
javac -d out $(find src -name "*.java")
java -cp out foodorder.Main
```
