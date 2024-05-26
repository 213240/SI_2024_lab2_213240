package mk.ukim.finki;

import java.util.List;


public class SILab2 {
    public static boolean checkCart(List<Item> allItems, int payment){
        if (allItems == null){
            throw new RuntimeException("allItems list can't be null!");
        }

        float sum = 0;

        for (int i = 0; i < allItems.size(); i++){
            Item item = allItems.get(i);
            if (item.getName() == null || item.getName().length() == 0){
                item.setName("unknown");
            }
            if (item.getBarcode() != null){
                String allowed = "0123456789";
                char chars[] = item.getBarcode().toCharArray();
                for (int j = 0; j < item.getBarcode().length(); j++){
                    char c = item.getBarcode().charAt(j);
                    if (allowed.indexOf(c) == -1){
                        throw new RuntimeException("Invalid character in item barcode!");
                    }
                }
                if (item.getDiscount() > 0){
                    sum += item.getPrice()*item.getDiscount();
                }
                else {
                    sum += item.getPrice();
                }
            }
            else {
                throw new RuntimeException("No barcode!");
            }
            if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'){
                sum -= 30;
            }
        }
        if (sum <= payment){
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean checkMultipleCondition(int price, float discount, String barcode) {
        if (price > 300 && discount > 0 && barcode.charAt(0) == '0'){
            return true;
        }
        return false;
    }
}