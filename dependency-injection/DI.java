Ví dụ chúng ta có một class cho phép User có thể gửi message.

public class EmailService {

    public void sendEmail(String message) {
        System.out.println("Message: " + message);
    }
}

public class UserController {

    private EmailService emailService = new EmailService();

    public void send() {
        emailService.sendEmail("Hello Dependency injection pattern");
    }
}
Chương trình trên rất đơn giản, chỉ gồm có 2 class. Tuy nhiên, nó có một vài giới hạn như sau:

Lớp UserController phụ thuộc trực tiếp vào class EmailService. Mỗi khi có thay đổi trong lớp EmailService,
chẳng hạn thêm tham số cho constructor của class này lên sẽ ảnh hưởng trực tiếp đến class UserController.
Một User khác không muốn sử dụng cách gửi message thông qua email, chẳng hạn qua sms, facebook, …
Khó khăn khi viết Unit Test cho UserController do phụ thuộc trực tiếp vào EmailService.
Bây giờ chúng ta sẽ áp dụng Dependency Injection để giải quyết các giới hạn trên.


public interface MessageService {

    void sendMessage(String message);
}


public class EmailService implements MessageService {

    @Override
    public void sendMessage(String message) {
        System.out.println("Email message: " + message);
    }
}

public class SmsService implements MessageService {

    @Override
    public void sendMessage(String message) {
        System.out.println("Sms message: " + message);
    }
}

public class UserController {

    private MessageService messageService;

    public UserController(MessageService messageService) {
        this.messageService = messageService;
    }

    public void send() {
        messageService.sendMessage("Hello Dependency injection pattern");
    }
}

public class DependencyInjectionPatternExample {

    public static void main(String[] args) {
        MessageService messageService = new EmailService();
        UserController userController = new UserController(messageService);
        userController.send();
    }
}
Như bạn thấy, thay vì sử dụng trực tiếp lớp EmailService chúng ta sử dụng interface.
Lớp UserController không trực tiếp khởi tạo message service mà nó được truyền từ bên ngoài vào.
Chính vì vậy mà các lớp không còn phụ thuộc vào nhau, chúng ta có thể dễ dàng sử đổi hay thêm bất kỳ service nào khác mà không ảnh hưởng đến UserController.
Đây chính là dạng Constructor Injection của Dependency Injection Pattern.

Tuy nhiên, chương trình trên vẫn còn một hạn chế là chúng ta phải khởi tạo một thể hiện cụ thể cho MessageService ở rất nhiều nơi,
chương trình cũng rất khó khăn khi cần thay thế một service cho toàn bộ hệ thống. Để giải quyết vấn đề này,
chúng ta có thể áp dụng Inversion of Control Container (IoC container), một nơi để quản lý các thành phần phụ thuộc này và cung cấp thể hiện cụ thể khi cần sử dụng.

Để giải quyết điều này, người ta nghĩ ra Dependency Injection Container hay còn gọi là Inversion of Control Container (IoC container).