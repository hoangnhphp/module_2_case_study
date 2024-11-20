package view;

import controller.*;
import entity.*;
import validation.CustomerValidation;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainView {
    private static Scanner scanner = new Scanner(System.in);
    private static ClothController clothController = new ClothController();
    private static CustomerController customerController = new CustomerController();
    private static CartItemController cartItemController = new CartItemController();
    private static CartController cartController = new CartController();
    private static OrderController orderController = new OrderController();

    public static void main(String[] args) {
        showMenu();
    }
    public static boolean isLogin() {
        Customer c = customerController.readFileBinary();
        if (c == null) {
            return false;
        }
        return true;
    }
    public static void showMenu() {
        List<Cloth> cloths;
        int choice;
        while (true) {
            System.out.println("--------- Trang Web Thương Mại Thời Trang ------------");
            System.out.println("1. Tìm kiếm sản phẩm theo tên");
            System.out.println("2. Thời Trang Nam");
            System.out.println("3. Thời Trang Nữ");
            System.out.println("4. Thời Trang Trẻ Em");
            if (isLogin()) {
                System.out.println("5. Giỏ Hàng");
                System.out.println("6. Tài khoản");
                System.out.println("7. Thoát");
            } else {
                System.out.println("5. Đăng nhập/Đăng ký");
                System.out.println("6. Thoát");
            }

            System.out.print("Mời bạn nhập lựa chọn: ");
            choice = inputChoice();
            switch (choice) {
                case 1:
                    System.out.println("Tìm kiếm sản phẩm theo tên");
                    searchByName();
                    break;
                case 2:
                    System.out.println("Thời Trang Nam");
                    cloths = clothController.getClothsByGender('m');
                    displayCloths(cloths);
                    break;
                case 3:
                    System.out.println("Thời Trang Nữ");
                    cloths = clothController.getClothsByGender('f');
                    displayCloths(cloths);
                    break;
                case 4:
                    System.out.println("Thời Trang Trẻ Em");
                    cloths = clothController.getClothsByGender('c');
                    displayCloths(cloths);
                    break;
                case 5:
                    if (isLogin()) {
                        System.out.println("Giỏ Hàng của bạn");
                        Customer customer = customerController.readFileBinary();
                        Cart cart = cartController.getByUserId(customer.getId());
                        List<CartItem> items = cartItemController.getByCartId(cart.getId());
                        displayItem(items);
                    } else {
                        signInOrRegisterMenu();
                    }
                    break;
                case 6:
                    if (isLogin()) {
//                        System.out.println("Menu tài khoản của bạn");
                        customerMenu();
                        break;
                    } else {
                        return;
                    }
                case 7:
                    if (isLogin()) {
                        return;
                    } else {
                        System.out.println("Nhập sai lựa chọn. Mời bạn nhập lại");
                        break;
                    }
            }
        }
    }

    private static void searchByName() {
        System.out.println("Xin mời nhập từ tìm kiếm");
        String name = scanner.nextLine();
        List<Cloth> cloths = clothController.getClothsByName(name);
        if (!cloths.isEmpty()) {
            displayCloths(cloths);
        } else {
            System.out.println("Không tìm thấy sản phẩm");
        }
    }

    private static void customerMenu() {
        int choice;
        Customer c = customerController.readFileBinary();
        while (true) {
            System.out.println("--------- Trang tài khoản ------------");
            System.out.println("1. Thông tin tài khoản");
            System.out.println("2. Chỉnh sửa tài khoản");
            System.out.println("3. Danh sách hóa đơn");
            System.out.println("4. Đăng xuất");
            System.out.println("5. Quay lại");
            System.out.print("Mời bạn nhập lựa chọn: ");
            choice = inputChoice();
            switch (choice) {
                case 1:
                    showInfo(c, false);
                    break;
                case 2:
                    chooseInfo(c);
                    break;
                case 3:
                    showOrder();
                    break;
                case 4:
                    customerController.logout();
                    return;
                case 5:
                    return;
            }
        }
    }

    private static void showOrder() {
        Customer c = customerController.readFileBinary();
        List<Order> orders = orderController.getByUserId(c.getId());
        if (!orders.isEmpty()) {
            for (Order order : orders) {
                System.out.println(order.getOrderNo() + " | " + order.getOrderDateTime() + " | " + order.getOrderStatus() + " | " + order.getTotalPrice());
            }
        } else {
            System.out.println("Bạn chưa đặt hàng!!!");
        }
    }

    private static void showInfo(Customer c, boolean isEdit) {
        System.out.println("\n---Thông tin tài khoản---");
        System.out.println("1. Tên: " + c.getName());
        System.out.println("2. Email: " + c.getEmail());
        System.out.println("3. Số điện thoại: " + c.getPhone());
        System.out.println("4. Địa chỉ: " + c.getAddress());
        System.out.println("5. Mật khẩu: " + c.getPassword());
        if (isEdit) {
            System.out.println("6. Ok");
            System.out.println("7. Hủy");
            System.out.print("Chọn thông tin cần sửa hoặc xác nhận: ");
        }
    }

    private static void chooseInfo(Customer c) {
        int choice;
        do {
            showInfo(c, true);
            choice = inputChoice();
            switch (choice) {
                case 1:
                    String name = getInput("Xin mời nhập tên");
                    c.setName(name);
                    break;
                case 2:
                    String email = getInput("Xin mời nhập email");
                    while (!CustomerValidation.isValidEmail(email)) {
                        System.out.println("Email sai định dạng!!!!");
                        System.out.println("Xin mời nhập lại email");
                        email = scanner.nextLine();
                    }

                    while (!CustomerValidation.isUniqueEmail(email, c.getId())) {
                        System.out.println("Email đang bị trùng!!!!");
                        System.out.println("Xin mời nhập lại email");
                        email = scanner.nextLine();
                    }
                    c.setEmail(email);
                    break;
                case 3:
                    String phone = getInput("Xin mời nhập số điện thoại");
                    while (!CustomerValidation.isValidPhone(phone)) {
                        System.out.println("Số điện thoại sai định dạng!!!!");
                        System.out.println("Xin mời nhập lại số điện thoại");
                        phone = scanner.nextLine();
                    }
                    c.setPhone(phone);
                    break;
                case 4:
                    String address = getInput("Xin mời nhập địa chỉ");
                    c.setAddress(address);
                    break;
                case 5:
                    String pass = getInput("Xin mời nhập mật khẩu");
                    while (!CustomerValidation.isValidPassword(pass)) {
                        System.out.println("Mật khẩu chỉ được phép bao gồm chữ, số và ít nhất phải có 8 ký tự");
                        System.out.println("Xin mời nhập lại mật khẩu");
                        pass = scanner.nextLine();
                    }
                    c.setPassword(pass);
                    break;
                case 6:
                    update(c);
                    return;
                case 7:
                    return;
            }
        } while (true);
    }

    private static void signInOrRegisterMenu() {
        int choice;
        while (true) {
            System.out.println("--------- Trang đăng nhập/đăng ký ------------");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("3. Quay lại");
            System.out.print("Mời bạn nhập lựa chọn: ");
            choice = inputChoice();
            switch (choice) {
                case 1:
                    System.out.println("Đăng nhập");
                    loginForm();
                    return;
                case 2:
                    System.out.println("Đăng ký");
                    registerForm();
                    break;
                case 3:
                    return;
            }
        }
    }

    private static int inputChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Nhập sai lựa chọn. Mời bạn nhập lại");
        } catch (Exception e) {
            System.out.println("Lỗi khác");
        }
        return 0;
    }

    private static void update(Customer customer) {
        int id = customer.getId();
        customerController.update(id, customer);
        System.out.println("Đã sửa lại thông tin tài khoản thành công");
    }

    public static void displayCloths(List<Cloth> cloths) {
        if (cloths != null) {
            for (Cloth c : cloths) {
                System.out.println(c);
            }
            if (isLogin()) {
                addClothToCart();
            }
        } else {
            System.out.println("Hiện tại đang chưa có sản phầm");
        }
    }

    public static void displayItem(List<CartItem> items) {
        if (!items.isEmpty()) {
            int total = 0;
            List<Cloth> cloths = clothController.getAll();
            for (CartItem i : items) {
                for (Cloth c : cloths) {
                    if (i.getProductId() == c.getId()) {
                        System.out.println("ID: " + c.getId() + " | Tên: " + c.getName() + " | Màu sắc: " + c.getColor() + " | Đơn giá: " + c.getPrice() + " | Số lượng: " + i.getQuantity());
                        total += c.getPrice() * i.getQuantity();
                    }
                }
            }
            System.out.println("Tổng số tiền: " + total + " VND");
            nextAction();
        } else {
            System.out.println("Chưa có sản phầm trong giỏ hàng");
        }
    }

    public static void loginForm() {
        System.out.println("Nhập email");
        String email = scanner.nextLine();
        System.out.println("Nhập mật khẩu");
        String password = scanner.nextLine();

        boolean check = customerController.login(email, password);
        if (!check) {
            System.out.println("Tài khoản không tồn tại");
            loginForm();
        }
//        showMenu();
    }

    public static void registerForm() {
        List<Customer> customers = customerController.getAll();
        int no = 1;
        if (!customers.isEmpty()) {
            no = customers.size() + 1;
        }
        String name = getInput("Nhập tên của bạn");
        String email = getInput("Nhập email");
        while (!CustomerValidation.isValidEmail(email)) {
            System.out.println("Email sai định dạng!!!!");
            System.out.println("Xin mời nhập lại email");
            email = scanner.nextLine();
        }

        while (!CustomerValidation.isUniqueEmail(email, 0)) {
            System.out.println("Email đang bị trùng!!!!");
            System.out.println("Xin mời nhập lại email");
            email = scanner.nextLine();
        }
        String phone = getInput("Nhập số điện thoại");
        while (!CustomerValidation.isValidPhone(phone)) {
            System.out.println("Số điện thoại sai định dạng!!!!");
            System.out.println("Xin mời nhập lại số điện thoại");
            phone = scanner.nextLine();
        }
        String address = getInput("Nhập địa chỉ");
        String password = getInput("Nhập mật khẩu");
        while (!CustomerValidation.isValidPassword(password)) {
            System.out.println("Mật khẩu chỉ được phép bao gồm chữ, số và ít nhất phải có 8 ký tự");
            System.out.println("Xin mời nhập lại mật khẩu");
            password = scanner.nextLine();
        }

        Customer c = new Customer(no, name, email, phone, address, password);
        customerController.add(c);
        addCart(no);
        System.out.println("Đăng ký tài khoản thành công.");
    }

    public static String getInput(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public static void addCart(int userId) {
        List<Cart> carts = cartController.getAll();
        int no = 1;
        if (!carts.isEmpty()) {
            no = carts.size() + 1;
        }

        Cart c = new Cart(no, userId);
        cartController.add(c);
    }

    public static void nextAction() {
        int choice;
        while (true) {
            System.out.println("1. Đặt hàng");
            System.out.println("2. Giảm số lượng sản phầm trong giỏ hàng đi 1 đơn vị");
            System.out.println("3. Xóa sản phầm trong giỏ hàng");
            System.out.println("4. Quay lại");
            System.out.print("Mời bạn nhập lựa chọn: ");
            choice = inputChoice();
            switch (choice) {
                case 1:
                    createOrder();
                    break;
                case 2:
                    System.out.println("Chọn Id sản phầm để giảm số lượng sản phẩm");
                    decrement();
                    break;
                case 3:
                    System.out.println("Chọn Id sản phầm để xóa sản phẩm");
                    delete();
                    break;
                case 4:
                    return;
            }
        }
    }

    public static void addClothToCart() {
        System.out.println("Nhập id để thêm sản phầm vào giỏ hàng hoặc 0 để quay lại");
        int id = Integer.parseInt(scanner.nextLine());
        if (id == 0) {
            showMenu();
        }

        Cloth c = clothController.getClothById(id);
        if (c == null) {
            System.out.println("sản phầm không tồn tại!!!!");
        } else {
            if (c.getQuantity() == 0) {
                System.out.println("Sản phầm đã hết hàng!!!!");
            } else {
                Customer customer = customerController.readFileBinary();
                Cart cart = cartController.getByUserId(customer.getId());
                CartItem item = cartItemController.getByProductIdAndCartId(id, cart.getId());
                if (item != null) {
                    item.setQuantity(item.getQuantity() + 1);
                    cartItemController.update(item.getId(), item);
                } else {
                    CartItem lastItem = cartItemController.getLast();
                    int no = 1;
                    if (lastItem != null) {
                        no = lastItem.getId() + 1;
                    }
                    CartItem newItem = new CartItem(no, cart.getId(), id, 1);
                    cartItemController.add(newItem);
                }
                System.out.println("Thêm sản phầm vào giỏ hàng thành công");
            }
        }
    }

    public static void decrement() {
        int id = Integer.parseInt(scanner.nextLine());
        Customer customer = customerController.readFileBinary();
        Cart cart = cartController.getByUserId(customer.getId());
        CartItem item = cartItemController.getByProductIdAndCartId(id, cart.getId());
        if (item != null) {
            int quantity = item.getQuantity();
            if (quantity > 1) {
                item.setQuantity(item.getQuantity() - 1);
                cartItemController.update(item.getId(), item);
                System.out.println("Đã giảm số lượng đi 1");
            } else {
                cartItemController.delete(item.getId());
                System.out.println("Xóa sản phầm thành công");
            }
            List<CartItem> items = cartItemController.getAll();
            displayItem(items);
        } else {
            System.out.println("sản phầm không tồn tại trong giỏ hàng");
        }
    }

    public static void delete() {
        int id = Integer.parseInt(scanner.nextLine());
        Customer customer = customerController.readFileBinary();
        Cart cart = cartController.getByUserId(customer.getId());
        CartItem item = cartItemController.getByProductIdAndCartId(id, cart.getId());
        if (item != null) {
            System.out.println("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng không?");
            System.out.println("Nhập Y để xác nhận xóa hoặc bất kỳ ký tự nào để hủy");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                cartItemController.delete(item.getId());
                System.out.println("Xóa sản phầm thành công");
                List<CartItem> items = cartItemController.getAll();
                displayItem(items);
            }
        } else {
            System.out.println("sản phầm không tồn tại trong giỏ hàng. Vui lòng nhập lại id sản phẩm");
            delete();
        }
    }

    public static void createOrder() {
        Customer customer = customerController.readFileBinary();
        System.out.println("Bạn có muốn nhập địa chỉ giao hàng không? (Nhập Y nếu muốn thay đổi hoặc bất kỳ ký tự nào nếu không đổi)");
        System.out.println("*Note: Nếu bạn không điền thì địa chỉ đã được đăng ký sẽ là địa chỉ giao hàng");
        String confirm = scanner.nextLine();
        String address;
        if (confirm.equalsIgnoreCase("y")) {
            System.out.println("Vui lòng nhập địa chỉ giao hàng");
            address = scanner.nextLine();
        } else {
            address = customer.getAddress();
        }
        String remark = null;
        System.out.println("Nhập ghi chú nếu có");
        remark = scanner.nextLine();
        List<Cloth> cloths = clothController.getAll();
        Cart cart = cartController.getByUserId(customer.getId());
        List<CartItem> items = cartItemController.getByCartId(cart.getId());
        Cloth cloth;
        List<Cloth> orderDetail = new ArrayList<>();
        int total = 0;
        for (CartItem i : items) {
            for (Cloth c : cloths) {
                if (i.getProductId() == c.getId()) {
                    cloth = new Cloth(c.getId(), c.getName(), c.getColor(), c.getSize(), c.getGenderType(), i.getQuantity(), c.getPrice());
                    orderDetail.add(cloth);
                    total += i.getQuantity() * c.getPrice();
                    c.setQuantity(c.getQuantity() - i.getQuantity());
                    clothController.update(c.getId(), c);
                }
            }
            cartItemController.delete(i.getId());
        }
        int no = 1;
        Order o = orderController.getLast();
        if (o != null) {
            no = o.getId() + 1;
        }
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = dateTime.format(dateTimeFormatter);
        Order order = new Order(no, customer.getId(), padLeft(String.valueOf(no), 10, '0'), formattedDateTime, address, orderDetail, 1, total,remark);
        orderController.add(order);
        System.out.println("Đã đặt hàng thành công");
    }

    public static String padLeft(String str, int length, char padChar) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() + str.length() < length) {
            sb.append(padChar);
        }
        sb.append(str);
        return sb.toString();
    }
}
