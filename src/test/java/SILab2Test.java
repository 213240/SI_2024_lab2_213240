import mk.ukim.finki.Item;
import mk.ukim.finki.SILab2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class SILab2Test {
    Item CASE1 = new Item(null,"0123456789",500,20);
    Item CASE2 = new Item("TEST1","1323456789",300,0);
    Item CASE3 = new Item("TEST2",null,300,0);
    Item CASE4 = new Item("TEST3","032A456B89",200,0);

    Item CASE5 = null;

    @Test
    void everyBranch() {
        // payment=10000000, sum<=payment is TRUE
        // CASE1 doesn't have a name, discount more than 0, has a barcode that starts with 0, price bigger than 300
        assertTrue(() -> SILab2.checkCart(List.of(CASE1),100000000));

        // payment=1 , sum<=payment is FALSE
        // CASE2 has name, discount is 0, doesn't have barcode that starts with 0, price smaller than 300
        assertFalse(() -> SILab2.checkCart(List.of(CASE2),1));

        // instance of RuntimeException so we can store the exception and check message afterwards
        RuntimeException ex;

        // CASE3 throws a "No barcode!" exception, because it's barcode is null
        ex = assertThrows(RuntimeException.class, ()->SILab2.checkCart(List.of(CASE3),200));
        assertTrue(ex.getMessage().contains("No barcode!"));

        // CASE4 throws a "Invalid character in item barcode!" exception, because it's barcode is 032A456B89 which has characters
        ex = assertThrows(RuntimeException.class, ()->SILab2.checkCart(List.of(CASE4),500));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        // CASE5: allItems = []
        // CASE5 throws a "allItems list can't be null!" exception, because it's null
        ex = assertThrows(RuntimeException.class, ()->SILab2.checkCart(List.of(CASE5),200));
        // here we avoid NullPointer exception by making the message we receive check if it's null
        assertTrue(ex.getMessage() == null);

    }
    @Test
    void multipleCondition() {
        // alItems = [ item1, item2, item3, item4, item5, item6, item7, item8 ]

        // T T T
        // item1.getPrice() is 500, item1.getDiscount() is 10 and item1.getBarcode() is  "0151357189"
        assertTrue(() -> SILab2.checkMultipleCondition(500,10,"0151357189" ));

        // T T F
        // item2.getPrice() is 600, item2.getDiscount() is 20 and item2.getBarcode() is "4131787489"
        assertFalse(() -> SILab2.checkMultipleCondition(600,20,"4131787489"));

        // T F T
        // item3.getPrice() is 800, item3.getDiscount() is 0 and item3.getBarcode() is "0131717479"
        assertFalse(() -> SILab2.checkMultipleCondition(800,0,"0131717479"));

        // T F F
        // item4.getPrice() is 1000, item4.getDiscount() is 0 and item4.getBarcode() is "5121785489"
        assertFalse(() -> SILab2.checkMultipleCondition(1000,0,"5121785489"));

        // F T T
        // item5.getPrice() is 200, item5.getDiscount() is 15 and item5.getBarcode() is "0629785189"
        assertFalse(() -> SILab2.checkMultipleCondition(200,15, "0629785189"));

        // F T F
        // item6.getPrice() is 100, item6.getDiscount() is 12 and item6.getBarcode() is "8349715189"
        assertFalse(() -> SILab2.checkMultipleCondition(100,12, "8349715189"));

        // F F T
        // item7.getPrice() is 50, item7.getDiscount() is 0 and item7.getBarcode() is "0349726889"
        assertFalse(() -> SILab2.checkMultipleCondition(50,0,"0349726889"));

        // F F F
        // item8.getPrice() is 0, item8.getDiscount() is 0 and item8.getBarcode() is "1234756789"
        assertFalse(() -> SILab2.checkMultipleCondition(0,0, "1234756789"));

    }
}