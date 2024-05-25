# Втора лабораториска вежба по Софтверско инженерство

Nikolas Kuzmanovski, 213240


![CFG](https://github.com/213240/SI_2024_lab2_213240/assets/156296136/a5086f24-8dbe-4e83-a17a-b57bab83065e)

3. Цикломатската комплексност на дадениот код е 11
   До таа бројка стигнав на два начини
   Прв начин: Броенје на вкупниот број на полиња
   Втор начин: Бројот на ребра(36) - бројот на јазли(27) + 2

4.@Test
    void testAllItemsNull() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(null, 100);
        });
        assertEquals("allItems list can't be null!", exception.getMessage());
    }

    @Test
    void testNameNull() {
        Item item = new Item(null, "12345", 100, 0);
        boolean result = SILab2.checkCart(Collections.singletonList(item), 100);
        assertEquals("unknown", item.getName());
        assertTrue(result);
    }

    @Test
    void testBarcodeNull() {
        Item item = new Item("Item1", null, 100, 0);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(Collections.singletonList(item), 100);
        });
        assertEquals("No barcode!", exception.getMessage());
    }

    @Test
    void testInvalidBarcodeCharacter() {
        Item item = new Item("Item1", "123a5", 100, 0);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(Collections.singletonList(item), 100);
        });
        assertEquals("Invalid character in item barcode!", exception.getMessage());
    }

    @Test
    void testDiscountApplied() {
        Item item = new Item("Item1", "12345", 200, 0.5f);
        assertTrue(SILab2.checkCart(Collections.singletonList(item), 100));
    }

    @Test
    void testNoDiscount() {
        Item item = new Item("Item1", "12345", 200, 0);
        assertFalse(SILab2.checkCart(Collections.singletonList(item), 100));
    }

    @Test
    void Special Discount Condition() {
        Item item = new Item("Item1", "012345", 400, 0.1f);
        assertTrue(SILab2.checkCart(Collections.singletonList(item), 100));
    }

    @Test
    void testSumLessThanOrEqualToPayment() {
        Item item1 = new Item("Item1", "12345", 50, 0);
        Item item2 = new Item("Item2", "67890", 50, 0);
        assertTrue(SILab2.checkCart(Arrays.asList(item1, item2), 100));
    }

    @Test
    void testSumGreaterThanPayment() {
        Item item1 = new Item("Item1", "12345", 60, 0);
        Item item2 = new Item("Item2", "67890", 50, 0);
        assertFalse(SILab2.checkCart(Arrays.asList(item1, item2), 100));
    }

testAllItemsNull: Осигурува дека се фрла исклучок кога allItems е null.
testNameNull: Осигурува дека името е поставено на "unknown" ако е null и проверува дали е вратена точната вредност.
testBarcodeNull: Осигурува дека се фрла исклучок кога баркодот е null.
testInvalidBarcodeCharacter: Осигурува дека се фрла исклучок кога баркодот содржи неважечки карактери.
testDiscountApplied: Проверува дали попустот е применет правилно.
testNoDiscount: Проверува дали вкупната сума е пресметана правилно без никаков попуст.
Special Discount Condition: Проверува дали посебната состојба за попуст е применета правилно.
testSumLessThanOrEqualToPayment: Осигурува дека методот враќа true кога вкупната сума е помала или еднаква на плаќањето.
testSumGreaterThanPayment: Осигурува дека методот враќа false кога вкупната сума е поголема од плаќањето.



5.@Test
    void testMultipleConditions() {
        // Test case: (T, T, T)
        Item item1 = new Item("Item1", "012345", 400, 0.1f);
        assertTrue(SILab2.checkCart(List.of(item1), 100), "(T, T, T) failed");

        // Test case: (T, T, F)
        Item item2 = new Item("Item1", "112345", 400, 0.1f);
        assertTrue(SILab2.checkCart(List.of(item2), 40), "(T, T, F) failed");

        // Test case: (T, F, T)
        Item item3 = new Item("Item1", "012345", 400, 0);
        assertFalse(SILab2.checkCart(List.of(item3), 100), "(T, F, T) failed");

        // Test case: (T, F, F)
        Item item4 = new Item("Item1", "112345", 400, 0);
        assertFalse(SILab2.checkCart(List.of(item4), 100), "(T, F, F) failed");

        // Test case: (F, T, T)
        Item item5 = new Item("Item1", "012345", 200, 0.1f);
        assertTrue(SILab2.checkCart(List.of(item5), 100), "(F, T, T) failed");

        // Test case: (F, T, F)
        Item item6 = new Item("Item1", "112345", 200, 0.1f);
        assertTrue(SILab2.checkCart(List.of(item6), 100), "(F, T, F) failed");

        // Test case: (F, F, T)
        Item item7 = new Item("Item1", "012345", 200, 0);
        assertFalse(SILab2.checkCart(List.of(item7), 100), "(F, F, T) failed");

        // Test case: (F, F, F)
        Item item8 = new Item("Item1", "112345", 200, 0);
        assertFalse(SILab2.checkCart(List.of(item8), 100), "(F, F, F) failed");
    }

(T, T, T): Пример каде цената е над 300, попустот е поголем од 0 и баркодот започнува со '0'.
(T, T, F): Пример каде цената е над 300, попустот е поголем од 0, но баркодот не започнува со '0'.
(T, F, T): Пример каде цената е над 300, нема попуст, но баркодот започнува со '0'.
(T, F, F): Пример каде цената е над 300, нема попуст и баркодот не започнува со '0'.
(F, T, T): Пример каде цената е 300 или помала, попустот е поголем од 0 и баркодот започнува со '0'.
(F, T, F): Пример каде цената е 300 или помала, попустот е поголем од 0, но баркодот не започнува со '0'.
(F, F, T): Пример каде цената е 300 или помала, нема попуст, но баркодот започнува со '0'.
(F, F, F): Пример каде цената е 300 или помала, нема попуст и баркодот не започнува со '0'.
    
