package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ValidatorTest {

    @Test
    void getStringSchema() {
        Validator validator = new Validator();

        assertNotNull(validator.string());
    }
}
