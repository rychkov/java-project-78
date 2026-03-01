package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidatorTest {
    private Validator validator = new Validator();

    @Test
    void getStringSchema() {
        assertNotNull(validator.string());
    }

    @Test
    void getNumberSchema() {
        assertNotNull(validator.number());
    }

    @Test
    void getMapSchema() {
        assertNotNull(validator.map());
    }

    @Test
    void testStringSchemaIndependence() {
        var v = new Validator();
        var s1 = v.string();
        var s2 = v.string();

        s1.required();
        assertTrue(s2.isValid(null)); // s2 не должен стать required
        assertFalse(s1.isValid(null));
    }

    @Test
    void testNumberSchemaIndependence() {
        var v = new Validator();
        var s1 = v.number();
        var s2 = v.number();

        s1.required();
        assertTrue(s2.isValid(null)); // s2 не должен стать required
        assertFalse(s1.isValid(null));
    }

    @Test
    void testMapSchemaIndependence() {
        var v = new Validator();
        var s1 = v.map();
        var s2 = v.map();

        s1.required();
        assertTrue(s2.isValid(null)); // s2 не должен стать required
        assertFalse(s1.isValid(null));
    }
}
