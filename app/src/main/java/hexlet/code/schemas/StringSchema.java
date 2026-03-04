package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    /**
     * Make data required.
     * @return StringSchema
     */
    public StringSchema required() {
        setRequired(true);
        //N.B. Для строк критерий required так же запрещает пустую строку
        addCheck("notEmpty", s -> !s.isEmpty());
        return this;
    }

    /**
     * Set minimum length.
     * @param length minimum length
     * @return StringSchema
     */
    public StringSchema minLength(int length) {
        addCheck("minLength", s -> s.length() >= length);
        return this;
    }

    /**
     * Set contains constraint.
     * @param part substring
     * @return StringSchema
     */
    public StringSchema contains(String part) {
        addCheck("contains", s -> s.contains(part));
        return this;
    }
}
