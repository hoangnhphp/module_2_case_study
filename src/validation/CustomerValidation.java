package validation;

import controller.CustomerController;
import entity.Customer;

import java.util.List;

public class CustomerValidation {
    private static CustomerController customerController = new CustomerController();
    public static boolean isValidPhone(String phone) {
        String regex = "^[0-9]{10}$";
        return phone.matches(regex);
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+$";

        return email.matches(regex);
    }

    public static boolean isValidPassword(String pass) {
        String regex = "^[a-zA-Z0-9]{8,}$";

        return pass.matches(regex);
    }

    public static boolean isUniqueEmail(String email, int id) {
        List<Customer> customers = customerController.getAll();
        boolean flag = true;
        for (Customer customer : customers) {
            if (id != 0) {
                if (customer.getId() != id && customer.getEmail().equals(email)) {
                    flag = false;
                    break;
                }
            } else {
                if (customer.getEmail().equals(email)) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
}
