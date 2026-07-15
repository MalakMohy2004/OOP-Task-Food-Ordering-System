package foodorder.notification;

public interface NotificationService {
    void send(String recipient, String message);
}
