package validation;

import java.util.regex.Pattern;

public class ClothValidation {
    public static boolean isValidSize(String size) {
        String regex = "^(XS|S|M|L|XL|2XL|3XL)$";

        return size.matches(regex);
    }

    public static boolean isValidGender(char gender) {
        String regex = "^(m|f|c)$";

        return Pattern.matches(regex, String.valueOf(gender));
    }

    public static boolean isValidQuantity(int quantity) {
        return quantity >= 0;
    }

    public static boolean isValidPrice(int price) {
        return price >= 0;
    }
}
