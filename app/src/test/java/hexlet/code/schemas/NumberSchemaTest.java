package hexlet.code.schemas;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class NumberSchemaTest {

    @Test
    void testNumberEdgeCases() {
        var schema = new NumberSchema();

        // По умолчанию null валиден
        assertTrue(schema.isValid(null));

        // Positive не включает 0
        schema.positive();
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-1));
        assertTrue(schema.isValid(null)); // positive() сам по себе не запрещает null

        // Проверка границ range
        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testNumberInvalidRange() {
        var schema = new NumberSchema();
        // Проверка исключения при некорректном диапазоне
        assertThrows(IllegalArgumentException.class, () -> schema.range(10, 5));
    }

    @Test
    void testCase3() {
        var schema = new NumberSchema();
        assertTrue(schema.isValid(5)); // true

        // Пока не вызван метод required(), null считается валидным
        assertTrue(schema.isValid(null)); // true
        assertTrue(schema.positive().isValid(null)); // true
        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(10)); // true

        // Потому что ранее мы вызвали метод positive()
        assertFalse(schema.isValid(-10)); // false
        //  Ноль — не положительное число
        assertFalse(schema.isValid(0)); // false

        schema.range(5, 10);

        assertTrue(schema.isValid(5)); // true
        assertTrue(schema.isValid(10)); // true
        assertFalse(schema.isValid(4)); // false
        assertFalse(schema.isValid(11)); // false
    }
}
