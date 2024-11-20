package view;

import controller.CartItemController;
import controller.ClothController;
import entity.CartItem;
import entity.Cloth;
import validation.ClothValidation;

import java.util.List;
import java.util.Scanner;

public class EmployeeView {
    private static Scanner scanner = new Scanner(System.in);
    private static ClothController clothController = new ClothController();
    private static CartItemController cartItemController = new CartItemController();
    public static void main(String[] args) {
        List<Cloth> cloths;
        int choice;
        while (true) {
            System.out.println("--------- Trang Web quản lý sản phẩm ------------");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm");
            System.out.println("3. Sửa sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Thoát");

            System.out.print("Mời bạn nhập lựa chọn: ");
            choice = inputChoice();
            switch (choice) {
                case 1:
                    System.out.println("Danh sách sản phẩm");
                    cloths = clothController.getAll();
                    displayCloths(cloths);
                    break;
                case 2:
                    System.out.println("Thêm sản phẩm");
                    add();
                    break;
                case 3:
                    System.out.println("Chỉnh sửa sản phẩm");
                    edit();
                    break;
                case 4:
                    System.out.println("Xóa sản phẩm");
                    delete();
                    break;
                case 5:
                    return;
            }
        }
    }

    public static void add() {
        Cloth c = clothController.getLast();
        int no = 1;
        if (c != null) {
            no = c.getId() + 1;
        }
        System.out.println("Nhập tên sản phẩm");
        String name = scanner.nextLine();
        System.out.println("Nhập màu");
        String color = scanner.nextLine();
        System.out.println("Nhập cỡ");
        String size = scanner.nextLine();
        while (!ClothValidation.isValidSize(size)) {
            System.out.println("Kích cỡ chỉ được phép là (XS, S, M, L , XL, 2XL, 3XL)");
            System.out.println("Nhập lại kích cỡ: ");
            size = scanner.nextLine();
        }
        System.out.println("Sản phầm dành cho giới tính nào? (m/f/c)");
        char gender = scanner.nextLine().charAt(0);
        while (!ClothValidation.isValidGender(gender)) {
            System.out.println("Nhập lại: ");
            gender = scanner.nextLine().charAt(0);
        }
        System.out.println("Nhập số lượng");
        int quantity = Integer.parseInt(scanner.nextLine());
        while (!ClothValidation.isValidQuantity(quantity)) {
            System.out.println("Nhập lại số lượng: ");
            quantity = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("Nhập giá");
        int price = Integer.parseInt(scanner.nextLine());
        while (!ClothValidation.isValidPrice(price)) {
            System.out.println("Nhập lại số lượng: ");
            price = Integer.parseInt(scanner.nextLine());
        }

        Cloth cloth = new Cloth(no, name, color, size, gender, quantity, price);
        clothController.add(cloth);
        System.out.println("Thêm mới sản phẩm thành công");
    }

    private static int inputChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("Nhập sai lựa chọn. Mời bạn nhập lại");
        } catch (Exception e) {
            System.out.println("Lỗi khác");
        }
        return 0;
    }

    public static void displayCloths(List<Cloth> cloths) {
        if (cloths != null) {
            for (Cloth c : cloths) {
                System.out.println(c);
            }
        } else {
            System.out.println("Hiện tại đang chưa có sản phầm");
        }
    }

    private static void edit() {
        System.out.println("Xin hãy nhập id sản phẩm muốn chỉnh sửa");
        int id = Integer.parseInt(scanner.nextLine());
        Cloth s = clothController.getClothById(id);
        if (s == null) {
            System.out.println("Sản phẩm không tồn tại!!!!");
        } else {
            chooseInfo(s);
        }
    }

    private static void showInfo (Cloth cloth) {
        System.out.println("\n---Thông tin sản phẩm---");
        System.out.println("1. Tên sản phầm: " + cloth.getName());
        System.out.println("2. Màu: " + cloth.getColor());
        System.out.println("3. Kích cỡ: " + cloth.getSize());
        System.out.println("4. Giới tính: " + cloth.getGenderType());
        System.out.println("5. Số lượng: " + cloth.getQuantity());
        System.out.println("6. Giá: " + cloth.getPrice());
        System.out.println("7. Ok");
        System.out.println("8. Hủy");
        System.out.print("Chọn thông tin cần sửa hoặc xác nhận: ");
    }

    private static void chooseInfo (Cloth cloth) {
        int choice;
        do {
            showInfo(cloth);
            choice = inputChoice();
            switch (choice) {
                case 1:
                    System.out.print("Xin mời nhập tên: ");
                    String name = scanner.nextLine();
                    cloth.setName(name);
                    break;
                case 2:
                    System.out.print("Xin mời nhập màu: ");
                    String color = scanner.nextLine();
                    cloth.setColor(color);
                    break;
                case 3:
                    System.out.println("Nhập cỡ");
                    String size = scanner.nextLine();
                    while (!ClothValidation.isValidSize(size)) {
                        System.out.println("Kích cỡ chỉ được phép là (XS, S, M, L , XL, 2XL, 3XL)");
                        System.out.println("Nhập lại kích cỡ: ");
                        size = scanner.nextLine();
                    }
                    break;
                case 4:
                    System.out.println("Nhập giới tính (m/f/c)");
                    char gender = scanner.nextLine().charAt(0);
                    while (!ClothValidation.isValidGender(gender)) {
                        System.out.println("Nhập lại: ");
                        gender = scanner.nextLine().charAt(0);
                    }
                    break;
                case 5:
                    System.out.println("Nhập số lượng");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    while (!ClothValidation.isValidQuantity(quantity)) {
                        System.out.println("Nhập lại số lượng: ");
                        quantity = Integer.parseInt(scanner.nextLine());
                    }
                    break;
                case 6:
                    System.out.println("Nhập giá");
                    int price = Integer.parseInt(scanner.nextLine());
                    while (!ClothValidation.isValidPrice(price)) {
                        System.out.println("Nhập lại số lượng: ");
                        price = Integer.parseInt(scanner.nextLine());
                    }
                    break;
                case 7:
                    update(cloth);
                    return;
                case 8:
                    return;
            }
        } while (true);
    }

    private static void update(Cloth cloth) {
        int id = cloth.getId();
        clothController.update(id, cloth);
        System.out.println("Đã sửa lại thông tin sản phẩm thành công");
    }

    private static void delete() {
        System.out.println("Xin hãy nhập id sản phẩm muốn xóa");
        int id = Integer.parseInt(scanner.nextLine());
        Cloth c = clothController.getClothById(id);
        if (c == null) {
            System.out.println("Sản phầm không tồn tại!!!!");
        } else {
            System.out.println("Bạn có chắc muốn xóa hay không. Nhấn Y nếu đồng ý hoặc bất kỳ kí tự khác nếu không?");
            String isConfirm = scanner.nextLine();
            if (isConfirm.equalsIgnoreCase("y")) {
                clothController.delete(id);
                List<CartItem> items = cartItemController.getByProductId(id);
                if (items.size() > 0) {
                    for (CartItem item : items) {
                        cartItemController.delete(item.getId());
                    }
                }
                System.out.println("Đã xóa sản phầm thành công");
            }
        }
    }
}
