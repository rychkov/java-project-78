package hexlet.code.schemas;

import static org.junit.jupiter.api.Assertions.*;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

class StringSchemaTest {

    @Test
    void testRequired() {
        var s = new StringSchema();
        s.required();

        assertFalse(s.isValid(null));
        assertFalse(s.isValid(""));
        assertTrue(s.isValid(" "));
    }

    @Test
    void testNotRequired() {
        var s = new StringSchema();

        assertTrue(s.isValid(null));
        assertTrue(s.isValid(""));
        assertTrue(s.isValid(" "));
    }

    @Test
    void testNoMinLength() {
        var s = new StringSchema();

        assertTrue(s.isValid(null));
        assertTrue(s.isValid(""));
        assertTrue(s.isValid("1"));
    }

    @Test
    void testMinLength() {
        var s = new StringSchema();
        s.minLength(3);

        assertTrue(s.isValid(null));
        assertFalse(s.isValid(""));
        assertTrue(s.isValid("123"));
    }

    @Test
    void testContainsSubString() {
        var s = new StringSchema();
        s.contains("sub");

        assertTrue(s.isValid(null));
        assertFalse(s.isValid(""));
        assertFalse(s.isValid("test"));
        assertTrue(s.isValid("testsub"));
    }

    @Test
    void testCaseStep2() {
        var v = new Validator();
        var schema = v.string();

        // Пока не вызван метод required(), null и пустая строка считаются валидным
        assertTrue(schema.isValid("")); // true
        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertFalse(schema.isValid("")); // false
        assertTrue(schema.isValid("what does the fox say")); // true
        assertTrue(schema.isValid("hexlet")); // true

        assertTrue(schema.contains("wh").isValid("what does the fox say")); // true
        assertTrue(schema.contains("what").isValid("what does the fox say")); // true
        assertFalse(schema.contains("whatthe").isValid("what does the fox say")); // false

        assertFalse(schema.isValid("what does the fox say")); // false
        // Здесь уже false, так как добавлена еще одна проверка contains("whatthe")

        // Если один валидатор вызывался несколько раз
        // то последний имеет приоритет (перетирает предыдущий)
        var schema1 = v.string();
        assertTrue(schema1.minLength(10).minLength(4).isValid("Hexlet")); // true
    }
}