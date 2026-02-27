package hexlet.code;

import hexlet.code.schemas.StringSchema;

public final class Validator {

    /**
     * @return StringSchema
     */
    public StringSchema string() {
        return new StringSchema();
    }
}
