import mk.ukim.finki.Item;
import mk.ukim.finki.SILab2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    private Item CASE1;
    private Item CASE2;
    private Item CASE3;
    private Item CASE4;

    @BeforeEach
    void setUp() {
        CASE1 = new Item(null, "0123456789", 400, 15); 
        CASE2 = new Item("TEST1", "1323456789", 200, 5); 
        CASE3 = new Item("TEST2", null, 350, 10); 
        CASE4 = new Item("TEST3", "032A456B89", 100, 0); 
    }

    @Test
    void testCheckCartWithValidCases() {
        assertTrue(SILab2.checkCart(List.of(CASE1), 1000000)); 
        assertFalse(SILab2.checkCart(List.of(CASE2), 100)); 
    }

    @Test
    void testCheckCartWithExceptions() {
        RuntimeException ex;

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(CASE3), 500)); 
        assertEquals("No barcode!", ex.getMessage());

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(CASE4), 200)); 
        assertEquals("Invalid character in item barcode!", ex.getMessage());

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of((Item) null), 300)); 
        assertNull(ex.getMessage());
    }

    @Test
    void testCheckMultipleCondition() {
        assertTrue(SILab2.checkMultipleCondition(450, 20, "0151357189")); 

        assertFalse(SILab2.checkMultipleCondition(550, 15, "4131787489")); 
        assertFalse(SILab2.checkMultipleCondition(700, 0, "0131717479")); 
        assertFalse(SILab2.checkMultipleCondition(900, 5, "5121785489")); 
        assertFalse(SILab2.checkMultipleCondition(150, 10, "0629785189")); 
        assertFalse(SILab2.checkMultipleCondition(80, 5, "8349715189")); 
        assertFalse(SILab2.checkMultipleCondition(40, 0, "0349726889")); 
        assertFalse(SILab2.checkMultipleCondition(20, 0, "1234756789")); 
    }
}
