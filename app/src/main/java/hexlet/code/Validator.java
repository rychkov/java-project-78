package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public final class Validator {

    /**
     * @return StringSchema
     */
    public StringSchema string() {
        return new StringSchema();
    }

    /**
     * @return NumberSchema
     */
    public NumberSchema number() {
        return new NumberSchema();
    }
}
