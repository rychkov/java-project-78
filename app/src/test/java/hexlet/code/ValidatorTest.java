package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ValidatorTest {
    Validator validator = new Validator();

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
}
